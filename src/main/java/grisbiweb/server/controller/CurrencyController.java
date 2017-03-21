package grisbiweb.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.dto.CurrencyDto;
import grisbiweb.server.mapper.CurrencyMapper;
import grisbiweb.server.service.GrisbiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/currencies", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/currencies", description = "Operations about currencies")
public class CurrencyController {

	@Autowired
	private GrisbiService grisbiService;

	@Autowired
	private CurrencyMapper currencyMapper;

	@GetMapping
	@ApiOperation(value = "get all currencies", response = CurrencyDto.class, responseContainer = "List")
	public List<CurrencyDto> getCurrencies() {
		return currencyMapper.currencyXmlToCurrencyResponse(grisbiService.getCurrencies());
	}
}
