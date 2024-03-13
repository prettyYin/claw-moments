package com.moments.claw.domain.common.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class StrUtils {
    private static final String EMPTY = "";
    private static final char SEPARATOR = '_';
    private static final String START = "*";
    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");

    private StrUtils() {
    }

    public static String joinRound(Collection<?> values, String separator, String left, String right) {
        return left + StringUtils.join(values, separator) + right;
    }

    public static String humpToLine(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();

        while(matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    public static boolean matches(String str, List<String> strs) {
        if (!isEmpty(str) && !isEmpty((Collection)strs)) {
            Iterator var2 = strs.iterator();

            String testStr;
            do {
                if (!var2.hasNext()) {
                    return false;
                }

                testStr = (String)var2.next();
            } while(!matches(str, testStr));

            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    public static boolean isEmpty(String str) {
        return isNull(str) || "".equals(str.trim());
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean matches(String str, String pattern) {
        if (!isEmpty(pattern) && !isEmpty(str)) {
            pattern = pattern.replaceAll("\\s*", "");
            int beginOffset = 0;
            String remainingURI = str;

            String suffixPattern;
            do {
                int formerStarOffset = StringUtils.indexOf(pattern, "*", beginOffset);
                String prefixPattern = StringUtils.substring(pattern, beginOffset, formerStarOffset > -1 ? formerStarOffset : pattern.length());
                boolean result = remainingURI.contains(prefixPattern);
                if (formerStarOffset == -1) {
                    return result;
                }

                if (!result) {
                    return false;
                }

                if (!isEmpty(prefixPattern)) {
                    remainingURI = StringUtils.substringAfter(str, prefixPattern);
                }

                int latterStarOffset = StringUtils.indexOf(pattern, "*", formerStarOffset + 1);
                suffixPattern = StringUtils.substring(pattern, formerStarOffset + 1, latterStarOffset > -1 ? latterStarOffset : pattern.length());
                result = remainingURI.contains(suffixPattern);
                if (!result) {
                    return false;
                }

                if (!isEmpty(suffixPattern)) {
                    remainingURI = StringUtils.substringAfter(str, suffixPattern);
                }

                beginOffset = latterStarOffset + 1;
            } while(!isEmpty(suffixPattern) && !isEmpty(remainingURI));

            return true;
        } else {
            return false;
        }
    }

    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean nexteCharIsUpperCase = true;

            for(int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                boolean preCharIsUpperCase;
                if (i > 0) {
                    preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
                } else {
                    preCharIsUpperCase = false;
                }

                boolean curreCharIsUpperCase = Character.isUpperCase(c);
                if (i < str.length() - 1) {
                    nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
                }

                if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                    sb.append('_');
                } else if (i != 0 && !preCharIsUpperCase && curreCharIsUpperCase) {
                    sb.append('_');
                }

                sb.append(Character.toLowerCase(c));
            }

            return sb.toString();
        }
    }

    public static String nvl(Object o) {
        return o == null ? "" : o.toString();
    }

    public static String trim(String str, char chr) {
        int prevNum = 0;
        int nextNum = 0;

        int i;
        for(i = 0; i < str.length() && str.charAt(i) == chr; ++i) {
            ++prevNum;
        }

        for(i = str.length() - 1; i >= 0 && str.charAt(i) == chr; --i) {
            ++nextNum;
        }

        return str.substring(prevNum, str.length() - nextNum);
    }
}
