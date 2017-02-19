package grisbiweb.server.utils;

import java.text.SimpleDateFormat;

public class DateUtils {

	public static SimpleDateFormat getEnglishDateFormat() {
		return new SimpleDateFormat("MM/dd/yyyy");
	}

	public static SimpleDateFormat getFrenchDateFormat() {
		return new SimpleDateFormat("dd/mm/yyyy");
	}

}
