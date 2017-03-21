package grisbiweb.server.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class CurrencyDto {

	private Long id;
	private String name;
	private String sign;

}
