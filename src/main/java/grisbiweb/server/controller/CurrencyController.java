package grisbiweb.server.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.rest.mapper.CurrencyMapper;
import grisbiweb.server.rest.model.response.CurrencyResponse;
import grisbiweb.server.service.GrisbiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/currencies")
@Api(value = "/currencies", description = "Operations about currencies")
public class CurrencyController {

    private GrisbiService grisbiService = GrisbiService.INSTANCE;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all currencies", response = CurrencyResponse.class, responseContainer = "List")
    public List<CurrencyResponse> getCurrencies()  {
        return CurrencyMapper.mapCurrencies(grisbiService.getCurrencies());
    }
}
