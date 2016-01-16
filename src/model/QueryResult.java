package model;

public class QueryResult {
	int offset;
	int length;
	String url;
	
	// constructor
	public QueryResult() {
		offset = 0;
		length = 0;
		url = "";
	}
	
	// getter and setter
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
