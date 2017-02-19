package grisbiweb.server.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class NumberUtils {
	
	public static String formatBigDecimal(BigDecimal bigDecimal){
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
			e.printStackTrace();
		}
		return bigDecimal;
	}
}
