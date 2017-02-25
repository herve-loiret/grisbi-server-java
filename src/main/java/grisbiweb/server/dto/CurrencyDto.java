package grisbiweb.server.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class CurrencyDto {

    private Long id;
    private String name;
    private String sign;

}
