package grisbiweb.server.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import grisbiweb.server.model.Category;
import grisbiweb.server.rest.model.response.CategoryResponse;
import grisbiweb.server.xml.model.CategoryXml;

@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper {

    @Mapping(source = "na", target = "name")
    @Mapping(source = "nb", target = "id")
    public Category categoryXmlToCategory(CategoryXml categoryXml);

    public List<CategoryResponse> categoriesToCategoryResponses(List<Category> categories);

    @Mapping(source = "id", target = "idCategory")
    @Mapping(source = "name", target = "nameCategory")
    public CategoryResponse categoryToCategoryResponse(Category category);
}
