package grisbiweb.server.xml;

import java.io.StringWriter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.google.common.annotations.VisibleForTesting;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import grisbiweb.server.xml.model.AccountXml;
import grisbiweb.server.xml.model.BankXml;
import grisbiweb.server.xml.model.CategoryXml;
import grisbiweb.server.xml.model.PartyXml;
import grisbiweb.server.xml.model.SubCategoryXml;
import grisbiweb.server.xml.model.TransactionXml;
import lombok.SneakyThrows;

@Service
public class ObjectToXmlMapper implements InitializingBean {

	@Value(value = "classpath:template")
	private Resource template;

	private Configuration configuration;

	@Override
	public void afterPropertiesSet() throws Exception {
		configuration = new Configuration(Configuration.VERSION_2_3_25);
		configuration.setDirectoryForTemplateLoading(template.getFile());
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	@SneakyThrows
	protected String createXmlStringFrom(PartyXml partyXml) {
		Template template = configuration.getTemplate("party.ftl");
		StringWriter stringWriter = new StringWriter();
		template.process(partyXml, stringWriter);
		return stringWriter.toString();
	}

	@SneakyThrows
	@VisibleForTesting
	protected String createXmlStringFrom(TransactionXml transactionXml) {
		Template template = configuration.getTemplate("transaction.ftl");
		StringWriter stringWriter = new StringWriter();
		template.process(transactionXml, stringWriter);
		return stringWriter.toString();
	}

	@SneakyThrows
	@VisibleForTesting
	protected String createXmlStringFrom(CategoryXml categoryXml) {
		Template template = configuration.getTemplate("category.ftl");
		StringWriter stringWriter = new StringWriter();
		template.process(categoryXml, stringWriter);
		return stringWriter.toString();
	}

	@SneakyThrows
	@VisibleForTesting
	protected String createXmlStringFrom(SubCategoryXml subCategoryXml) {
		Template template = configuration.getTemplate("subcategory.ftl");
		StringWriter stringWriter = new StringWriter();
		template.process(subCategoryXml, stringWriter);
		return stringWriter.toString();
	}

	@SneakyThrows
	@VisibleForTesting
	protected String createXmlStringFrom(AccountXml accountXml) {
		Template template = configuration.getTemplate("account.ftl");
		StringWriter stringWriter = new StringWriter();
		template.process(accountXml, stringWriter);
		return stringWriter.toString();
	}

	@SneakyThrows
	@VisibleForTesting
	protected String createXmlStringFrom(BankXml bankXml) {
		Template template = configuration.getTemplate("bank.ftl");
		StringWriter stringWriter = new StringWriter();
		template.process(bankXml, stringWriter);
		return stringWriter.toString();
	}
}
