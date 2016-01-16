package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Date;

import javax.swing.text.StringContent;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class IndexDBpediaFile {
	public IndexDBpediaFile() {}
	
	public static void main(String[] args) throws IOException, ParseException{
		// the index path to store the index
		String indexPath = "src/index-DBPedia/";
		// the sample docs path used for debug
		String docsPath = "src/docs/dbpedia2014_long_abstracts_en_sample.ttl";
		// the long docs path
		String longDocsPath = "src/docs/long-abstracts_en.ttl";
		// the pattern to split the document
		String pattern = "<http://dbpedia.org/ontology/abstract>";
		// the line used for debug
		long line = 1;
		
		// File stream
		File file = new File(docsPath);
		BufferedReader reader = null;
		Directory index = FSDirectory.open(Paths.get(indexPath));
		
		// Analyzer for tokenizing the index
		StandardAnalyzer analyzer = new StandardAnalyzer();
		
		// IndexWriter is for writing index
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		config.setOpenMode(OpenMode.CREATE);
		IndexWriter writer = new IndexWriter(index, config);
		
		// Start reading files
		System.out.println("Start reading file " + docsPath);
		Date start = new Date();
		try{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			String tempString = null;
			// read each line
			while((tempString = reader.readLine()) != null){
				if (tempString.substring(0, 1).equals("#")){
					continue;
				}
				// split each line by the pattern address in the middle
				String[] segments = tempString.split(pattern);
				String name = nameParser(segments[0]);
				String address = addressParser(segments[0]);
				String content = contentParser(segments[1]);
				// save the info in index
				indexDoc(writer, name, address, content);
				line++;
			}
			writer.close();
			reader.close();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if (reader != null){
				try{
					reader.close();
				} catch (IOException e){
					
				}
			}
			System.out.println("Stop reading file " + longDocsPath + " with " + line + " lines");
			Date end = new Date();
			System.out.println(end.getTime() - start.getTime() + " total milliseconds");
		}
	}
	
	public static String addressParser(String address) {
		String temp = address.trim();
		return temp.substring(1, temp.length() - 1);
	}
	
	// get the name from the address
	public static String nameParser(String address){
		String prefix = "<http://dbpedia.org/resource/";
		String temp = address.replace("\\s+", "");
		temp = temp.replace("\t", "");
		temp = temp.replace("_", " ");
		return temp.substring(prefix.length(), temp.length()-1);
	}
	
	public static String contentParser(String content) {
		// remove the beginning and trailing space
		String temp = content.trim();
		for(int i = temp.length() - 2; i >= 0; i--){
			if ( (temp.charAt(i) == '"') && (temp.charAt(i+1) == '@') ){
				if (temp.charAt(0) == '"') {
					temp = temp.substring(1, i);
					break;
				}
			}
		}
		
		return temp;
	}
	
	static void indexDoc(IndexWriter writer, String name, String address, String content) throws IOException {
		// create a new document
		Document document = new Document();
		// set the fields
		document.add(new TextField("name", name, Field.Store.YES));
		document.add(new StringField("url", address, Field.Store.YES));
		document.add(new TextField("content", content, Field.Store.YES));
		// add to index
		writer.addDocument(document);
	}

}
