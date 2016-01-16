package controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

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

import model.CurrentQuery;
import model.QueryResult;


/**
 * Query in Lucene with given content(context) and name-entity_name pair, and return top results
 * @author Gaoxiang Zhang
 *
 */
public class QueryFromLucene {
	public QueryResult query(String content, CurrentQuery variousNames) throws ParseException, IOException{
		
		// index path 
		final String index = "src/index-DBPedia/";
		final int hitsPerPage = 1000;
		//find all possible entities with given name, with case-insensitive
		CaseAnalyzer nameAnalyzer = new CaseAnalyzer();
		StandardAnalyzer contentAnalyzer = new StandardAnalyzer();
		
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
		//Query nameQuery = new QueryParser("name", nameAnalyzer).parse(variousNames.getName());
		IndexSearcher nameSearcher = new IndexSearcher(reader);
		
		// get all possible entities, find their name matching 
		ArrayList<String> possibleEntities = variousNames.getEntityName();
		ArrayList<Double> possibilities = new ArrayList<>();
		// saved instance with max possibility
		String maxAddress = "";
		double maxValue = 0;
		for(String str : possibleEntities){
			Query nameQuery = new QueryParser("name", nameAnalyzer).parse(str);
			TopDocs docs = nameSearcher.search(nameQuery, hitsPerPage);
			ScoreDoc[] hits = docs.scoreDocs;
			for(int i = 0; i < hits.length; i++){
				double nameScore = hits[i].score;
				if(nameScore > maxValue){
					int docId = hits[i].doc;
					Document document = nameSearcher.doc(docId);
					possibilities.add(nameScore);
					maxValue = hits[0].score;
					maxAddress = document.get("url");
				}
			}

		}
		QueryResult result = new QueryResult();
		result.setOffset(variousNames.getOffset());
		result.setLength(variousNames.getLength());
		result.setUrl(maxAddress);
		return result;
	}
}
