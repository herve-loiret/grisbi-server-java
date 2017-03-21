package grisbiweb.server.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import grisbiweb.server.dto.CategoryDto;
import grisbiweb.server.model.Category;
import grisbiweb.server.xml.model.CategoryXml;

@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper {

	@Mapping(source = "na", target = "name")
	@Mapping(source = "nb", target = "id")
	public Category categoryXmlToCategory(CategoryXml categoryXml);

	public List<CategoryDto> categoriesToCategoryResponses(List<Category> categories);

	@Mapping(source = "id", target = "idCategory")
	@Mapping(source = "name", target = "nameCategory")
	public CategoryDto categoryToCategoryResponse(Category category);
}
