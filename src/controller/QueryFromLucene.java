package controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import model.DBPedia;

/**
 * Query the content (original query sentence) in Lucene index
 * @author Gaoxiang Zhang
 *
 */
public class QueryFromLucene {
	public List<DBPedia> query(String content) throws ParseException, IOException{
		// specify the analyzer for query
		StandardAnalyzer analyzer = new StandardAnalyzer();
		String index = "src/index-DBPedia/";
		// query the column (document) = "name"
		Query query = new QueryParser("name", analyzer).parse(content);
		
		// query from Lucene
		int hitsPerPage = 10;
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs docs = searcher.search(query, hitsPerPage);
		ScoreDoc[] hits = docs.scoreDocs;
		
		// return the DBPedia List
		List<DBPedia> list = new ArrayList<>();
		for(int i = 0; i < hits.length; i++) {
			int docId = hits[i].doc;
			Document document = searcher.doc(docId);
			DBPedia dbPedia = new DBPedia(document.get("name"), document.get("url"), document.get("content"), 1.0);
			list.add(dbPedia);
		}
		
		return list;
	}

}
