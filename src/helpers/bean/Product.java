package helpers.bean;

public class Product {
	
	private String productName;
	private int pointsPrice;
	private int votes;
	
	public Product(String productName, int pointsPrice, int votes) {
		this.productName = productName;
		this.pointsPrice = pointsPrice;
		this.votes = votes;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public void setPointsPrice(int pointsPrice) {
		this.pointsPrice = pointsPrice;
	}
	
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	public String getProductName() {
		return this.productName;
	}
	
	public int getPointsPrice() {
		return this.pointsPrice;
	}
	
	public int getVotes() {
		return votes;
	}
	
}
