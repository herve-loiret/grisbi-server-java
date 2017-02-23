package grisbiweb.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import grisbiweb.server.model.SubCategory;
import grisbiweb.server.xml.model.SubCategoryXml;

@Mapper(componentModel = "spring", uses = {})
public interface SubCategoryMapper {

    @Mapping(source = "nb", target = "id")
    @Mapping(source = "nbc", target = "idCategory")
    @Mapping(source = "na", target = "name")
    SubCategory subCategoryXmlToSubCategory(SubCategoryXml subCategoryXml);

}
