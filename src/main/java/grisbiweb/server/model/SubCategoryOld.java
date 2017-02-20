package grisbiweb.server.model;

import grisbiweb.server.xml.model.SubCategoryXml;

public class SubCategoryOld {

	private SubCategoryXml subCategory;

	public SubCategoryOld(SubCategoryXml subCategory) {
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
