package grisbiweb.server.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import grisbiweb.server.xml.XmlWriter;
import grisbiweb.server.xml.model.AccountXml;
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
        xml.setBIC(factory.manufacturePojo(String.class));
        
        String string = xmlWriter.createXmlStringFrom(xml);

        assertThat(string)
                .isEqualTo("    <Bank Nb=\"" + xml.getNb() + "\" Na=\"" + xml.getNa() + "\" Co=\"" + xml.getCo()
                        + "\" BIC=\"" + xml.getBIC() + "\" Adr=\"" + xml.getAdr() + "\" Tel=\"" + xml.getTel()
                        + "\" Mail=\"" + xml.getMail() + "\" Web=\"" + xml.getWeb() + "\" Nac=\"" + xml.getNac()
                        + "\" Faxc=\"" + xml.getFaxc() + "\" Telc=\"" + xml.getTelc() + "\" Mailc=\"" + xml.getMailc()
                        + "\" Rem=\"" + xml.getRem() + "\" />");
    }
    
    @Test
    public void should_create_xml_string_from_account_work(){
        AccountXml xml = factory.manufacturePojo(AccountXml.class);
        //TODO : the xsd is incomplete for this object
        String string = xmlWriter.createXmlStringFrom(xml);

        assertThat(string)
                .isEqualTo("<Account\n  Name=\"Compte Monsieur\"\n  Id=\"(null)\"\n  Number=\"1\"\n  Owner=\"G.L. Inuit\"\n  Kind=\"0\"\n  Currency=\"1\"\n  Path_icon=\"(null)\"\n  Bank=\"-1\"\n  Bank_branch_code=\"Près de chez moi\"\n  Bank_account_number=\"1122334455\"\n  Key=\"(null)\"\n  Bank_account_IBAN=\"(null)\"\n  Initial_balance=\"0.00\"\n  Minimum_wanted_balance=\"0.00\"\n  Minimum_authorised_balance=\"0.00\"\n  Closed_account=\"0\"\n  Show_marked=\"1\"\n  Show_archives_lines=\"1\"\n  Lines_per_transaction=\"2\"\n  Comment=\"(null)\"\n  Owner_address=\"10 Rue du Groenland&amp;#xA;99999 Saquaille\"\n  Default_debit_method=\"3\"\n  Default_credit_method=\"2\"\n  Sort_by_method=\"0\"\n  Neutrals_inside_method=\"0\"\n  Sort_order=\"1/2/3/4/5\"\n  Ascending_sort=\"0\"\n  Column_sort=\"1\"\n  Sorting_kind_column=\"18-1-3-13-5-6-0\"\n  Form_columns_number=\"5\"\n  Form_lines_number=\"3\"\n  Form_organization=\"1-6-2-3-13-0-4-7-11-0-14-0-12-0-0-18-0-0-0-0-0-0-0-0\"\n  Form_columns_width=\"12-25-18-21-21-0\"\n  Bet_use_budget=\"1\"\n  Bet_credit_card=\"0\"\n  Bet_start_date=\"02/01/2013\"\n  Bet_months=\"3\"\n  Bet_UT=\"0\"\n  Bet_auto_inc_month=\"0\"\n  Bet_select_transaction_label=\"0\"\n  Bet_select_scheduled_label=\"0\"\n  Bet_select_futur_label=\"0\"\n  Bet_SD=\"0\"\n  Bet_Fi=\"0\" />");
    }

}
