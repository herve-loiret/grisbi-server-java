package grisbiweb.server.model;

import grisbiweb.server.xml.model.SubCategoryXml;

public class SubCategory {

	private SubCategoryXml subCategory;

	public SubCategory(SubCategoryXml subCategory) {
		this.subCategory = subCategory;
	}

	public String getId() {
		return this.subCategory.getNb();
	}
	
	public String getIdCategory(){
		return this.subCategory.getNbc();
	}

	public Long getIdLong(){
		return Long.valueOf(this.subCategory.getNb());
	}
	
	public String getName() {
		return this.subCategory.getNa();
	}
}
