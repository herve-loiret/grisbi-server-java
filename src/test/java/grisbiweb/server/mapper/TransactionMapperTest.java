package grisbiweb.server.mapper;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import grisbiweb.server.model.Transaction;
import grisbiweb.server.utils.DateUtils;
import grisbiweb.server.xml.model.TransactionXml;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class TransactionMapperTest {

    @Test
    public void should_transaction_xml_to_transaction_work() {
        TransactionMapper mapper = Mappers.getMapper(TransactionMapper.class);
        PodamFactory podam = new PodamFactoryImpl();
        TransactionXml transactionXml = podam.manufacturePojo(TransactionXml.class);
        transactionXml.setNb("123");
        transactionXml.setAm("10.00");
        transactionXml.setDt("11/23/2017");
        transactionXml.setDv("10/22/2017");
        transactionXml.setExr("0");
        transactionXml.setAu("1");

        Transaction transaction = mapper.transactionXmlToTransaction(transactionXml);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(transaction.getAccountId()).as("accountId").isEqualTo(transactionXml.getAc());
        softly.assertThat(transaction.getAccountingDocument()).as("getAccountingDocument")
                .isEqualTo(transactionXml.getVo());
        softly.assertThat(transaction.getBankReferences()).as("getBankReferences").isEqualTo(transactionXml.getBa());
        softly.assertThat(transaction.getBreakdown()).as("getBreakdown").isEqualTo(transactionXml.getBr());
        softly.assertThat(transaction.getBudgetImputationId()).as("getBudgetImputationId")
                .isEqualTo(transactionXml.getBu());
        softly.assertThat(transaction.getBudgetSubImputationId()).as("getBudgetSubImputationId")
                .isEqualTo(transactionXml.getSbu());
        softly.assertThat(transaction.getCategoryId()).as("getCategoryId").isEqualTo(transactionXml.getCa());
        softly.assertThat(transaction.getCurrencyId()).as("getCurrencyId").isEqualTo(transactionXml.getCu());
        softly.assertThat(transaction.getDate()).as("getDate")
                .isEqualTo(DateUtils.parseEnglishDate(transactionXml.getDt()));
        softly.assertThat(transaction.getExchange()).as("getExchange").isEqualTo(transactionXml.getExb().equals("1")); // PODAM
        softly.assertThat(transaction.getForeignTransactionId()).as("getForeignTransactionId")
                .isEqualTo(transactionXml.getTrt());
        softly.assertThat(transaction.getId()).isEqualTo(Long.valueOf(transactionXml.getNb())).as("getId");
        softly.assertThat(transaction.getId()).as("getId").isEqualTo(Long.valueOf(transactionXml.getNb()));
        softly.assertThat(transaction.getNotes()).isEqualTo(transactionXml.getNo()).as("getNotes");
        softly.assertThat(transaction.getOfxId()).as("getOfxId").isEqualTo(transactionXml.getId());
        softly.assertThat(transaction.getPaiementMethodContent()).as("paiementMethodContent")
                .isEqualTo(transactionXml.getPc());
        softly.assertThat(transaction.getPaiementMethodId()).as("getPaiementMethodId")
                .isEqualTo(transactionXml.getPn());
        softly.assertThat(transaction.getPartyId()).as("getPartyId").isEqualTo(transactionXml.getPa());
        softly.assertThat(transaction.getSubCategoryId()).as("getSubCategoryId").isEqualTo(transactionXml.getSca());
        softly.assertThat(transaction.getTransactionParentId()).as("getTransactionParentId")
                .isEqualTo(transactionXml.getMo());
        softly.assertThat(transaction.getTransactionStatusId()).as("getTransactionStatusId")
                .isEqualTo(transactionXml.getMa());
        softly.assertThat(transaction.getValueDate()).as("getValueDate")
                .isEqualTo(DateUtils.parseEnglishDate(transactionXml.getDv()));
        softly.assertThat(transaction.isATransfer()).as("isATransfer")
                .isEqualTo(!"0".equals(transaction.getForeignTransactionId()));
        softly.assertThat(transaction.isChildTransaction()).as("isChildTransaction")
                .isEqualTo(!"0".equals(transaction.getTransactionParentId()));
        softly.assertThat(transaction.isDebit()).as("isDebit").isEqualTo(transaction.getAmount().doubleValue() < 0);
        softly.assertThat(transaction.isTransactionArchive()).as("isTransactionArchive")
                .isEqualTo("3".equals(transactionXml.getMa()));
        softly.assertThat(transaction.isTransactionPointe()).as("isTransactionPointe")
                .isEqualTo("1".equals(transactionXml.getMa()));
        softly.assertThat(transaction.isTransactionPointeOuArchive()).as("isTransactionPointe")
                .isEqualTo(transaction.isTransactionPointe() || transaction.isTransactionArchive());
        softly.assertThat(transaction.getArchiveNumber()).as("archiveNumber").isEqualTo(transactionXml.getAr());
        softly.assertThat(transaction.getAutomatic()).as("automatic").isTrue();
        softly.assertThat(transaction.getReconcileNumber()).as("reconcileNumber").isEqualTo(transactionXml.getRe());
        softly.assertThat(transaction.getFinancialYearNumber()).as("financialYearNumber")
                .isEqualTo(transactionXml.getFi());
        softly.assertAll();
    }
}
