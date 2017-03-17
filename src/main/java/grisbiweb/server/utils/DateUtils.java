package grisbiweb.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.SneakyThrows;

public class DateUtils {

    public static final String FRENCH_DATE_PATTERN = "dd/mm/yyyy";
    public static final String ENGLISH_DATE_PATTERN = "MM/dd/yyyy";

    public static SimpleDateFormat getEnglishDateFormat() {
        return new SimpleDateFormat(ENGLISH_DATE_PATTERN);
    }

    @SneakyThrows
    public static Date parseEnglishDate(String englishDate) {
        return getEnglishDateFormat().parse(englishDate);
    }

    public static SimpleDateFormat getFrenchDateFormat() {
        return new SimpleDateFormat(FRENCH_DATE_PATTERN);
    }

}
