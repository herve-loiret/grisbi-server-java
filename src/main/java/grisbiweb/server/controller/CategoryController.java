package grisbiweb.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.rest.mapper.CategoryResponseBuilder;
import grisbiweb.server.rest.model.response.CategoryResponse;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    private CategoryResponseBuilder categoryResponseBuilder;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all categories", response = CategoryResponse.class, responseContainer = "List")
    public List<CategoryResponse> getCategoriesUI() {
        return categoryResponseBuilder.getCategoriesUI();
    }
}
