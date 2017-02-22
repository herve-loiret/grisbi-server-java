package grisbiweb.server.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import grisbiweb.server.model.Transaction;
import grisbiweb.server.utils.DateUtils;
import grisbiweb.server.utils.NumberUtils;
import grisbiweb.server.xml.model.TransactionXml;
import lombok.SneakyThrows;

@Mapper(componentModel = "spring", uses = {})
public interface TransactionMapper {

    @Mapping(source = "ac", target = "accountId")
    @Mapping(source = "vo", target = "accountingDocument")
    @Mapping(expression = "java(mapAmount(transactionXml))", target = "amount")
    @Mapping(source = "ba", target = "bankReferences")
    @Mapping(source = "br", target = "breakdown")
    @Mapping(source = "bu", target = "budgetImputationId")
    @Mapping(source = "sbu", target = "budgetSubImputationId")
    @Mapping(source = "ca", target = "categoryId")
    @Mapping(source = "cu", target = "currencyId")
    @Mapping(expression = "java(grisbiweb.server.utils.DateUtils.parseEnglishDate(transactionXml.getDt()))", target = "date")
    @Mapping(expression = "java(transactionXml.getExb().equals(\"1\"))", target = "exchange")
    @Mapping(expression = "java(grisbiweb.server.utils.NumberUtils.parseNumber(transactionXml.getExf()))", target = "exchangeFees")
    @Mapping(expression = "java(grisbiweb.server.utils.NumberUtils.parseNumber(transactionXml.getExr()))", target = "exchangeRate")
    @Mapping(source = "trt", target = "foreignTransactionId")
    @Mapping(source = "nb", target = "id")
    @Mapping(source = "no", target = "notes")
    @Mapping(source = "pc", target = "numberCheckTransfer")
    @Mapping(source = "id", target = "ofxId")
    @Mapping(source = "pn", target = "paiementMethodId")
    @Mapping(source = "pa", target = "partyId")
    @Mapping(source = "sca", target = "subCategoryId")
    @Mapping(source = "mo", target = "transactionParentId")
    @Mapping(source = "ma", target = "transactionStatusId")
    @Mapping(expression = "java(mapValueDate(transactionXml))", target = "valueDate")
    public Transaction transactionXmlToTransaction(TransactionXml transactionXml);

    default BigDecimal mapAmount(TransactionXml transactionXml) {

        BigDecimal amount = NumberUtils.parseNumber(transactionXml.getAm());

        // TODO le if "1" est faux, il faut prendre vraiment en compte la
        // currency
        // si cette transaction est en devise étrangère
        // il faut diviser le montant de amout par exr
        BigDecimal exr = NumberUtils.parseNumber(transactionXml.getExr());
        if (!"1".equals(transactionXml.getCu()) && exr.compareTo(BigDecimal.ZERO) != 0) {
            amount = amount.divide(exr, 2, RoundingMode.HALF_EVEN);
        }

        return amount;
    }

    @SneakyThrows
    default Date mapValueDate(TransactionXml transactionXml) {
        Date date = null;
        String valueDate = transactionXml.getDv();
        if (!"(null)".equals(valueDate)) {
            date = DateUtils.getEnglishDateFormat().parse(valueDate);
        }
        return date;
    }
}
