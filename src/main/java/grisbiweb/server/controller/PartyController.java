package grisbiweb.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.dto.PartyDto;
import grisbiweb.server.service.PartyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/parties")
@Api(value = "/parties", description = "Operations about parties")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all parties", response = PartyDto.class, responseContainer = "List")
    public List<PartyDto> getParties() {
        return partyService.getParties();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "create a new party", response = PartyDto.class)
    public PartyDto postParty(@RequestBody PartyDto partyDto) {
        return partyService.createParty(partyDto);
    }

    @RequestMapping(value = "/{partyId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete a party")
    public void deleteParty(@PathVariable("partyId") Long partyId) {
        partyService.deleteParty(partyId);
    }
}
