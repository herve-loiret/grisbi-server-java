package grisbiweb.server.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberUtils {

	public static String formatBigDecimal(BigDecimal bigDecimal) {
		return bigDecimal.setScale(2).toPlainString();
	}

	public static BigDecimal parseNumber(String stringNumber) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		String pattern = "###.###";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
		BigDecimal bigDecimal = null;
		try {
			bigDecimal = (BigDecimal) decimalFormat.parse(stringNumber);
		} catch (ParseException e) {
			log.error("error while trying to parsea number", e);
		}
		return bigDecimal;
	}
}
