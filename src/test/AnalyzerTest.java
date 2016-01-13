package test;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Constants;

public class AnalyzerTest {
	public static void main(String[] args) throws Exception{
		String str = "Sentence boundary disambiguation (SBD), also known as"
				+ " sentence breaking, is the problem in natural language processing "
				+ "of deciding where sentences begin and end. Often natural language processing "
				+ "tools require their input to be divided into sentences for a number of reasons. "
				+ "However sentence boundary identification is challenging because punctuation marks "
				+ "are often ambiguous. For example, a period may denote an abbreviation, decimal point, "
				+ "an ellipsis, or an email address ¨C not the end of a sentence. About 47% of the periods "
				+ "in the Wall Street Journal corpus denote abbreviations. As well, question marks and exclamation "
				+ "marks may appear in embedded quotations, emoticons, computer code, and slang. Languages like "
				+ "Japanese and Chinese have unambiguous sentence-ending markers.";
		Analyzer analyzer = new StandardAnalyzer();
		//KeywordAnalyzer analyzer = new KeywordAnalyzer();
		//SimpleAnalyzer analyzer = new SimpleAnalyzer();
		//WhitespaceAnalyzer analyzer = new WhitespaceAnalyzer();
		
		StringReader reader = new StringReader(str);
		TokenStream tokenStream = analyzer.tokenStream(null, reader);
		NGramTokenFilter filter = new NGramTokenFilter(tokenStream);
		tokenStream.reset();
		CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class);
		System.out.println(analyzer.getClass());
		
		while(tokenStream.incrementToken()){
			System.out.println(term.toString() + "|");
		}
		

	}
}
