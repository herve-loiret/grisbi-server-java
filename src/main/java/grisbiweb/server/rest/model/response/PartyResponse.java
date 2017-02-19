package grisbiweb.server.rest.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PartyResponse {

	@ApiModelProperty(value = "id of the party", required = true)
	private Long id;

	@ApiModelProperty(value = "name of the party", required = true)
	private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
