package controller;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;

public class CaseAnalyzer extends Analyzer {
	@Override
	protected TokenStreamComponents createComponents(String arg0) {
		KeywordTokenizer source = new KeywordTokenizer();
		return new TokenStreamComponents(source, new LowerCaseFilter(source));
	}

}
