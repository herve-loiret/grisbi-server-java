package grisbiweb.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class PartyDto {

    @ApiModelProperty(value = "id of the party", required = false)
    private Long id;

    @ApiModelProperty(value = "name of the party", required = true)
    private String name;

    @ApiModelProperty(value = "description of the party", required = false)
    private String description;

}
