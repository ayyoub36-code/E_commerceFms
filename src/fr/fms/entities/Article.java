package fr.fms.entities;

public class Article {

	private long id;
	private String description;
	private String brand;
	private double price;
	private long idCategory;

	public Article(long id, String description, String brand, double price, long idCategory) {
		this.id = id;
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.idCategory = idCategory;
	}

	public Article(String description, String brand, double price, long idCategory) {
		this.idCategory = idCategory;
		this.description = description;
		this.brand = brand;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return id + " = " + description + " " + brand + " " + price + " €";
	}

}
