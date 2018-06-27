/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.utils;

import baseclass.ServletBase;
import com.twmacinta.util.MD5;
import configuration.Configuration;
import crdhn.fcore.common.Config;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author namdv
 */
public class Utils {

    private static final Logger log = Logger.getLogger(Utils.class);

    public static String checkSession(String keylog, HttpServletRequest request) {
        String ret = "";
        try {
            String cookie = ServletBase.getCookieValue(request, Configuration.cookieName);
            if (cookie == null || cookie.isEmpty()) {
                cookie = keylog;
            }
            long currentTime = System.currentTimeMillis();
            Long timeLogin = Configuration.mapTimeSession.get(cookie);
            String userName = Configuration.mapSession.get(cookie);
            System.out.println("getcookie " + Configuration.cookieName + "=========" + cookie + "===currentTime=" + currentTime + "===timeloginOld=" + timeLogin + "====userName===" + userName);
            if (timeLogin != null && timeLogin > 0 && (currentTime - timeLogin) < 86400000 && userName != null && !userName.isEmpty()) {
                ret = userName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static long parserDateTimeToLong(String dateTime) {
        String[] data = dateTime.trim().split(" ");
        String date = "";
        String time = "";
        if (data.length >= 2) {
            if (data[0].trim().length() == 10 && data[1].trim().length() == 5) {
                date = data[0].trim();
                time = data[1].trim();
            } else {
                return 0;
            }
        } else {
            return 0;
        }

        try {
            String[] arrdate = date.split("/", 3);
            String[] arrtime = time.split(":", 2);
            String day = arrdate[0];
            String month = arrdate[1];
            String year = arrdate[2];
            String hour = arrtime[0];
            String minute = arrtime[1];
            int day1 = Integer.parseInt(day);
            int month1 = Integer.parseInt(month);
            int year1 = Integer.parseInt(year);
            int hour1 = Integer.parseInt(hour);
            int minute1 = Integer.parseInt(minute);
            if (day1 > 0 && day1 <= 31 && month1 > 0 && month1 <= 12 && year1 >= 2016) {
                Calendar cal = Calendar.getInstance();
                cal.set(year1, (month1 - 1), day1, hour1, minute1);
                return cal.getTimeInMillis() / 1000l;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public static long parserDateToLong(String dateStr) {
        String[] data = dateStr.trim().split("/", 3);
        String date = "";
        if (data != null && data.length == 3) {
            try {
                String day = data[0];
                String month = data[1];
                String year = data[2];
                int day1 = Integer.parseInt(day);
                int month1 = Integer.parseInt(month);
                int year1 = Integer.parseInt(year);
                if (day1 > 0 && day1 <= 31 && month1 > 0 && month1 <= 12 && year1 >= 2016) {
                    Calendar cal = Calendar.getInstance();
                    cal.set(year1, (month1 - 1), day1, 0, 0);
                    return cal.getTimeInMillis() / 1000l;
                }
            } catch (Exception e) {
            }

        } else {
            return 0;
        }

        return 0;
    }

    public static int toSecondTimeAndCheck(long time) {
        if (time > 100000000000L) {
            return (int) time / 1000;
        }
        return (int) time;
    }

    public static int toSecondTime(long time) {
        int ret = 0;
        if (time > 10000000000l) {
            ret = (int) (time / 1000);
        } else {
            ret = (int) time;
        }
        return ret;
    }

    public static long toLongTime(int time) {
        long ret = 0;
        if (time < 10000000000l) {
            ret = ((long) time) * 1000;
        } else {
            ret = (long) time;
        }
        return ret;
    }

    public static String md5(String content) {
        MD5 md5 = new MD5();
        md5.Update(content);
        String hash = md5.asHex();
        return hash;
    }

    public static String encryptMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertTimeToDayString(long miliTime) {

        Date datetime = new Date(miliTime);
//        int s = datetime.getSeconds();
//        int mi = datetime.getMinutes();
//        int h = datetime.getHours();
        int d = datetime.getDate();
        int m = datetime.getMonth() + 1;
        int y = datetime.getYear() + 1900;

        String date = d + "/" + m + "/" + y;
        return date;
    }

    public static String convertTimeSecondToDayString(long secondTime) {

        Date datetime = new Date(secondTime * 1000);
//        int s = datetime.getSeconds();
//        int mi = datetime.getMinutes();
//        int h = datetime.getHours();
        int d = datetime.getDate();
        int m = datetime.getMonth() + 1;
        int y = datetime.getYear() + 1900;

        String date = d + "/" + m + "/" + y;
        return date;
    }

    public static String convertTimeToStringDateTime(long secondTime) {

        Date datetime = new Date(secondTime * 1000);
//        int s = datetime.getSeconds();
        int mi = datetime.getMinutes();//Calendar.get(Calendar.MINUTE)
        int h = datetime.getHours();
        int d = datetime.getDate();
        int m = datetime.getMonth() + 1;
        int y = datetime.getYear() + 1900;

        String day = d + "";
        String hour = h + "";
        String minute = mi + "";
        String month = m + "";
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        if (minute.length() == 1) {
            minute = "0" + minute;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }

        String date = day + "/" + month + "/" + y + " " + hour + ":" + minute;
        return date;
    }

    public static Map<String, Object> getTimeService() {
        long miliTime = System.currentTimeMillis();

        long timesecond = Utils.toSecondTimeAndCheck(miliTime);

        Date datetime = new Date(miliTime);
        int s = datetime.getSeconds();
        int mi = datetime.getMinutes();
        int h = datetime.getHours();
        int d = datetime.getDate();
        int m = datetime.getMonth() + 1;
        int y = datetime.getYear() + 1900;

        String time = h + ":" + mi + ":" + s;
        String date = m + "-" + d + "-" + y;

        Map<String, Object> ret = new TreeMap<String, Object>();

        ret.put("second", timesecond);
        ret.put("time", time);
        ret.put("date", date);

        return ret;
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println("time = " + convertLongTimeToString(time));
    }

    public static String getActionName(int actionId) {
        if (actionId == Constant.ACTIONID_EDIT) {
            return "Edit News";
        } else if (actionId == Constant.ACTIONID_VIEW) {
            return "View News";
        } else if (actionId == Constant.ACTIONID_POST) {
            return "Post News";
        } else {
            return "Unknow Action";
        }
    }

    public static String convertLongTimeToString(long miliTime) {

        long timesecond = Utils.toSecondTimeAndCheck(miliTime);

        Date datetime = new Date(timesecond * 1000);
        int s = datetime.getSeconds();
        int mi = datetime.getMinutes();
        int h = datetime.getHours();
        int d = datetime.getDate();
        int m = datetime.getMonth() + 1;
        int y = datetime.getYear() + 1900;

        String time = h + ":" + mi + ":" + s;
        String date = d + "/" + m + "/" + y;
        return (date + "  " + time);
//        Map<String, Object> ret = new TreeMap<String, Object>();
//
//        ret.put("second", timesecond);
//        ret.put("time", time);
//        ret.put("date", date);
//
//        return ret;
    }

    public static int convertTime(String someDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy H:mm");
        try {
            Date dateTime = sdf.parse(someDate);
            long mili = dateTime.getTime();

            return toSecondTime(mili);
        } catch (Exception ex) {
        }

        return -1;
    }

    public static int convertDate(int timer) {
        int ret = -1;
        try {
            long timesecond = Utils.toLongTime(timer);

            Date datetime = new Date(timesecond);

            Calendar cal = Calendar.getInstance();
            cal.setTime(datetime);

            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

            String timeFormat = dateFormat.format(cal.getTime());

            long mili = dateFormat.parse(timeFormat).getTime();

            ret = toSecondTime(mili);
        } catch (Exception ex) {
        }

        return ret;
    }

//    public static int timeDate(String timer) {
//        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
//        try {
//            Date dateTime = sdf.parse(timer);
//            long mili = dateTime.getTime();
//
//            return toSecondTime(mili);
//        } catch (Exception ex) {
//        }
//
//        return -1;
//    }
//    public static void main(String[] args) {
//        String someDate = "6-13-2014";
//        
//        
//        BettMatchCachetingCacheData data = BettinMatchCachence().getCache(1);
//        
//        List<BettingCMatchCacheCacheData> list = BettingCacMatchCache).getListBettingByTime(data.time);
//        
//        
//        Calendar cal = Calendar.getInstance();
//        cal.set(2014, 05, 13);
//        long time = cal.getTimeInMillis()/1000;
//     
//        List<BettingCacheData> listItem = BettingCacheMatchCachegetListBettingByTime((int) time);
//        
//        System.out.println("aaaaaa");
//      
//    }
    public static String getAvatarPath(String oname, int avatarversion, int size) {
        try {

            String path_big = Config.getParam("avatarpath", "180");
            String path_50 = Config.getParam("avatarpath", "50");
            String path_75 = Config.getParam("avatarpath", "75");
            String path_120 = Config.getParam("avatarpath", "120");
            String path_160 = Config.getParam("avatarpath", "160");
            String path_default = Config.getParam("avatarpath", "default");
            String path = path_default;
            int level = 4;
            int i_size = 50;
            MD5 md5 = new MD5();
            String oname_lower = oname.toLowerCase();
            md5.Update(oname_lower);
            String hash = md5.asHex();
            String avatarpath2 = "";
            for (int i = 0; i < level; i++) {
                avatarpath2 += "/" + hash.substring(i, i + 1);
            }

            try {
                i_size = Integer.valueOf(size);
            } catch (Exception ex) {
                i_size = 50;
            }

            if (i_size == 180) {
                path = path_big;
            } else if (i_size == 50) {
                path = path_50;
                String sub = hash.substring(0, 1);
                if (sub.equals("5")) {
                    sub = "1";
                }
                path = String.format(path, sub);
            } else if (i_size == 75) {
                path = path_75;
            } else if (i_size == 120) {
                path = path_120;
            } else if (i_size == 160) {
                path = path_160;
            } else {
                path = path_50;
                String sub = hash.substring(0, 1);
                if (sub.equals("5")) {
                    sub = "1";
                }
                path = String.format(path, sub);
            }
            return path + avatarpath2 + "/" + oname + "_" + size + "_" + avatarversion + ".jpg";
        } catch (Exception ex) {
            log.error("Util.getAvatarPath:" + ex.getMessage(), ex);
            return "";
        }
    }

    public static String splitShortContent(String content, int size) {
        try {
            if (content == null) {
                return "";
            }
            if (content.length() <= size) {
                return content;
            }
            String substr = content.substring(0, size);
            int spaceIndex = substr.lastIndexOf(" ");

            if (spaceIndex != -1 && spaceIndex >= size - 10) {
                substr = substr.substring(0, spaceIndex);
            }
            return substr + "...";
        } catch (Exception ex) {
            log.error("Util.splitShortContent:" + ex.getMessage(), ex);
            return "";
        }
    }
}
