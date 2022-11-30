package br.com.gustavoleterio.model.product;

import br.com.gustavoleterio.model.category.Category;

public class Product {
	String id = null;
	String name;
	String description;
	Category category;

	public Product(String id, String name, String description, Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Product -> {\n\tid = " + id + ",\n\t name = " + name + ",\n\t description = " + description + "\n\t Category = " + category.getName() + "\n}";
	}

}
