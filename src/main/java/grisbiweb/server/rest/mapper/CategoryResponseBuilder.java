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

	public List<CategoryDto> getCategoriesDto() {
		List<CategoryDto> categoriesDto = new ArrayList<>();
		// TODO à optimiser...
		for (Category category : categoryService.getAllCategories()) {
			CategoryDto categoryUI = new CategoryDto();
			categoryUI.setIdCategory(category.getIdLong());
			categoryUI.setNameCategory(category.getName());
			categoriesDto.add(categoryUI);
			for (SubCategory subCategory : categoryService.getSubCategoriesByCategoryId(category.getId())) {
				CategoryDto subCategoryDto = new CategoryDto();

				subCategoryDto.setIdCategory(categoryUI.getIdCategory());
				subCategoryDto.setNameCategory(categoryUI.getNameCategory());

				subCategoryDto.setIdSubCategory(subCategory.getIdLong());
				subCategoryDto.setNameSubCategory(subCategory.getName());
				categoriesDto.add(subCategoryDto);
			}
		}
		return categoriesDto;
	}
}
