package grisbiweb.server.rest.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TransactionRequest {

	@ApiModelProperty(value = "id of the account", required = true)
	private String accountId;

	@ApiModelProperty(value = "date of the transaction", required = true)
	private String date;

	@ApiModelProperty(value = "category id")
	private String categoryId;

	@ApiModelProperty(value = "subcategory id")
	private String subCategoryId;

	@ApiModelProperty(value = "party id")
	private String partyId;

	@ApiModelProperty(value = "credit - either credit or debit are mandatory")
	private String debit;

	@ApiModelProperty(value = "debit - either credit or debit are mandatory")
	private String credit;

	public String getAccountId() {
		return accountId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public String getCredit() {
		return credit;
	}

	public String getDate() {
		return date;
	}

	public String getDebit() {
		return debit;
	}

	public String getPartyId() {
		return partyId;
	}

	public String getSubCategoryId() {
		return subCategoryId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

}
