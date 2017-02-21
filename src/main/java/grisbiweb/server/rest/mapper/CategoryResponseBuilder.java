package grisbiweb.server.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.model.Category;
import grisbiweb.server.model.SubCategoryOld;
import grisbiweb.server.rest.model.response.CategoryResponse;
import grisbiweb.server.service.CategoryService;

@Service
public class CategoryResponseBuilder {

    @Autowired
    private CategoryService categoryService;

    public List<CategoryResponse> getCategoriesUI() {
        List<CategoryResponse> categoriesUI = new ArrayList<>();
        // TODO à optimiser...
        for (Category category : categoryService.getCategoryById().values()) {
            CategoryResponse categoryUI = new CategoryResponse();
            categoryUI.setIdCategory(category.getIdLong());
            categoryUI.setNameCategory(category.getName());
            categoriesUI.add(categoryUI);
            for (SubCategoryOld subCategory : categoryService.getSubCategoriesByIdAndCategory().values()) {
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
