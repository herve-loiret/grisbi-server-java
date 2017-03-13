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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import grisbiweb.server.mapper.TransactionMapper;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.service.GrisbiFileService;
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
    private GrisbiFileService grisbiFileService;

    @Autowired
    private TransactionMapper transactionMapper;

    private Configuration configuration;

    @Override
    public void afterPropertiesSet() throws Exception {
        configuration = new Configuration(Configuration.VERSION_2_3_25);
        configuration.setDirectoryForTemplateLoading(template.getFile());
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    @SneakyThrows
    public String createXmlStringFrom(PartyXml partyXml) {
        Template template = configuration.getTemplate("party.ftl");
        StringWriter stringWriter = new StringWriter();
        template.process(partyXml, stringWriter);
        return stringWriter.toString();
    }

    @SneakyThrows
    public String createXmlStringFrom(TransactionXml transactionXml) {
        Template template = configuration.getTemplate("transaction.ftl");
        StringWriter stringWriter = new StringWriter();
        template.process(transactionXml, stringWriter);
        return stringWriter.toString();
    }

    @SneakyThrows
    public String createXmlStringFrom(CategoryXml categoryXml) {
        Template template = configuration.getTemplate("category.ftl");
        StringWriter stringWriter = new StringWriter();
        template.process(categoryXml, stringWriter);
        return stringWriter.toString();
    }

    @SneakyThrows
    public String createXmlStringFrom(SubCategoryXml subCategoryXml) {
        Template template = configuration.getTemplate("subcategory.ftl");
        StringWriter stringWriter = new StringWriter();
        template.process(subCategoryXml, stringWriter);
        return stringWriter.toString();
    }

    @SneakyThrows
    public String createXmlStringFrom(AccountXml accountXml) {
        Template template = configuration.getTemplate("account.ftl");
        StringWriter stringWriter = new StringWriter();
        template.process(accountXml, stringWriter);
        return stringWriter.toString();
    }

    @SneakyThrows
    public String createXmlStringFrom(BankXml bankXml) {
        Template template = configuration.getTemplate("bank.ftl");
        StringWriter stringWriter = new StringWriter();
        template.process(bankXml, stringWriter);
        return stringWriter.toString();
    }

    private int findLineOfLastTransaction() throws FileNotFoundException, IOException {

        File file = grisbiFileService.getGrisbiFile();

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

    public void writeTransaction(Transaction transaction) {

        TransactionXml transactionXml = transactionMapper.transactionToTransactionXml(transaction);
        String line = this.createXmlStringFrom(transactionXml);

        try {
            int lineNumber = findLineOfLastTransaction();
            synchronized (this) {
                insertStringInFile(grisbiFileService.getGrisbiFile(), lineNumber, line);
            }
        } catch (IOException e) {
            log.error("Error while trying to insert transaction in grisbi file", e);
        }

    }

}
