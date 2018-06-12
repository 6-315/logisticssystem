package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNumberUtil {
	static SimpleDateFormat formatter;
	public static String getTimeNumber() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date secondDate = new Date();
		String date = formatter.format(secondDate);
		try {
			secondDate = formatter.parse(date);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return "00000000";
		}
	}
}
