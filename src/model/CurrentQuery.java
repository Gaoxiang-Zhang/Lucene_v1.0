package model;

import java.util.ArrayList;

public class CurrentQuery {
	
	String name;					// the name appeared in original content
	ArrayList<String> entityName;	// possible candidates entity names
	int offset;						// the offset in original query content
	int length;						// the length in original query content
	
	// CurrentQuery
	public CurrentQuery() {
		name = "";
		entityName = new ArrayList<>();
		offset = 0;
		length = 0;
	}
	
	// getter and setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getEntityName() {
		return entityName;
	}
	public void setEntityName(ArrayList<String> entityName) {
		this.entityName = entityName;
	}
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
}
