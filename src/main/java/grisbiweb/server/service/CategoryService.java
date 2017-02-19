package grisbiweb.server.service;

import grisbiweb.server.model.Category;
import grisbiweb.server.model.SubCategory;
import grisbiweb.server.xml.GrisbiXmlManager;
import grisbiweb.server.xml.model.CategoryXml;
import grisbiweb.server.xml.model.SubCategoryXml;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public enum CategoryService {

	INSTANCE;

	private Map<String, Category> categoryById = new HashMap<>();
	private Map<Pair<Category, String>, SubCategory> subCategoriesByIdAndCategory = new HashMap<>();

	private CategoryService() {
		this.reloadCategories();
	}

	public Map<String, Category> getCategoryById() {
		return categoryById;
	}

	public Category getCategoryById(String categoryId) {
		return this.categoryById.get(categoryId);
	}

	public Map<Pair<Category, String>, SubCategory> getSubCategoriesByIdAndCategory() {
		return subCategoriesByIdAndCategory;
	}

	public SubCategory getSubCategoryByIds(String categoryId, String subCategoryId) {
		Category category = this.getCategoryById(categoryId);
		Pair<Category, String> key = new ImmutablePair<>(category, subCategoryId);
		SubCategory subCategory = this.subCategoriesByIdAndCategory.get(key);
		return subCategory;
	}

	public void reloadCategories() {
		for (Object object : GrisbiXmlManager.INSTANCE.loadGrisbi().getGroupCategories()) {
			if (object instanceof CategoryXml) {
				Category category = new Category((CategoryXml) object);
				this.categoryById.put(category.getId(), category);
			} else {
				SubCategory subCategory = new SubCategory((SubCategoryXml) object);
				Category category = categoryById.get(subCategory.getIdCategory());
				Pair<Category, String> key = new ImmutablePair<>(category, subCategory.getId());
				this.subCategoriesByIdAndCategory.put(key, subCategory);
			}
		}
	}

}
