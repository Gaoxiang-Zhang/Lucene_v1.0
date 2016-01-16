package model;

/**
 * DBPedia: the model of DBPedia File
 * @author Gaoxiang Zhang
 *
 */
public class DBPedia {
	private String itemName;
	private String resourceAddress;
	private String itemContent;
	private double confidence;
	
	public DBPedia(){
		itemName = "";
		resourceAddress = "";
		itemContent = "";
		confidence = 0.0;
	}

	public DBPedia(String name, String address, String content, double d) {
		this.itemName = name;
		this.resourceAddress = address;
		this.itemContent = content;
		this.confidence = d;
	}

	public String getItemName() {
		return itemName;
	}

	public String getResourceAddress() {
		return resourceAddress;
	}
	
	public String getItemContent() {
		return itemContent;
	}
	
	public double getConfidence() {
		return confidence;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setResourceAddress(String resourceAddress) {
		this.resourceAddress = resourceAddress;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
}
