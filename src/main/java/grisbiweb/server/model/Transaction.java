package grisbiweb.server.model;

import grisbiweb.server.utils.DateUtils;
import grisbiweb.server.utils.NumberUtils;
import grisbiweb.server.xml.model.TransactionXml;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;

/**
 * @author yoshi
 *
 */
public class Transaction {

	private TransactionXml transactionXml;

	public Transaction() {
		this.transactionXml = new TransactionXml();
	}

	public Transaction(TransactionXml transaction) {
		this.transactionXml = transaction;
	}

	public String getAccountId() {
		return transactionXml.getAc();
	}

	public String getAccountingDocument() {
		return this.transactionXml.getVo();
	}

	/**
	 * Prend en compte les devises étrangères
	 * 
	 * @param transaction
	 * @return
	 */
	public BigDecimal getAmount() {

		BigDecimal amount = NumberUtils.parseNumber(this.transactionXml.getAm());

		// TODO le if "1" est faux, il faut prendre vraiment en compte la currency
		// si cette transaction est en devise étrangère
		// il faut diviser le montant de amout par exr
		BigDecimal exr = this.getExchangeRate();
		if (!"1".equals(this.getCurrencyId()) && exr.compareTo(BigDecimal.ZERO) != 0) {
			amount = amount.divide(exr, 2, RoundingMode.HALF_EVEN);
		}

		return amount;
	}

	public String getBankReferences() {
		return transactionXml.getBa();
	}

	public String getBreakdown() {
		return this.transactionXml.getBr();
	}

	public String getBudgetImputationId() {
		return transactionXml.getBu();
	}

	public String getBudgetSubImputationId() {
		return transactionXml.getSbu();
	}

	public String getCategoryId() {
		return transactionXml.getCa();
	}

	public String getCurrencyId() {
		return transactionXml.getCu();
	}

	public Date getDate() {
		Date date = null;
		try {
			date = DateUtils.getEnglishDateFormat().parse(this.transactionXml.getDt());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public Boolean getExchange() {
		return this.transactionXml.getExb().equals("1");
	}

	public BigDecimal getExchangeFees() {
		return NumberUtils.parseNumber(this.transactionXml.getExf());
	}

	public BigDecimal getExchangeRate() {
		return NumberUtils.parseNumber(this.transactionXml.getExr());
	}

	public String getForeignTransactionId() {
		return transactionXml.getTrt();
	}

	public String getId() {
		return this.transactionXml.getNb();
	}

	public Long getIdLong() {
		return Long.valueOf(this.transactionXml.getNb());
	}

	public String getNotes() {
		return this.transactionXml.getNo();
	}

	public String getNumberCheckTransfer() {
		return this.transactionXml.getPc();
	}

	public String getOfxId() {
		return transactionXml.getId();
	}

	public String getPaiementMethodId() {
		return this.transactionXml.getPn();
	}

	public String getPartyId() {
		return transactionXml.getPa();
	}

	public String getSubCategoryId() {
		return transactionXml.getSca();
	}

	public String getTransactionParentId() {
		return transactionXml.getMo();
	}

	public String getTransactionStatusId() {
		return transactionXml.getMa();
	}

	public TransactionXml getTransactionXml() {
		return transactionXml;
	}

	public Date getValueDate() {
		Date date = null;
		String valueDate = this.transactionXml.getDv();
		if ("(null)".equals(valueDate)) {
			try {
				date = DateUtils.getEnglishDateFormat().parse(this.transactionXml.getDt());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	public boolean isATransfer() {
		return !"0".equals(this.getForeignTransactionId());
	}

	public boolean isChildTransaction() {
		return !"0".equals(this.getTransactionParentId());
	}

	public boolean isDebit() {
		return this.getAmount().doubleValue() < 0;
	}

	public boolean isTransactionArchive() {
		return "3".equals(transactionXml.getMa());
	}

	public boolean isTransactionPointe() {
		return "1".equals(transactionXml.getMa());
	}

	public boolean isTransactionPointeOuArchive() {
		return this.isTransactionPointe() || this.isTransactionArchive();
	}

	public void setAccountId(String accountId) {
		transactionXml.setAc(accountId);
	}

	public void setAccountingDocument(String accountingDocument) {
		if (accountingDocument == null) {
			transactionXml.setVo("(null)");
		} else {
			transactionXml.setVo(accountingDocument);
		}
	}

	public void setAmount(BigDecimal amount) {
		// TODO voir comment gerer les devises étrangères
		this.transactionXml.setAm(NumberUtils.formatBigDecimal(amount));
	}

	public void setArchiveNumber(String archiveNumber) {
		this.transactionXml.setAr(archiveNumber);
	}

	public void setAutomatic(boolean automatic) {
		if (automatic) {
			this.transactionXml.setAu("1");
		} else {
			this.transactionXml.setAu("0");
		}
	}

	public void setBankReferences(String bankReferences) {
		transactionXml.setBa(bankReferences);
	}

	public void setBreakdown(String breakdown) {
		this.transactionXml.setBr(breakdown);
	}

	public void setBudgetImputationId(String budgetImputationId) {
		this.transactionXml.setBu(budgetImputationId);
	}

	public void setBudgetSubImputationId(String budgetSubImputationId) {
		this.transactionXml.setSbu(budgetSubImputationId);
	}

	public void setCategoryId(String categoryId) {
		this.transactionXml.setCa(categoryId);
	}

	public void setCurrencyId(String currencyId) {
		transactionXml.setCu(currencyId);
	}

	public void setDate(Date date) {
		this.transactionXml.setDt(DateUtils.getEnglishDateFormat().format(date));
	}

	public void setExchange(boolean exchange) {
		if (exchange) {
			this.transactionXml.setExb("1");
		} else {
			this.transactionXml.setExb("0");
		}
	}

	public void setExchangeFees(BigDecimal exchangeFees) {
		if (exchangeFees == null) {
			this.transactionXml.setExf("0.00");
		} else {
			this.transactionXml.setExf(NumberUtils.formatBigDecimal(exchangeFees));
		}
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		if (exchangeRate == null) {
			this.transactionXml.setExr("0.00");
		} else {
			this.transactionXml.setExr(NumberUtils.formatBigDecimal(exchangeRate));
		}
	}

	public void setFinancialYearNumber(String financialYearNumber) {
		this.transactionXml.setFi(financialYearNumber);
	}

	public void setForeignTransactionId(String foreignTransactionId) {
		transactionXml.setTrt(foreignTransactionId);
	}

	public void setId(Long id){
		this.transactionXml.setNb(String.valueOf(id));
	}
	
	public void setId(String id) {
		this.transactionXml.setNb(id);
	}

	public void setNotes(String notes) {
		this.transactionXml.setNo(notes);
	}

	public void setNumberCheckTransfer(String numberCheckTransfer) {
		this.transactionXml.setPc(numberCheckTransfer);
	}

	public void setOfxId(String ofxId) {
		transactionXml.setId(ofxId);
	}

	public void setPaiementMethodContent(String paiementMethodContent) {
		this.transactionXml.setPc(paiementMethodContent);
	}

	public void setPaiementMethodId(String paiementMethodId) {
		this.transactionXml.setPn(paiementMethodId);
	}

	public void setPartyId(String partyId) {
		this.transactionXml.setPa(partyId);
	}

	public void setReconcileNumber(String reconcileNumber) {
		this.transactionXml.setRe(reconcileNumber);
	}

	public void setSubCategoryId(String subCategoryId) {
		this.transactionXml.setSca(subCategoryId);
	}

	public void setTransactionParentId(String transactionParentId) {
		transactionXml.setMo(transactionParentId);
	}

	/**
	 * (0=nothing, 1=P, 2=T, 3=R)
	 * 
	 * @param transactionStatusId
	 */
	public void setTransactionStatusId(String transactionStatusId) {
		this.transactionXml.setMa(transactionStatusId);
	}

	public void setValueDate(Date valueDate) {
		if (valueDate == null) {
			this.transactionXml.setDv("(null)");
		} else {
			String date = DateUtils.getEnglishDateFormat().format(valueDate);
			this.transactionXml.setDv(date);
		}
	}

}
