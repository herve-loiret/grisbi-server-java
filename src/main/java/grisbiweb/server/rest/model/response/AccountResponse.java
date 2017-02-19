package grisbiweb.server.rest.model.response;

import grisbiweb.server.model.Account.AccountType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AccountResponse {

	@ApiModelProperty(value = "id of the account")
	private Long id;

	@ApiModelProperty(value = "name of the account")
	private String name;

	@ApiModelProperty(value = "id of the currency used in this account")
	private AccountType typeAccount;

	@ApiModelProperty(value = "id of the currency used in this account")
	private String currencyId;

	public String getCurrencyId() {
		return currencyId;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public AccountType getTypeAccount() {
		return typeAccount;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTypeAccount(AccountType typeAccount) {
		this.typeAccount = typeAccount;
	}

}
