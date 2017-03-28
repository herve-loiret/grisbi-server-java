package grisbiweb.server.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.google.common.annotations.VisibleForTesting;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import grisbiweb.server.exception.GrisbiFileException;
import grisbiweb.server.mapper.TransactionMapper;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.xml.model.AccountXml;
import grisbiweb.server.xml.model.BankXml;
import grisbiweb.server.xml.model.CategoryXml;
import grisbiweb.server.xml.model.PartyXml;
import grisbiweb.server.xml.model.SubCategoryXml;
import grisbiweb.server.xml.model.TransactionXml;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class XmlWriter implements InitializingBean {

	@Value(value = "classpath:template")
	private Resource template;

	@Autowired
	private XmlFileLocator xmlFileLocator;

	@Autowired
	private XmlReader xmlReader;

	@Autowired
	private TransactionMapper transactionMapper;

	@Autowired
	private XmlRepository xmlRepository;

	private Configuration configuration;

	@SneakyThrows
	public void updateParty(PartyXml partyXml) {
		log.debug("updateParty {}", partyXml);
		File tempFile = createTempFile();
		String partyString = createXmlStringFrom(partyXml);
		String trigerString = partyString.substring(0, 19);
		try (Stream<String> input = Files.lines(xmlFileLocator.getGrisbiFile().toPath());
				PrintWriter output = new PrintWriter(tempFile, "UTF-8")) {
			input.map(s -> s.startsWith(trigerString) ? partyString : s).forEachOrdered(output::println);
		}
		updateGrisbiFile(tempFile);
	}

	private File createTempFile() {
		try {
			File tempFile = File.createTempFile("grisbi-temp-file", ".tmp");
			log.debug("temp file created : {}", tempFile.getAbsolutePath());
			return tempFile;
		} catch (IOException e) {
			throw new GrisbiFileException("error while trying to create a temp file", e);
		}
	}

	private void updateGrisbiFile(File source) {
		log.debug("updateGrisbiFile {}", source.getAbsolutePath());
		xmlReader.declareModified();
		File grisbiFile = xmlFileLocator.getGrisbiFile();
		File archiveFile = new File(grisbiFile + String.valueOf(new Date().getTime()));
		if (!grisbiFile.renameTo(archiveFile)) {
			throw new GrisbiFileException("can't rename the grisbi file");
		}
		try {
			Files.copy(source.toPath(), grisbiFile.toPath());
		} catch (IOException e) {
			log.error("Error while trying to replace the generated temp grisbi file by the new one", e);
		}
	}

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

	@VisibleForTesting
	@Deprecated
	protected int findLineOfLastTransaction() throws FileNotFoundException, IOException {

		File file = xmlFileLocator.getGrisbiFile();

		boolean alreadyOneTransaction = false;
		int allLines = 0;
		int lastLine = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				allLines++;
				if (line.contains("<Payment") && !alreadyOneTransaction) {
					lastLine = allLines;
				}
				if (line.contains("<Transaction")) {
					alreadyOneTransaction = true;
					lastLine = allLines;
				}
			}
		}

		return ++lastLine;
	}

	@Deprecated
	private void insertStringInFile(File file, int lineNumber, String line) throws IOException {
		// temp file
		File outFile = new File("$$$$$$$$.tmp");

		// input
		FileInputStream fis = new FileInputStream(file);
		BufferedReader in = new BufferedReader(new InputStreamReader(fis));

		// output
		FileOutputStream fos = new FileOutputStream(outFile);
		PrintWriter out = new PrintWriter(fos);

		String thisLine = "";
		int i = 1;
		while ((thisLine = in.readLine()) != null) {
			if (i == lineNumber) {
				out.println(line);
			}
			out.println(thisLine);
			i++;
		}
		out.flush();
		out.close();
		in.close();

		file.delete();
		outFile.renameTo(file);
	}

	@Deprecated
	public void writeTransaction(Transaction transaction) {

		TransactionXml transactionXml = transactionMapper.transactionToTransactionXml(transaction);
		String line = this.createXmlStringFrom(transactionXml);

		try {
			int lineNumber = findLineOfLastTransaction();
			synchronized (this) {
				insertStringInFile(xmlFileLocator.getGrisbiFile(), lineNumber, line);
			}
		} catch (IOException e) {
			log.error("Error while trying to insert transaction in grisbi file", e);
		}

	}

	@SneakyThrows
	public void deleteParty(PartyXml partyXml) {
		log.debug("deleteParty {}", partyXml);
		File tempFile = createTempFile();
		String partyString = createXmlStringFrom(partyXml);
		String trigerString = partyString.substring(0, 19);
		try (Stream<String> input = Files.lines(xmlFileLocator.getGrisbiFile().toPath());
				PrintWriter output = new PrintWriter(tempFile, "UTF-8")) {
			input.filter(s -> !s.startsWith(trigerString))
					.forEachOrdered(output::println);
		}
		updateGrisbiFile(tempFile);
	}

	@SneakyThrows
	public PartyXml createParty(PartyXml partyXml) {
		log.debug("createParty {}", partyXml);
		partyXml.setNb(String.valueOf(xmlRepository.findNextPartyId()));

		String partyString = createXmlStringFrom(partyXml);
		File tempFile = this.createTempFile();
		String trigerString = partyString.substring(0, 12);
		List<String> lines = this.readAllLines();

		boolean transactionFound = false;
		boolean transactionInserted = false;
		try (PrintWriter output = new PrintWriter(tempFile, "UTF-8")) {
			for (String line : lines) {
				System.out.println(line);
				// FIXME : in v1, we assume there is already at least one
				// party in the grisbi file
				if (!transactionInserted) {
					if (line.startsWith(trigerString)) {
						transactionFound = true;
					} else if (transactionFound) {
						transactionInserted = true;
						output.println(partyString);
					}
				}
				output.println(line);
			}
		}
		updateGrisbiFile(tempFile);
		return partyXml;
	}

	@SneakyThrows
	public TransactionXml createTransaction(TransactionXml transactionXml) {
		log.debug("createTransaction {}", transactionXml);
		String transactionXmlString = this.createXmlStringFrom(transactionXml);
		
		return transactionXml;
	}
	

	public List<String> readAllLines() {
		try {
			return Files.readAllLines(xmlFileLocator.getGrisbiFile().toPath());
		} catch (IOException e) {
			throw new GrisbiFileException("can't read grisbi xml file");
		}
	}

}
