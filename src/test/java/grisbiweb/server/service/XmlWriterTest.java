package grisbiweb.server.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import grisbiweb.server.xml.XmlWriter;
import grisbiweb.server.xml.model.BankXml;
import grisbiweb.server.xml.model.CategoryXml;
import grisbiweb.server.xml.model.PartyXml;
import grisbiweb.server.xml.model.SubCategoryXml;
import grisbiweb.server.xml.model.TransactionXml;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlWriterTest {

    @Autowired
    private XmlWriter xmlWriter;

    private PodamFactory factory = new PodamFactoryImpl();

    @Test
    public void should_create_xml_string_party_work() {
        PartyXml partyXml = factory.manufacturePojo(PartyXml.class);

        String partyString = xmlWriter.createXmlStringFrom(partyXml);

        assertThat(partyString).isEqualTo("    <Party Nb=\"" + partyXml.getNb()
                + "\" Na=\"" + partyXml.getNa() + "\" Txt=\"" + partyXml.getTxt() + "\" Search=\""
                + partyXml.getSearch() + "\" />");
    }

    @Test
    public void should_create_xml_string_transaction_work() {
        TransactionXml transactionXml = factory.manufacturePojo(TransactionXml.class);

        String transactionString = xmlWriter.createXmlStringFrom(transactionXml);

        assertThat(transactionString).isEqualTo("    <Transaction Ac=\"" + transactionXml.getAc()
                + "\" Nb=\"" + transactionXml.getNb() + "\" Id=\"" + transactionXml.getId()
                + "\" Dt=\"" + transactionXml.getDt() + "\" Dv=\"" + transactionXml.getDv() + "\" Cu=\""
                + transactionXml.getCu() + "\" Am=\"" + transactionXml.getAm() + "\" Exb=\"" + transactionXml.getExb()
                + "\" Exr=\"" + transactionXml.getExr() + "\" Exf=\"" + transactionXml.getExf() + "\" Pa=\""
                + transactionXml.getPa() + "\" Ca=\"" + transactionXml.getCa() + "\" Sca=\"" + transactionXml.getSca()
                + "\" Br=\"" + transactionXml.getBr() + "\" No=\"" + transactionXml.getNo() + "\" Pn=\""
                + transactionXml.getPn() + "\" Pc=\"" + transactionXml.getPc() + "\" Ma=\"" + transactionXml.getMa()
                + "\" Ar=\"" + transactionXml.getAr() + "\" Au=\"" + transactionXml.getAu() + "\" Re=\""
                + transactionXml.getRe() + "\" Fi=\"" + transactionXml.getFi() + "\" Bu=\"" + transactionXml.getBu()
                + "\" Sbu=\"" + transactionXml.getSbu() + "\" Vo=\"" + transactionXml.getVo() + "\" Ba=\""
                + transactionXml.getBa() + "\" Trt=\"" + transactionXml.getTrt() + "\" Mo=\"" + transactionXml.getMo()
                + "\" />");
    }

    @Test
    public void should_create_xml_string_category_work() {
        CategoryXml categoryXml = factory.manufacturePojo(CategoryXml.class);

        String categoryString = xmlWriter.createXmlStringFrom(categoryXml);

        assertThat(categoryString)
                .isEqualTo("    <Category Nb=\"" + categoryXml.getNb() + "\" Na=\"" + categoryXml.getNa() + "\" Kd=\""
                        + categoryXml.getKd() + "\" />");
    }

    @Test
    public void should_create_xml_string_subcategory_work() {
        SubCategoryXml subCategoryXml = factory.manufacturePojo(SubCategoryXml.class);

        String subCategoryString = xmlWriter.createXmlStringFrom(subCategoryXml);

        assertThat(subCategoryString)
                .isEqualTo("    <Sub_category Nbc=\"" + subCategoryXml.getNbc() + "\" Nb=\"" + subCategoryXml.getNb()
                        + "\" Na=\"" + subCategoryXml.getNa() + "\" />");
    }

    @Test
    public void should_create_xml_string_bank_work() {
        BankXml xml = factory.manufacturePojo(BankXml.class);
        xml.setBIC("sdfsdf");
        
        String string = xmlWriter.createXmlStringFrom(xml);

        assertThat(string)
                .isEqualTo("    <Bank Nb=\"" + xml.getNb() + "\" Na=\"" + xml.getNa() + "\" Co=\"" + xml.getCo()
                        + "\" BIC=\"" + xml.getBIC() + "\" Adr=\"" + xml.getAdr() + "\" Tel=\"" + xml.getTel()
                        + "\" Mail=\"" + xml.getMail() + "\" Web=\"" + xml.getWeb() + "\" Nac=\"" + xml.getNac()
                        + "\" Faxc=\"" + xml.getFaxc() + "\" Telc=\"" + xml.getTelc() + "\" Mailc=\"" + xml.getMailc()
                        + "\" Rem=\"" + xml.getRem() + "\" />");
    }

}
