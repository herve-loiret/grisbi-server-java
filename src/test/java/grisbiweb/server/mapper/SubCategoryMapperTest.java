package grisbiweb.server.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import grisbiweb.server.model.SubCategory;
import grisbiweb.server.xml.model.SubCategoryXml;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class SubCategoryMapperTest {

    @Test
    public void should_mapper_map_subcategory() {
        SubCategoryMapper mapper = Mappers.getMapper(SubCategoryMapper.class);
        PodamFactory podam = new PodamFactoryImpl();
        SubCategoryXml subCategoryXml = podam.manufacturePojo(SubCategoryXml.class);
        subCategoryXml.setNb("1234");

        SubCategory subCategory = mapper.subCategoryXmlToSubCategory(subCategoryXml);

        assertThat(subCategory.getId()).as("name").isEqualTo(subCategoryXml.getNb());
        assertThat(subCategory.getIdCategory()).as("name").isEqualTo(subCategoryXml.getNbc());
        assertThat(subCategory.getIdLong()).as("name").isEqualTo(Long.valueOf(subCategory.getId()));
        assertThat(subCategory.getName()).as("name").isEqualTo(subCategoryXml.getNa());
    }

}
