package grisbiweb.server.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class Transaction {

    private String accountId;
    private String accountingDocument;
    private BigDecimal amount;
    private String archiveNumber;
    private String bankReferences;
    private String breakdown;
    private String budgetImputationId;
    private String budgetSubImputationId;
    private String categoryId;
    private String currencyId;
    private Date date;
    private Boolean exchange;
    private BigDecimal exchangeFees;
    private BigDecimal exchangeRate;
    private String foreignTransactionId;
    private Long id;
    private String ofxId;
    private String paiementMethodId;
    private String paiementMethodContent;
    private String partyId;
    private String subCategoryId;
    private String transactionParentId;
    private String transactionStatusId;
    private String notes;
    private Date valueDate;
    private Boolean automatic;
    private String reconcileNumber;
    private String financialYearNumber;

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
        return "3".equals(this.getTransactionStatusId());
    }

    public boolean isTransactionPointe() {
        return "1".equals(this.getTransactionStatusId());
    }

    public boolean isTransactionPointeOuArchive() {
        return this.isTransactionPointe() || this.isTransactionArchive();
    }

}
