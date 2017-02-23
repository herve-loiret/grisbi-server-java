package grisbiweb.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.rest.mapper.PartyMapper;
import grisbiweb.server.rest.model.response.PartyResponse;
import grisbiweb.server.service.GrisbiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/parties")
@Api(value = "/parties", description = "Operations about parties")
public class PartyController {

    @Autowired
    private GrisbiService grisbiService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all parties", response = PartyResponse.class, responseContainer = "List")
    public List<PartyResponse> getParties() {
        return PartyMapper.mapParties(grisbiService.getParties());
    }
}
