package grisbiweb.server.service;

import grisbiweb.server.model.CategoryOld;
import grisbiweb.server.model.SubCategoryOld;
import grisbiweb.server.xml.GrisbiXmlManager;
import grisbiweb.server.xml.model.CategoryXml;
import grisbiweb.server.xml.model.SubCategoryXml;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public enum CategoryService {

	INSTANCE;

	private Map<String, CategoryOld> categoryById = new HashMap<>();
	private Map<Pair<CategoryOld, String>, SubCategoryOld> subCategoriesByIdAndCategory = new HashMap<>();

	private CategoryService() {
		this.reloadCategories();
	}

	public Map<String, CategoryOld> getCategoryById() {
		return categoryById;
	}

	public CategoryOld getCategoryById(String categoryId) {
		return this.categoryById.get(categoryId);
	}

	public Map<Pair<CategoryOld, String>, SubCategoryOld> getSubCategoriesByIdAndCategory() {
		return subCategoriesByIdAndCategory;
	}

	public SubCategoryOld getSubCategoryByIds(String categoryId, String subCategoryId) {
		CategoryOld categoryOld = this.getCategoryById(categoryId);
		Pair<CategoryOld, String> key = new ImmutablePair<>(categoryOld, subCategoryId);
		SubCategoryOld subCategoryOld = this.subCategoriesByIdAndCategory.get(key);
		return subCategoryOld;
	}

	public void reloadCategories() {
		for (Object object : GrisbiXmlManager.INSTANCE.loadGrisbi().getGroupCategories()) {
			if (object instanceof CategoryXml) {
				CategoryOld categoryOld = new CategoryOld((CategoryXml) object);
				this.categoryById.put(categoryOld.getId(), categoryOld);
			} else {
				SubCategoryOld subCategoryOld = new SubCategoryOld((SubCategoryXml) object);
				CategoryOld categoryOld = categoryById.get(subCategoryOld.getIdCategory());
				Pair<CategoryOld, String> key = new ImmutablePair<>(categoryOld, subCategoryOld.getId());
				this.subCategoriesByIdAndCategory.put(key, subCategoryOld);
			}
		}
	}

}
