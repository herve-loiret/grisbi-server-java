package grisbiweb.server.xml;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class ObjectToXmlMapperTest {

	@Autowired
	private ObjectToXmlMapper objectToXmlMapperTest;

	private PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void should_create_xml_string_party_work() {
		PartyXml partyXml = factory.manufacturePojo(PartyXml.class);

		String partyString = objectToXmlMapperTest.createXmlStringFrom(partyXml);

		assertThat(partyString).isEqualTo("	<Party Nb=\"" + partyXml.getNb() + "\" Na=\"" + partyXml.getNa()
				+ "\" Txt=\"" + partyXml.getTxt() + "\" Search=\"" + partyXml.getSearch() + "\" />");
	}

	@Test
	public void should_create_xml_string_transaction_work() {
		TransactionXml transactionXml = factory.manufacturePojo(TransactionXml.class);

		String transactionString = objectToXmlMapperTest.createXmlStringFrom(transactionXml);

		assertThat(transactionString).isEqualTo("	<Transaction Ac=\"" + transactionXml.getAc() + "\" Nb=\""
				+ transactionXml.getNb() + "\" Id=\"" + transactionXml.getId() + "\" Dt=\"" + transactionXml.getDt()
				+ "\" Dv=\"" + transactionXml.getDv() + "\" Cu=\"" + transactionXml.getCu() + "\" Am=\""
				+ transactionXml.getAm() + "\" Exb=\"" + transactionXml.getExb() + "\" Exr=\"" + transactionXml.getExr()
				+ "\" Exf=\"" + transactionXml.getExf() + "\" Pa=\"" + transactionXml.getPa() + "\" Ca=\""
				+ transactionXml.getCa() + "\" Sca=\"" + transactionXml.getSca() + "\" Br=\"" + transactionXml.getBr()
				+ "\" No=\"" + transactionXml.getNo() + "\" Pn=\"" + transactionXml.getPn() + "\" Pc=\""
				+ transactionXml.getPc() + "\" Ma=\"" + transactionXml.getMa() + "\" Ar=\"" + transactionXml.getAr()
				+ "\" Au=\"" + transactionXml.getAu() + "\" Re=\"" + transactionXml.getRe() + "\" Fi=\""
				+ transactionXml.getFi() + "\" Bu=\"" + transactionXml.getBu() + "\" Sbu=\"" + transactionXml.getSbu()
				+ "\" Vo=\"" + transactionXml.getVo() + "\" Ba=\"" + transactionXml.getBa() + "\" Trt=\""
				+ transactionXml.getTrt() + "\" Mo=\"" + transactionXml.getMo() + "\" />");
	}

	@Test
	public void should_create_xml_string_category_work() {
		CategoryXml categoryXml = factory.manufacturePojo(CategoryXml.class);

		String categoryString = objectToXmlMapperTest.createXmlStringFrom(categoryXml);

		assertThat(categoryString).isEqualTo("	<Category Nb=\"" + categoryXml.getNb() + "\" Na=\""
				+ categoryXml.getNa() + "\" Kd=\"" + categoryXml.getKd() + "\" />");
	}

	@Test
	public void should_create_xml_string_subcategory_work() {
		SubCategoryXml subCategoryXml = factory.manufacturePojo(SubCategoryXml.class);

		String subCategoryString = objectToXmlMapperTest.createXmlStringFrom(subCategoryXml);

		assertThat(subCategoryString).isEqualTo("	<Sub_category Nbc=\"" + subCategoryXml.getNbc() + "\" Nb=\""
				+ subCategoryXml.getNb() + "\" Na=\"" + subCategoryXml.getNa() + "\" />");
	}

	@Test
	public void should_create_xml_string_bank_work() {
		BankXml xml = factory.manufacturePojo(BankXml.class);
		xml.setBIC(factory.manufacturePojo(String.class));

		String string = objectToXmlMapperTest.createXmlStringFrom(xml);

		assertThat(string).isEqualTo("	<Bank Nb=\"" + xml.getNb() + "\" Na=\"" + xml.getNa() + "\" Co=\"" + xml.getCo()
				+ "\" BIC=\"" + xml.getBIC() + "\" Adr=\"" + xml.getAdr() + "\" Tel=\"" + xml.getTel() + "\" Mail=\""
				+ xml.getMail() + "\" Web=\"" + xml.getWeb() + "\" Nac=\"" + xml.getNac() + "\" Faxc=\"" + xml.getFaxc()
				+ "\" Telc=\"" + xml.getTelc() + "\" Mailc=\"" + xml.getMailc() + "\" Rem=\"" + xml.getRem() + "\" />");
	}

	@Test
	public void should_create_xml_string_from_account_work() {
		AccountXml xml = factory.manufacturePojo(AccountXml.class);

		String string = objectToXmlMapperTest.createXmlStringFrom(xml);

		String expected = "	<Account\n		Name=\"" + xml.getName() + "\"\n		Id=\"" + xml.getId()
				+ "\"\n		Number=\"" + xml.getNumber() + "\"\n		Owner=\"" + xml.getOwner() + "\"\n		Kind=\""
				+ xml.getKind() + "\"\n		Currency=\"" + xml.getCurrency() + "\"\n		Path_icon=\""
				+ xml.getPathIcon() + "\"\n		Bank=\"" + xml.getBank() + "\"\n		Bank_branch_code=\""
				+ xml.getBankBranchCode() + "\"\n		Bank_account_number=\"" + xml.getBankAccountNumber()
				+ "\"\n		Key=\"" + xml.getKey() + "\"\n		Bank_account_IBAN=\"" + xml.getBankAccountIBAN()
				+ "\"\n		Initial_balance=\"" + xml.getInitialBalance() + "\"\n		Minimum_wanted_balance=\""
				+ xml.getMinimumWantedBalance() + "\"\n		Minimum_authorised_balance=\""
				+ xml.getMinimumAuthorisedBalance() + "\"\n		Closed_account=\"" + xml.getClosedAccount()
				+ "\"\n		Show_marked=\"" + xml.getShowMarked() + "\"\n		Show_archives_lines=\""
				+ xml.getShowArchivesLines() + "\"\n		Lines_per_transaction=\"" + xml.getLinesPerTransaction()
				+ "\"\n		Comment=\"" + xml.getComment() + "\"\n		Owner_address=\"" + xml.getOwnerAddress()
				+ "\"\n		Default_debit_method=\"" + xml.getDefaultDebitMethod()
				+ "\"\n		Default_credit_method=\"" + xml.getDefaultCreditMethod() + "\"\n		Sort_by_method=\""
				+ xml.getSortByMethod() + "\"\n		Neutrals_inside_method=\"" + xml.getNeutralsInsideMethod()
				+ "\"\n		Sort_order=\"" + xml.getSortOrder() + "\"\n		Ascending_sort=\"" + xml.getAscendingSort()
				+ "\"\n		Column_sort=\"" + xml.getColumnSort() + "\"\n		Sorting_kind_column=\""
				+ xml.getSortingKindColumn() + "\"\n		Form_columns_number=\"" + xml.getFormColumnsNumber()
				+ "\"\n		Form_lines_number=\"" + xml.getFormLinesNumber() + "\"\n		Form_organization=\""
				+ xml.getFormOrganization() + "\"\n		Form_columns_width=\"" + xml.getFormColumnsWidth()
				+ "\"\n		Bet_use_budget=\"" + xml.getBetUseBudget()
				+ "\"\n		Bet_credit_card=\"0\"\n		Bet_start_date=\"" + xml.getBetStartDate()
				+ "\"\n		Bet_months=\"" + xml.getBetMonths() + "\"\n		Bet_UT=\"" + xml.getBetUT()
				+ "\"\n		Bet_auto_inc_month=\"" + xml.getBetAutoIncMonth()
				+ "\"\n		Bet_select_transaction_label=\"" + xml.getBetSelectTransactionLabel()
				+ "\"\n		Bet_select_scheduled_label=\"" + xml.getBetSelectScheduledLabel()
				+ "\"\n		Bet_select_futur_label=\"" + xml.getBetSelectFuturLabel() + "\"\n		Bet_SD=\""
				+ xml.getBetSD() + "\"\n		Bet_Fi=\"" + xml.getBetFi() + "\" />";

		assertThat(string).isEqualTo(expected);
	}

}
