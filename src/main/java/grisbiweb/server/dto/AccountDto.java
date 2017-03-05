package grisbiweb.server.dto;

import grisbiweb.server.model.Account.AccountType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class AccountDto {

	@ApiModelProperty(value = "id of the account")
	private Long id;

	@ApiModelProperty(value = "name of the account")
	private String name;

	@ApiModelProperty(value = "id of the currency used in this account")
	private AccountType accountType;

	@ApiModelProperty(value = "id of the currency used in this account")
	private String currencyId;

}
