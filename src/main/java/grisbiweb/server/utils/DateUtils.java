package grisbiweb.server.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.SneakyThrows;

public class DateUtils {

	public static final String ENGLISH_DATE_PATTERN = "MM/dd/yyyy";

	public static SimpleDateFormat getEnglishDateFormat() {
		return new SimpleDateFormat(ENGLISH_DATE_PATTERN);
	}

	public static DateTimeFormatter getEnlishDateTimeFormatter() {
		return DateTimeFormatter.ofPattern(ENGLISH_DATE_PATTERN);
	}

	@SneakyThrows
	public static Date parseEnglishDate(String englishDate) {
		return getEnglishDateFormat().parse(englishDate);
	}

	@SneakyThrows
	public static LocalDate parseEnglishLocalDate(String englishDate) {
		return LocalDate.parse(englishDate, getEnlishDateTimeFormatter());
	}

}
