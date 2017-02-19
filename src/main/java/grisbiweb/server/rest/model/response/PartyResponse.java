package grisbiweb.server.rest.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class PartyResponse {

    @ApiModelProperty(value = "id of the party", required = true)
    private Long id;

    @ApiModelProperty(value = "name of the party", required = true)
    private String name;

}
