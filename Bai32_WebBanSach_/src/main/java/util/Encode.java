package util;

import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;

public class Encode {
	public static String encodePass(String string) {
		String salt ="ZcqwFlkq";
		String result = null;
		string = salt + string;
		try {
			byte[] dataBytes = string.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
