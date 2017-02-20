package grisbiweb.server.rest.mapper;

import grisbiweb.server.model.CategoryOld;
import grisbiweb.server.model.SubCategoryOld;
import grisbiweb.server.rest.model.response.CategoryResponse;
import grisbiweb.server.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

	private static CategoryService categoryManager = CategoryService.INSTANCE;

	public static List<CategoryResponse> getCategoriesUI() {
		List<CategoryResponse> categoriesUI = new ArrayList<>();
		// TODO à optimiser...
		for (CategoryOld categoryOld : categoryManager.getCategoryById().values()) {
			CategoryResponse categoryUI = new CategoryResponse();
			categoryUI.setIdCategory(categoryOld.getidLong());
			categoryUI.setNameCategory(categoryOld.getName());
			categoriesUI.add(categoryUI);
			for (SubCategoryOld subCategoryOld : categoryManager.getSubCategoriesByIdAndCategory()
					.values()) {
				if (subCategoryOld.getIdCategory().equals(categoryOld.getId())) {
					CategoryResponse subCategoryUI = new CategoryResponse();
					
					subCategoryUI.setIdCategory(categoryUI.getIdCategory());
					subCategoryUI.setNameCategory(categoryUI.getNameCategory());
					
					subCategoryUI.setIdSubCategory(subCategoryOld.getIdLong());
					subCategoryUI.setNameSubCategory(subCategoryOld.getName());
					categoriesUI.add(subCategoryUI);
				}
			}
		}
		return categoriesUI;
	}
}
