//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.06 at 02:36:53 PM CET 
//


package grisbiweb.server.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Form_columns_width" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="15-50-15-15-0-0" />
 *       &lt;attribute name="Form_organization" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="1-6-2-3-0-0-0-7-11-12-0-0-0-10-17-0-0-0-0-0-0-0-0-0" />
 *       &lt;attribute name="Form_lines_number" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="3" />
 *       &lt;attribute name="Form_columns_number" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="4" />
 *       &lt;attribute name="Sorting_kind_column" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="13-1-15-0-5-6-0" />
 *       &lt;attribute name="Column_sort" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="1" />
 *       &lt;attribute name="Ascending_sort" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="0" />
 *       &lt;attribute name="Sort_order" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Neutrals_inside_method" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="0" />
 *       &lt;attribute name="Sort_by_method" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="0" />
 *       &lt;attribute name="Default_credit_method" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="Default_debit_method" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="Owner_address" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="(null)" />
 *       &lt;attribute name="Comment" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="(null)" />
 *       &lt;attribute name="Lines_per_transaction" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="3" />
 *       &lt;attribute name="Show_marked" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="0" />
 *       &lt;attribute name="Closed_account" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="0" />
 *       &lt;attribute name="Minimum_authorised_balance" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="0.00" />
 *       &lt;attribute name="Minimum_wanted_balance" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="0.00" />
 *       &lt;attribute name="Initial_balance" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="Bank_account_IBAN" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="(null)" />
 *       &lt;attribute name="Key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Bank_account_number" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="Bank_branch_code" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="Bank" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="1" />
 *       &lt;attribute name="Path_icon" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="(null)" />
 *       &lt;attribute name="Currency" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="1" />
 *       &lt;attribute name="Kind" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" fixed="0" />
 *       &lt;attribute name="Owner" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Number" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="(null)" />
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "AccountOld")
public class AccountXml {

    @XmlAttribute(name = "Form_columns_width", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String formColumnsWidth;
    @XmlAttribute(name = "Form_organization", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String formOrganization;
    @XmlAttribute(name = "Form_lines_number", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String formLinesNumber;
    @XmlAttribute(name = "Form_columns_number", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String formColumnsNumber;
    @XmlAttribute(name = "Sorting_kind_column", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String sortingKindColumn;
    @XmlAttribute(name = "Column_sort", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String columnSort;
    @XmlAttribute(name = "Ascending_sort", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String ascendingSort;
    @XmlAttribute(name = "Sort_order", required = true)
    protected String sortOrder;
    @XmlAttribute(name = "Neutrals_inside_method", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String neutralsInsideMethod;
    @XmlAttribute(name = "Sort_by_method", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String sortByMethod;
    @XmlAttribute(name = "Default_credit_method", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String defaultCreditMethod;
    @XmlAttribute(name = "Default_debit_method", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String defaultDebitMethod;
    @XmlAttribute(name = "Owner_address", required = true)
    protected String ownerAddress;
    @XmlAttribute(name = "Comment", required = true)
    protected String comment;
    @XmlAttribute(name = "Lines_per_transaction", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String linesPerTransaction;
    @XmlAttribute(name = "Show_marked", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String showMarked;
    @XmlAttribute(name = "Closed_account", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String closedAccount;
    @XmlAttribute(name = "Minimum_authorised_balance", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String minimumAuthorisedBalance;
    @XmlAttribute(name = "Minimum_wanted_balance", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String minimumWantedBalance;
    @XmlAttribute(name = "Initial_balance", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String initialBalance;
    @XmlAttribute(name = "Bank_account_IBAN", required = true)
    protected String bankAccountIBAN;
    @XmlAttribute(name = "Key", required = true)
    protected String key;
    @XmlAttribute(name = "Bank_account_number", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String bankAccountNumber;
    @XmlAttribute(name = "Bank_branch_code", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String bankBranchCode;
    @XmlAttribute(name = "Bank", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String bank;
    @XmlAttribute(name = "Path_icon", required = true)
    protected String pathIcon;
    @XmlAttribute(name = "Currency", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String currency;
    @XmlAttribute(name = "Kind", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String kind;
    @XmlAttribute(name = "Owner", required = true)
    protected String owner;
    @XmlAttribute(name = "Number", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String number;
    @XmlAttribute(name = "Id", required = true)
    protected String id;
    @XmlAttribute(name = "Name", required = true)
    protected String name;

    /**
     * Gets the value of the formColumnsWidth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormColumnsWidth() {
        if (formColumnsWidth == null) {
            return "15-50-15-15-0-0";
        } else {
            return formColumnsWidth;
        }
    }

    /**
     * Sets the value of the formColumnsWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormColumnsWidth(String value) {
        this.formColumnsWidth = value;
    }

    /**
     * Gets the value of the formOrganization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormOrganization() {
        if (formOrganization == null) {
            return "1-6-2-3-0-0-0-7-11-12-0-0-0-10-17-0-0-0-0-0-0-0-0-0";
        } else {
            return formOrganization;
        }
    }

    /**
     * Sets the value of the formOrganization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormOrganization(String value) {
        this.formOrganization = value;
    }

    /**
     * Gets the value of the formLinesNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormLinesNumber() {
        if (formLinesNumber == null) {
            return "3";
        } else {
            return formLinesNumber;
        }
    }

    /**
     * Sets the value of the formLinesNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormLinesNumber(String value) {
        this.formLinesNumber = value;
    }

    /**
     * Gets the value of the formColumnsNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormColumnsNumber() {
        if (formColumnsNumber == null) {
            return "4";
        } else {
            return formColumnsNumber;
        }
    }

    /**
     * Sets the value of the formColumnsNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormColumnsNumber(String value) {
        this.formColumnsNumber = value;
    }

    /**
     * Gets the value of the sortingKindColumn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortingKindColumn() {
        if (sortingKindColumn == null) {
            return "13-1-15-0-5-6-0";
        } else {
            return sortingKindColumn;
        }
    }

    /**
     * Sets the value of the sortingKindColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortingKindColumn(String value) {
        this.sortingKindColumn = value;
    }

    /**
     * Gets the value of the columnSort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnSort() {
        if (columnSort == null) {
            return "1";
        } else {
            return columnSort;
        }
    }

    /**
     * Sets the value of the columnSort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnSort(String value) {
        this.columnSort = value;
    }

    /**
     * Gets the value of the ascendingSort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAscendingSort() {
        if (ascendingSort == null) {
            return "0";
        } else {
            return ascendingSort;
        }
    }

    /**
     * Sets the value of the ascendingSort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAscendingSort(String value) {
        this.ascendingSort = value;
    }

    /**
     * Gets the value of the sortOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the value of the sortOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortOrder(String value) {
        this.sortOrder = value;
    }

    /**
     * Gets the value of the neutralsInsideMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNeutralsInsideMethod() {
        if (neutralsInsideMethod == null) {
            return "0";
        } else {
            return neutralsInsideMethod;
        }
    }

    /**
     * Sets the value of the neutralsInsideMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNeutralsInsideMethod(String value) {
        this.neutralsInsideMethod = value;
    }

    /**
     * Gets the value of the sortByMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortByMethod() {
        if (sortByMethod == null) {
            return "0";
        } else {
            return sortByMethod;
        }
    }

    /**
     * Sets the value of the sortByMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortByMethod(String value) {
        this.sortByMethod = value;
    }

    /**
     * Gets the value of the defaultCreditMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultCreditMethod() {
        return defaultCreditMethod;
    }

    /**
     * Sets the value of the defaultCreditMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultCreditMethod(String value) {
        this.defaultCreditMethod = value;
    }

    /**
     * Gets the value of the defaultDebitMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultDebitMethod() {
        return defaultDebitMethod;
    }

    /**
     * Sets the value of the defaultDebitMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultDebitMethod(String value) {
        this.defaultDebitMethod = value;
    }

    /**
     * Gets the value of the ownerAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerAddress() {
        if (ownerAddress == null) {
            return "(null)";
        } else {
            return ownerAddress;
        }
    }

    /**
     * Sets the value of the ownerAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerAddress(String value) {
        this.ownerAddress = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        if (comment == null) {
            return "(null)";
        } else {
            return comment;
        }
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the linesPerTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinesPerTransaction() {
        if (linesPerTransaction == null) {
            return "3";
        } else {
            return linesPerTransaction;
        }
    }

    /**
     * Sets the value of the linesPerTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinesPerTransaction(String value) {
        this.linesPerTransaction = value;
    }

    /**
     * Gets the value of the showMarked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShowMarked() {
        if (showMarked == null) {
            return "0";
        } else {
            return showMarked;
        }
    }

    /**
     * Sets the value of the showMarked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShowMarked(String value) {
        this.showMarked = value;
    }

    /**
     * Gets the value of the closedAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClosedAccount() {
        if (closedAccount == null) {
            return "0";
        } else {
            return closedAccount;
        }
    }

    /**
     * Sets the value of the closedAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClosedAccount(String value) {
        this.closedAccount = value;
    }

    /**
     * Gets the value of the minimumAuthorisedBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumAuthorisedBalance() {
        if (minimumAuthorisedBalance == null) {
            return "0.00";
        } else {
            return minimumAuthorisedBalance;
        }
    }

    /**
     * Sets the value of the minimumAuthorisedBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumAuthorisedBalance(String value) {
        this.minimumAuthorisedBalance = value;
    }

    /**
     * Gets the value of the minimumWantedBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumWantedBalance() {
        if (minimumWantedBalance == null) {
            return "0.00";
        } else {
            return minimumWantedBalance;
        }
    }

    /**
     * Sets the value of the minimumWantedBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumWantedBalance(String value) {
        this.minimumWantedBalance = value;
    }

    /**
     * Gets the value of the initialBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialBalance() {
        return initialBalance;
    }

    /**
     * Sets the value of the initialBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialBalance(String value) {
        this.initialBalance = value;
    }

    /**
     * Gets the value of the bankAccountIBAN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAccountIBAN() {
        if (bankAccountIBAN == null) {
            return "(null)";
        } else {
            return bankAccountIBAN;
        }
    }

    /**
     * Sets the value of the bankAccountIBAN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAccountIBAN(String value) {
        this.bankAccountIBAN = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the bankAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    /**
     * Sets the value of the bankAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAccountNumber(String value) {
        this.bankAccountNumber = value;
    }

    /**
     * Gets the value of the bankBranchCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankBranchCode() {
        return bankBranchCode;
    }

    /**
     * Sets the value of the bankBranchCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankBranchCode(String value) {
        this.bankBranchCode = value;
    }

    /**
     * Gets the value of the bank property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBank() {
        if (bank == null) {
            return "1";
        } else {
            return bank;
        }
    }

    /**
     * Sets the value of the bank property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBank(String value) {
        this.bank = value;
    }

    /**
     * Gets the value of the pathIcon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathIcon() {
        if (pathIcon == null) {
            return "(null)";
        } else {
            return pathIcon;
        }
    }

    /**
     * Sets the value of the pathIcon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathIcon(String value) {
        this.pathIcon = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        if (currency == null) {
            return "1";
        } else {
            return currency;
        }
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the kind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKind() {
        if (kind == null) {
            return "0";
        } else {
            return kind;
        }
    }

    /**
     * Sets the value of the kind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKind(String value) {
        this.kind = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        if (id == null) {
            return "(null)";
        } else {
            return id;
        }
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
