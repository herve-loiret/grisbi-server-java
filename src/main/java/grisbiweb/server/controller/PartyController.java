package grisbiweb.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.dto.PartyDto;
import grisbiweb.server.mapper.PartyMapper;
import grisbiweb.server.service.PartyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/parties", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/parties", description = "Operations about parties")
public class PartyController {

	@Autowired
	private PartyService partyService;

	@Autowired
	private PartyMapper partyMapper;

	@GetMapping
	@ApiOperation(value = "get all parties", response = PartyDto.class, responseContainer = "List")
	public List<PartyDto> getParties() {
		return partyMapper.partyToPartyDto(partyService.getParties());
	}

	@PostMapping
	@ApiOperation(value = "create a new party", response = PartyDto.class)
	public PartyDto postParty(@RequestBody PartyDto partyDto) {
		return partyService.createParty(partyDto);
	}

	@PutMapping
	@ApiOperation(value = "update a party", response = PartyDto.class)
	public PartyDto putParty(@RequestBody PartyDto partyDto) {
		return partyService.updateParty(partyDto);
	}

	@DeleteMapping(value = "/{partyId}")
	@ApiOperation(value = "delete a party")
	public void deleteParty(@PathVariable("partyId") String partyId) {
		partyService.deleteParty(partyId);
	}
}
