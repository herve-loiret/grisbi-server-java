package grisbiweb.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.dto.CategoryDto;
import grisbiweb.server.rest.mapper.CategoryResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryResponseBuilder categoryResponseBuilder;

    @GetMapping
    @ApiOperation(value = "get all categories", response = CategoryDto.class, responseContainer = "List")
    public List<CategoryDto> getCategoriesUI() {
        return categoryResponseBuilder.getCategoriesUI();
    }
}
