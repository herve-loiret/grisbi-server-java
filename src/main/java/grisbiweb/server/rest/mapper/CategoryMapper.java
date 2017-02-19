package grisbiweb.server.rest.mapper;

import grisbiweb.server.model.Category;
import grisbiweb.server.model.SubCategory;
import grisbiweb.server.rest.model.response.CategoryResponse;
import grisbiweb.server.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

	private static CategoryService categoryManager = CategoryService.INSTANCE;

	public static List<CategoryResponse> getCategoriesUI() {
		List<CategoryResponse> categoriesUI = new ArrayList<>();
		// TODO à optimiser...
		for (Category category : categoryManager.getCategoryById().values()) {
			CategoryResponse categoryUI = new CategoryResponse();
			categoryUI.setIdCategory(category.getidLong());
			categoryUI.setNameCategory(category.getName());
			categoriesUI.add(categoryUI);
			for (SubCategory subCategory : categoryManager.getSubCategoriesByIdAndCategory()
					.values()) {
				if (subCategory.getIdCategory().equals(category.getId())) {
					CategoryResponse subCategoryUI = new CategoryResponse();
					
					subCategoryUI.setIdCategory(categoryUI.getIdCategory());
					subCategoryUI.setNameCategory(categoryUI.getNameCategory());
					
					subCategoryUI.setIdSubCategory(subCategory.getIdLong());
					subCategoryUI.setNameSubCategory(subCategory.getName());
					categoriesUI.add(subCategoryUI);
				}
			}
		}
		return categoriesUI;
	}
}
