package fr.fms.entities;

public class Category {

	private long id;
	private String name;
	private String description;

	public Category(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Category " + id + " [Name : " + name + ", Description : " + description + "]";
	}

}
