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

import grisbiweb.server.model.TransactionOld;
import grisbiweb.server.utils.FileUtils;
import grisbiweb.server.xml.model.TransactionXml;

public enum XmlWriter {

	INSTANCE;

	private StringBuilder createValue(String value) {
		StringBuilder result = new StringBuilder("");
		result.append("\"");
		result.append(value);
		result.append("\"");
		return result;
	}

	private String createXmlElement(TransactionXml transactionXml) {
		StringBuilder element = new StringBuilder("	<TransactionOld");
		element.append(" Ac=" + createValue(transactionXml.getAc()));
		element.append(" Nb=" + createValue(transactionXml.getNb()));
		element.append(" Id=" + createValue(transactionXml.getId()));
		element.append(" Dt=" + createValue(transactionXml.getDt()));
		element.append(" Dv=" + createValue(transactionXml.getDv()));
		element.append(" Cu=" + createValue(transactionXml.getCu()));
		element.append(" Am=" + createValue(transactionXml.getAm()));
		element.append(" Exb=" + createValue(transactionXml.getExb()));
		element.append(" Exr=" + createValue(transactionXml.getExr()));
		element.append(" Exf=" + createValue(transactionXml.getExf()));
		element.append(" Pa=" + createValue(transactionXml.getPa()));
		element.append(" Ca=" + createValue(transactionXml.getCa()));
		element.append(" Sca=" + createValue(transactionXml.getSca()));
		element.append(" Br=" + createValue(transactionXml.getBr()));
		element.append(" No=" + createValue(transactionXml.getNo()));
		element.append(" Pn=" + createValue(transactionXml.getPn()));
		element.append(" Pc=" + createValue(transactionXml.getPc()));
		element.append(" Ma=" + createValue(transactionXml.getMa()));
		element.append(" Ar=" + createValue(transactionXml.getAr()));
		element.append(" Au=" + createValue(transactionXml.getAu()));
		element.append(" Re=" + createValue(transactionXml.getRe()));
		element.append(" Fi=" + createValue(transactionXml.getFi()));
		element.append(" Bu=" + createValue(transactionXml.getBu()));
		element.append(" Sbu=" + createValue(transactionXml.getSbu()));
		element.append(" Vo=" + createValue(transactionXml.getVo()));
		element.append(" Ba=" + createValue(transactionXml.getBa()));
		element.append(" Trt=" + createValue(transactionXml.getTrt()));
		element.append(" Mo=" + createValue(transactionXml.getMo()));
		element.append("/>");
		return element.toString();
	}

	private int findLineOfLastTransaction() throws FileNotFoundException, IOException {

		File file = FileUtils.findGrisbiAccountFile();

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
				if (line.contains("<TransactionOld")) {
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
			if (i == lineNumber)
				out.println(line);
			out.println(thisLine);
			i++;
		}
		out.flush();
		out.close();
		in.close();

		file.delete();
		outFile.renameTo(file);
	}

	public synchronized void writeTransaction(TransactionOld transactionOld) {

		TransactionXml transactionXml = transactionOld.getTransactionXml();
		String line = this.createXmlElement(transactionXml);

		try {
			int lineNumber = findLineOfLastTransaction();
			insertStringInFile(FileUtils.findGrisbiAccountFile(), lineNumber, line);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
