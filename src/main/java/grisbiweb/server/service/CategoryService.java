package grisbiweb.server.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.mapper.CategoryMapper;
import grisbiweb.server.mapper.SubCategoryMapper;
import grisbiweb.server.model.Category;
import grisbiweb.server.model.SubCategory;
import grisbiweb.server.xml.GrisbiXmlManager;
import grisbiweb.server.xml.model.CategoryXml;
import grisbiweb.server.xml.model.SubCategoryXml;

@Service
public class CategoryService implements InitializingBean {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SubCategoryMapper subCategoryMapper;

    @Autowired
    private GrisbiXmlManager grisbiXmlManager;

    private Map<String, Category> categoryById = new HashMap<>();
    private Map<Pair<Category, String>, SubCategory> subCategoriesByIdAndCategory = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        for (Object object : grisbiXmlManager.loadGrisbi().getGroupCategories()) {
            if (object instanceof CategoryXml) {
                Category categoryOld = categoryMapper.categoryXmlToCategory((CategoryXml) object);
                this.categoryById.put(categoryOld.getId(), categoryOld);
            } else {
                SubCategory subCategoryOld = subCategoryMapper.subCategoryXmlToSubCategory((SubCategoryXml) object);
                Category categoryOld = categoryById.get(subCategoryOld.getIdCategory());
                Pair<Category, String> key = new ImmutablePair<>(categoryOld, subCategoryOld.getId());
                this.subCategoriesByIdAndCategory.put(key, subCategoryOld);
            }
        }
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
        return this.subCategoriesByIdAndCategory.get(key);
    }

}
