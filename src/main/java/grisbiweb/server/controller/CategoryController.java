package grisbiweb.server.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.rest.mapper.CategoryMapper;
import grisbiweb.server.rest.model.response.CategoryResponse;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all categories", response = CategoryResponse.class, responseContainer = "List")
    public List<CategoryResponse> getCategoriesUI()  {
        return CategoryMapper.getCategoriesUI();
    }
}
