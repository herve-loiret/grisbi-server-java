package grisbiweb.server.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.mapper.CategoryMapper;
import grisbiweb.server.model.Category;
import grisbiweb.server.model.SubCategoryOld;
import grisbiweb.server.xml.GrisbiXmlManager;
import grisbiweb.server.xml.model.CategoryXml;
import grisbiweb.server.xml.model.SubCategoryXml;

@Service
public class CategoryService implements InitializingBean {

    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public void afterPropertiesSet() {
        for (Object object : GrisbiXmlManager.INSTANCE.loadGrisbi().getGroupCategories()) {
            if (object instanceof CategoryXml) {
                Category categoryOld = categoryMapper.categoryXmlToCategory((CategoryXml) object);
                this.categoryById.put(categoryOld.getId(), categoryOld);
            } else {
                SubCategoryOld subCategoryOld = new SubCategoryOld((SubCategoryXml) object);
                Category categoryOld = categoryById.get(subCategoryOld.getIdCategory());
                Pair<Category, String> key = new ImmutablePair<>(categoryOld, subCategoryOld.getId());
                this.subCategoriesByIdAndCategory.put(key, subCategoryOld);
            }
        }
    }
    
    private Map<String, Category> categoryById = new HashMap<>();
    private Map<Pair<Category, String>, SubCategoryOld> subCategoriesByIdAndCategory = new HashMap<>();

    public Map<String, Category> getCategoryById() {
        return categoryById;
    }

    public Category getCategoryById(String categoryId) {
        return this.categoryById.get(categoryId);
    }

    public Map<Pair<Category, String>, SubCategoryOld> getSubCategoriesByIdAndCategory() {
        return subCategoriesByIdAndCategory;
    }

    public SubCategoryOld getSubCategoryByIds(String categoryId, String subCategoryId) {
        Category categoryOld = this.getCategoryById(categoryId);
        Pair<Category, String> key = new ImmutablePair<>(categoryOld, subCategoryId);
        SubCategoryOld subCategoryOld = this.subCategoriesByIdAndCategory.get(key);
        return subCategoryOld;
    }

}
