package grisbiweb.server.rest.model.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class CurrencyResponse {

	private Long id;
	private String name;
	private String sign;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSign() {
		return sign;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
