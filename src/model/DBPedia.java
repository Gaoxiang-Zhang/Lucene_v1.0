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
}
