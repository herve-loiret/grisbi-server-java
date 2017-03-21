package grisbiweb.server.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.dto.CategoryDto;
import grisbiweb.server.model.Category;
import grisbiweb.server.model.SubCategory;
import grisbiweb.server.service.CategoryService;

@Service
public class CategoryResponseBuilder {

	@Autowired
	private CategoryService categoryService;

	public List<CategoryDto> getCategoriesUI() {
		List<CategoryDto> categoriesUI = new ArrayList<>();
		// TODO à optimiser...
		for (Category category : categoryService.getCategoryById().values()) {
			CategoryDto categoryUI = new CategoryDto();
			categoryUI.setIdCategory(category.getIdLong());
			categoryUI.setNameCategory(category.getName());
			categoriesUI.add(categoryUI);
			for (SubCategory subCategory : categoryService.getSubCategoriesByIdAndCategory().values()) {
				if (subCategory.getIdCategory().equals(category.getId())) {
					CategoryDto subCategoryUI = new CategoryDto();

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
