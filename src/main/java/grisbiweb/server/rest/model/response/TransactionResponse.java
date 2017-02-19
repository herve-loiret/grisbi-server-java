package grisbiweb.server.rest.model.response;

import io.swagger.annotations.ApiModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel
public class TransactionResponse {

	private Long id;
	private Date date;
	private String party;
	private Double debit;
	private Double credit;
	private Double solde;
	private String category;
	private String currencyId;
	private String pr;
	private List<TransactionResponse> subTransactions = new ArrayList<>();

	public String getCategory() {
		return category;
	}

	public Double getCredit() {
		return credit;
	}

	public String getCreditUI() {
		return credit == null ? "" : credit + " €";
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public Date getDate() {
		return date;
	}

	public String getDateUI() {

		if (date == null) {
			return null;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = simpleDateFormat.format(date);
		return dateStr;
	}

	public Double getDebit() {
		return debit;
	}

	public Long getId() {
		return id;
	}

	public String getParty() {
		return party;
	}

	public String getPr() {
		return pr;
	}

	public Double getSolde() {
		return solde;
	}

	public String getSoldeUI() {
		return solde + " €";
	}

	public List<TransactionResponse> getSubTransactions() {
		return subTransactions;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public void setPr(String pr) {
		this.pr = pr;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "TransactionResponse [id=" + id + ", date=" + date + ", party=" + party + ", debit="
				+ debit + ", credit=" + credit + ", solde=" + solde + ", category=" + category
				+ ", currencyId=" + currencyId + ", pr=" + pr + ", subTransactions="
				+ subTransactions + "]";
	}

}
