package grisbiweb.server.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.mapper.CategoryMapper;
import grisbiweb.server.mapper.SubCategoryMapper;
import grisbiweb.server.model.Category;
import grisbiweb.server.model.SubCategory;
import grisbiweb.server.xml.XmlReader;
import grisbiweb.server.xml.model.CategoryXml;
import grisbiweb.server.xml.model.SubCategoryXml;

@Service
public class CategoryService implements InitializingBean {

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private SubCategoryMapper subCategoryMapper;

	@Autowired
	private XmlReader xmlReader;

	private Map<String, Category> categoryById = new HashMap<>();
	private Map<Pair<Category, String>, SubCategory> subCategoriesByIdAndCategory = new HashMap<>();

	@Override
	public void afterPropertiesSet() {
		for (CategoryXml categoryXml : xmlReader.getGrisbi().getCategory()) {
			Category category = categoryMapper.categoryXmlToCategory(categoryXml);
			this.categoryById.put(category.getId(), category);
		}
		for (SubCategoryXml subCategoryXml : xmlReader.getGrisbi().getSubCategory()) {
			SubCategory subCategory = subCategoryMapper.subCategoryXmlToSubCategory(subCategoryXml);
			Category category = categoryById.get(subCategory.getIdCategory());
			Pair<Category, String> key = new ImmutablePair<>(category, subCategory.getId());
			this.subCategoriesByIdAndCategory.put(key, subCategory);
		}
	}

	public Collection<Category> getAllCategories() {
		return categoryById.values();
	}

	public Category getCategoryById(String categoryId) {
		return this.categoryById.get(categoryId);
	}

	public List<SubCategory> getSubCategoriesByCategoryId(String categoryId) {
		return subCategoriesByIdAndCategory.values().stream()
				.filter(sc -> categoryId.equals(sc.getIdCategory()))
				.collect(Collectors.toList());
	}

	public SubCategory getSubCategoryByIds(String categoryId, String subCategoryId) {
		Category category = this.getCategoryById(categoryId);
		Pair<Category, String> key = new ImmutablePair<>(category, subCategoryId);
		return this.subCategoriesByIdAndCategory.get(key);
	}

}
