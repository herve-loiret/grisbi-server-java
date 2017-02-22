package grisbiweb.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.SneakyThrows;

public class DateUtils {

	public static SimpleDateFormat getEnglishDateFormat() {
		return new SimpleDateFormat("MM/dd/yyyy");
	}
	
	@SneakyThrows
	public static Date parseEnglishDate(String englishDate){
	    return getEnglishDateFormat().parse(englishDate);
	}

	public static SimpleDateFormat getFrenchDateFormat() {
		return new SimpleDateFormat("dd/mm/yyyy");
	}

}
