package grisbiweb.server.model;

import grisbiweb.server.xml.model.CategoryXml;

public class Category {

	private CategoryXml category;

	public Category(CategoryXml category) {
		this.category = category;
	}

	public String getId() {
		return this.category.getNb();
	}

	public Long getidLong() {
		return Long.valueOf(getId());
	}

	public String getName() {
		return this.category.getNa();
	}

}
