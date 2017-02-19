package grisbiweb.server.rest.model.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class CurrencyResponse {

    private Long id;
    private String name;
    private String sign;

}
