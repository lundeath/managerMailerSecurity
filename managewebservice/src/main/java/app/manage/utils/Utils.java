package app.manage.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean validate(final String hex) {
    Pattern pattern = Pattern.compile(hex);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
