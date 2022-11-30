package br.com.gustavoleterio.model.category;

public class Category {
	String id = null;
	String name;

	public Category(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Category -> {\n\tid = " + id + ",\n\t name = " + name + "\n}";
	}

}
