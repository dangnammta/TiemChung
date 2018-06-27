/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author namdv
 */
public class StringUtil {

    private static final HashMap<String, String> sqlTokens;
    private static Pattern sqlTokenPattern;
    public static final Map<Character, Character> vietnameseChars = new HashMap<Character, Character>();
    public static final Map<Character, Character> vietnameseCharstoUpperCase = new HashMap<Character, Character>();

    static {
        String[][] search_regex_replacement = new String[][]{
            //search string     search regex        sql replacement regex
            {"\u0000", "\\x00", "\\\\0"},
            {"'", "'", "\\\\'"},
            {"\"", "\"", "\\\\\""},
            {"\b", "\\x08", "\\\\b"},
            {"\n", "\\n", "\\\\n"},
            {"\r", "\\r", "\\\\r"},
            {"\t", "\\t", "\\\\t"},
            {"\u001A", "\\x1A", "\\\\Z"},
            {"\\", "\\\\", "\\\\\\\\"}
        };

        sqlTokens = new HashMap<String, String>();
        String patternStr = "";
        for (String[] srr : search_regex_replacement) {
            sqlTokens.put(srr[0], srr[2]);
            patternStr += (patternStr.isEmpty() ? "" : "|") + srr[1];
        }
        sqlTokenPattern = Pattern.compile('(' + patternStr + ')');
        vietnameseCharstoUpperCase.put('À', 'A');
        vietnameseCharstoUpperCase.put('Á', 'A');
        vietnameseCharstoUpperCase.put('À', 'A');
        vietnameseCharstoUpperCase.put('Ả', 'A');
        vietnameseCharstoUpperCase.put('Ã', 'A');
        vietnameseCharstoUpperCase.put('Ạ', 'A');

        vietnameseCharstoUpperCase.put('Ă', 'A');
        vietnameseCharstoUpperCase.put('Ằ', 'A');
        vietnameseCharstoUpperCase.put('Ắ', 'A');
        vietnameseCharstoUpperCase.put('Ẳ', 'A');
        vietnameseCharstoUpperCase.put('Ẵ', 'A');
        vietnameseCharstoUpperCase.put('Ặ', 'A');

        vietnameseCharstoUpperCase.put('Â', 'A');
        vietnameseCharstoUpperCase.put('Ầ', 'A');
        vietnameseCharstoUpperCase.put('Ấ', 'A');
        vietnameseCharstoUpperCase.put('Ẩ', 'A');
        vietnameseCharstoUpperCase.put('Ẫ', 'A');
        vietnameseCharstoUpperCase.put('Ậ', 'A');

        vietnameseCharstoUpperCase.put('È', 'E');
        vietnameseCharstoUpperCase.put('É', 'E');
        vietnameseCharstoUpperCase.put('Ẽ', 'E');
        vietnameseCharstoUpperCase.put('Ẻ', 'E');
        vietnameseCharstoUpperCase.put('Ẹ', 'E');

        vietnameseCharstoUpperCase.put('Ê', 'E');
        vietnameseCharstoUpperCase.put('Ề', 'E');
        vietnameseCharstoUpperCase.put('Ế', 'E');
        vietnameseCharstoUpperCase.put('Ể', 'E');
        vietnameseCharstoUpperCase.put('Ễ', 'E');
        vietnameseCharstoUpperCase.put('Ệ', 'E');

        vietnameseCharstoUpperCase.put('Ì', 'I');
        vietnameseCharstoUpperCase.put('Í', 'I');
        vietnameseCharstoUpperCase.put('Ỉ', 'I');
        vietnameseCharstoUpperCase.put('Ĩ', 'I');
        vietnameseCharstoUpperCase.put('Ị', 'I');

        vietnameseCharstoUpperCase.put('Ò', 'O');
        vietnameseCharstoUpperCase.put('Ó', 'O');
        vietnameseCharstoUpperCase.put('Ỏ', 'O');
        vietnameseCharstoUpperCase.put('Õ', 'O');
        vietnameseCharstoUpperCase.put('Ọ', 'O');

        vietnameseCharstoUpperCase.put('Ô', 'O');
        vietnameseCharstoUpperCase.put('Ồ', 'O');
        vietnameseCharstoUpperCase.put('Ố', 'O');
        vietnameseCharstoUpperCase.put('Ổ', 'O');
        vietnameseCharstoUpperCase.put('Ỗ', 'O');
        vietnameseCharstoUpperCase.put('Ộ', 'O');

        vietnameseCharstoUpperCase.put('Ơ', 'O');
        vietnameseCharstoUpperCase.put('Ờ', 'O');
        vietnameseCharstoUpperCase.put('Ớ', 'O');
        vietnameseCharstoUpperCase.put('Ở', 'O');
        vietnameseCharstoUpperCase.put('Ờ', 'O');
        vietnameseCharstoUpperCase.put('Ợ', 'O');

        vietnameseCharstoUpperCase.put('U', 'U');
        vietnameseCharstoUpperCase.put('Ù', 'U');
        vietnameseCharstoUpperCase.put('Ú', 'U');
        vietnameseCharstoUpperCase.put('Ủ', 'U');
        vietnameseCharstoUpperCase.put('Ũ', 'U');
        vietnameseCharstoUpperCase.put('Ụ', 'U');

        vietnameseCharstoUpperCase.put('Ư', 'U');
        vietnameseCharstoUpperCase.put('Ừ', 'U');
        vietnameseCharstoUpperCase.put('Ứ', 'U');
        vietnameseCharstoUpperCase.put('Ử', 'U');
        vietnameseCharstoUpperCase.put('Ữ', 'U');
        vietnameseCharstoUpperCase.put('Ự', 'U');

        vietnameseCharstoUpperCase.put('Ỳ', 'Y');
        vietnameseCharstoUpperCase.put('Ý', 'Y');
        vietnameseCharstoUpperCase.put('Ỷ', 'Y');
        vietnameseCharstoUpperCase.put('Ỹ', 'Y');
        vietnameseCharstoUpperCase.put('Ỵ', 'Y');

        vietnameseCharstoUpperCase.put('Đ', 'D');

        vietnameseChars.put('À', 'A');
        vietnameseChars.put('Á', 'A');
        vietnameseChars.put('À', 'A');
        vietnameseChars.put('Ả', 'A');
        vietnameseChars.put('Ã', 'A');
        vietnameseChars.put('Ạ', 'A');

        vietnameseChars.put('Ă', 'Ă');
        vietnameseChars.put('Ằ', 'Ă');
        vietnameseChars.put('Ắ', 'Ă');
        vietnameseChars.put('Ẳ', 'Ă');
        vietnameseChars.put('Ẵ', 'Ă');
        vietnameseChars.put('Ặ', 'Ă');

        vietnameseChars.put('Â', 'Â');
        vietnameseChars.put('Ầ', 'Â');
        vietnameseChars.put('Ấ', 'Â');
        vietnameseChars.put('Ẩ', 'Â');
        vietnameseChars.put('Ẫ', 'Â');
        vietnameseChars.put('Ậ', 'Â');

        vietnameseChars.put('È', 'E');
        vietnameseChars.put('É', 'E');
        vietnameseChars.put('Ẽ', 'E');
        vietnameseChars.put('Ẻ', 'E');
        vietnameseChars.put('Ẹ', 'E');

        vietnameseChars.put('Ê', 'Ê');
        vietnameseChars.put('Ề', 'Ê');
        vietnameseChars.put('Ế', 'Ê');
        vietnameseChars.put('Ể', 'Ê');
        vietnameseChars.put('Ễ', 'Ê');
        vietnameseChars.put('Ệ', 'Ê');

        vietnameseChars.put('Ì', 'I');
        vietnameseChars.put('Í', 'I');
        vietnameseChars.put('Ỉ', 'I');
        vietnameseChars.put('Ĩ', 'I');
        vietnameseChars.put('Ị', 'I');

        vietnameseChars.put('Ò', 'O');
        vietnameseChars.put('Ó', 'O');
        vietnameseChars.put('Ỏ', 'O');
        vietnameseChars.put('Õ', 'O');
        vietnameseChars.put('Ọ', 'O');

        vietnameseChars.put('Ô', 'Ô');
        vietnameseChars.put('Ồ', 'Ô');
        vietnameseChars.put('Ố', 'Ô');
        vietnameseChars.put('Ổ', 'Ô');
        vietnameseChars.put('Ỗ', 'Ô');
        vietnameseChars.put('Ộ', 'Ô');

        vietnameseChars.put('Ơ', 'Ơ');
        vietnameseChars.put('Ờ', 'Ơ');
        vietnameseChars.put('Ớ', 'Ơ');
        vietnameseChars.put('Ở', 'Ơ');
        vietnameseChars.put('Ờ', 'Ơ');
        vietnameseChars.put('Ợ', 'Ơ');

        vietnameseChars.put('U', 'U');
        vietnameseChars.put('Ù', 'U');
        vietnameseChars.put('Ú', 'U');
        vietnameseChars.put('Ủ', 'U');
        vietnameseChars.put('Ũ', 'U');
        vietnameseChars.put('Ụ', 'U');

        vietnameseChars.put('Ư', 'Ư');
        vietnameseChars.put('Ừ', 'Ư');
        vietnameseChars.put('Ứ', 'Ư');
        vietnameseChars.put('Ử', 'Ư');
        vietnameseChars.put('Ữ', 'Ư');
        vietnameseChars.put('Ự', 'Ư');

        vietnameseChars.put('Ỳ', 'Y');
        vietnameseChars.put('Ý', 'Y');
        vietnameseChars.put('Ỷ', 'Y');
        vietnameseChars.put('Ỹ', 'Y');
        vietnameseChars.put('Ỵ', 'Y');

        vietnameseChars.put('à', 'a');
        vietnameseChars.put('á', 'a');
        vietnameseChars.put('ả', 'a');
        vietnameseChars.put('ã', 'a');
        vietnameseChars.put('ạ', 'a');

        vietnameseChars.put('ă', 'a');
        vietnameseChars.put('ằ', 'a');
        vietnameseChars.put('ắ', 'a');
        vietnameseChars.put('ẳ', 'a');
        vietnameseChars.put('ẵ', 'a');
        vietnameseChars.put('ặ', 'a');

        vietnameseChars.put('â', 'a');
        vietnameseChars.put('ầ', 'a');
        vietnameseChars.put('ấ', 'a');
        vietnameseChars.put('ẩ', 'a');
        vietnameseChars.put('ẫ', 'a');
        vietnameseChars.put('ậ', 'a');

        vietnameseChars.put('è', 'e');
        vietnameseChars.put('é', 'e');
        vietnameseChars.put('ẻ', 'e');
        vietnameseChars.put('ẽ', 'e');
        vietnameseChars.put('ẹ', 'e');

        vietnameseChars.put('ê', 'e');
        vietnameseChars.put('ề', 'e');
        vietnameseChars.put('ế', 'e');
        vietnameseChars.put('ể', 'e');
        vietnameseChars.put('ễ', 'e');
        vietnameseChars.put('ệ', 'e');

        vietnameseChars.put('ì', 'i');
        vietnameseChars.put('í', 'i');
        vietnameseChars.put('ỉ', 'i');
        vietnameseChars.put('ĩ', 'i');
        vietnameseChars.put('ị', 'i');

        vietnameseChars.put('ò', 'o');
        vietnameseChars.put('ó', 'o');
        vietnameseChars.put('ỏ', 'o');
        vietnameseChars.put('õ', 'o');
        vietnameseChars.put('ọ', 'o');

        vietnameseChars.put('ô', 'o');
        vietnameseChars.put('ồ', 'o');
        vietnameseChars.put('ố', 'o');
        vietnameseChars.put('ổ', 'o');
        vietnameseChars.put('ỗ', 'o');
        vietnameseChars.put('ộ', 'o');

        vietnameseChars.put('ơ', 'o');
        vietnameseChars.put('ờ', 'o');
        vietnameseChars.put('ớ', 'o');
        vietnameseChars.put('ợ', 'o');
        vietnameseChars.put('ỡ', 'o');
        vietnameseChars.put('ợ', 'o');

        vietnameseChars.put('ù', 'u');
        vietnameseChars.put('ú', 'u');
        vietnameseChars.put('ủ', 'u');
        vietnameseChars.put('ũ', 'u');
        vietnameseChars.put('ụ', 'u');

        vietnameseChars.put('ư', 'u');
        vietnameseChars.put('ừ', 'u');
        vietnameseChars.put('ứ', 'u');
        vietnameseChars.put('ử', 'u');
        vietnameseChars.put('ữ', 'u');
        vietnameseChars.put('ự', 'u');

        vietnameseChars.put('ỳ', 'y');
        vietnameseChars.put('ý', 'y');
        vietnameseChars.put('ỷ', 'y');
        vietnameseChars.put('ỹ', 'y');
        vietnameseChars.put('ỵ', 'y');

        vietnameseChars.put('đ', 'd');
        vietnameseChars.put('Đ', 'd');
    }
    private static final String normalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String convertStreamToString(InputStream is) {

        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static List<String> stringToListString(String strList) {
        List<String> result = new ArrayList<String>();
        if (strList != null && strList.length() > 0) {
            String[] strArr = strList.split(",");
            if (strArr != null && strArr.length > 0) {
                for (int i = 0; i < strArr.length; i++) {
                    String str = strArr[i];
                    if (str != null && str.length() > 0) {
                        result.add(str);
                    }
                }
            }
        }
        return result;
    }

    public static String listStringToString(List<String> strList) {
        String result = "";
        for (int i = 0; i < strList.size(); i++) {
            String str = strList.get(i);
            if (str != null && str.length() > 0) {
                if (result.length() == 0) {
                    result = str;
                } else {
                    result = result + "," + str;
                }
            }
        }
        return result;
    }

    public static String removeSpace(String name) {
        String re = "";
        if (name == null) {
            return re;
        }

        name.replace(" ", "");

        return name.replace(" ", "");
    }

    public static String fromVietnamToVN(String content) {
        String newWord = "";
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (vietnameseChars.containsKey(c)) {
                c = vietnameseChars.get(c);
            }
            newWord += c;
        }
//	        newWord = newWord.toLowerCase().replaceAll(" ", "-");
        return newWord;
    }

    public static String fromVietnamToVN_LowerCase(String content) {
        String newWord = "";
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (vietnameseChars.containsKey(c)) {
                c = vietnameseChars.get(c);
            }
            newWord += c;
        }
        newWord = newWord.toLowerCase().replaceAll(" ", "-");
        return newWord;
    }

    public static String fromVietnamToVN_toUpperCase(String content) {
        String newWord = "";
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (vietnameseCharstoUpperCase.containsKey(c)) {
                c = vietnameseCharstoUpperCase.get(c);
            }
            newWord += c;
        }
//	        newWord = newWord.toLowerCase().replaceAll(" ", "-");
        return newWord;
    }

    public static String getFirstChar(String name) {
        String re = "";
        if (name == null || name.equals("")) {
            return re;
        }

        String c = String.valueOf(name.charAt(0));

        re = fromVietnamToVN(c).toUpperCase();

        if (!normalChars.contains(re)) {
            re = "#";
        }

        return re;
    }

    public static String getFirstCharNoCase(String name) {
        String re = "";
        if (name == null || name.equals("")) {
            return re;
        }

        String c = String.valueOf(name.charAt(0));

        re = fromVietnamToVN(c);

        if (!normalChars.contains(re)) {
            re = "#";
        }

        return re;
    }

    public static int compareString(String name1, String name2) {
        name1 = removeSpace(name1);
        name2 = removeSpace(name2);

        String temp1 = "";
        String first1 = getFirstCharNoCase(name1);
        if (name1.length() > 1) {
            temp1 = first1.concat(fromVietnamToVN(name1.substring(1)));
        } else {
            temp1 = first1;
        }

        String temp2 = "";
        String first2 = getFirstCharNoCase(name2);
        if (name2.length() > 1) {
            temp2 = first2.concat(fromVietnamToVN(name2.substring(1)));
        } else {
            temp2 = first2;
        }

        //System.out.println("---------->>>>>>>>>>> Compare: " + name1 + " : " + name2 + " : " + first1 + " : " + first2);
        if (first1.equals("#") && !first2.equals("#")) {
            return 1;
        }

        if (!first1.equals("#") && first2.equals("#")) {
            return -1;
        }

        int result = temp1.compareToIgnoreCase(temp2);
        //System.out.println("---------->>>>>>>>>>> Compare: " + temp1 + " : " + temp2 + " : " + result);
        return result;
    }

    public static String escape(String s) {
        if (s == null) {
            return null;
        }
        Matcher matcher = sqlTokenPattern.matcher(s);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, sqlTokens.get(matcher.group(1)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
