package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

	public static class UserValidators{
		public static boolean validateEmail(String emailStr) {
			final Pattern VALID_EMAIL_ADDRESS_REGEX = 
				    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);	
			Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	        return matcher.find();
		}
	}
}
