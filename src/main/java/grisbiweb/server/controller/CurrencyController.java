package grisbiweb.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.mapper.CurrencyMapper;
import grisbiweb.server.rest.model.response.CurrencyResponse;
import grisbiweb.server.service.GrisbiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/currencies")
@Api(value = "/currencies", description = "Operations about currencies")
public class CurrencyController {

    @Autowired
    private GrisbiService grisbiService;

    @Autowired
    private CurrencyMapper currencyMapper;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all currencies", response = CurrencyResponse.class, responseContainer = "List")
    public List<CurrencyResponse> getCurrencies() {
        return currencyMapper.currencyXmlToCurrencyResponse(grisbiService.getCurrencies());
    }
}
