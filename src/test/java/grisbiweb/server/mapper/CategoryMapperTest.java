package grisbiweb.server.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import grisbiweb.server.dto.CategoryDto;
import grisbiweb.server.model.Category;
import grisbiweb.server.xml.model.CategoryXml;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class CategoryMapperTest {

    @Test
    public void should_mapper_map_category_xml() {
        CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
        PodamFactory podam = new PodamFactoryImpl();
        CategoryXml categoryXml = podam.manufacturePojo(CategoryXml.class);

        Category category = mapper.categoryXmlToCategory(categoryXml);

        assertThat(categoryXml.getNb()).isEqualTo(category.getId());
        assertThat(categoryXml.getNa()).isEqualTo(category.getName());
    }
    
    @Test
    public void should_category_to_categoryResponse_work() {
        CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
        PodamFactory podam = new PodamFactoryImpl();
        Category category = podam.manufacturePojo(Category.class);
        category.setId("1234");

        CategoryDto categoryDto = mapper.categoryToCategoryResponse(category);
        
        assertThat(categoryDto.getIdCategory()).isEqualTo(category.getIdLong());
        assertThat(categoryDto.getNameCategory()).isEqualTo(category.getName());
    }
}
