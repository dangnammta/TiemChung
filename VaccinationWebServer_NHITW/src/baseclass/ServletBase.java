package baseclass;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

/**
 *
 * @author namdv
 */
public class ServletBase extends HttpServlet {

    private static final Logger log = Log.getLogger(ServletBase.class);

    public static String getOStype(HttpServletRequest request, boolean isMobile) {
        if (isMobile) {
            String str = request.getHeader("User-Agent");
            if (str.matches("(.*)([Android])(.*)")) {
                return "android";
            } else if (str.matches("(.*)([iPhone|iPad|iPod])(.*)")) {
                return "ios";
            } else {
                return "touch";
            }
        } else {
            return "touch";
        }

    }

    public static void main(String args[]) {
        //String str = request.getHeader("User-Agent");
        String str = " Mozilla/5.0 (iPad; CPU OS 7_0_4 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Mobile/11B554a";
        boolean a = str.matches("(.*)([Android])(.*)");
        boolean b = str.matches("(.*)([iPhone|iPad|iPod])(.*)");
        int x = 2;

    }

    /**
     * Sign key creation
     *
     * @param ownerId
     * @param time
     * @return
     */
    public static String getSign(long ownerId, long time) {
        String key = ownerId + ZM_POST_SALT + time;
        key = DigestUtils.md5Hex(key);
        return key;
    }

    public static boolean checkSign(long ownerId, long time, String sign) {
        return getSign(ownerId, time).equals(sign);
    }

    public static boolean postParamValidate(long ownerId, long time, String sign) {
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        if (currentTime - time > 86400) {
            return false;
        }
        return checkSign(ownerId, time, sign);
    }

    protected void responseText(Object text, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            out = response.getWriter();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
            response.addHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, origin, authorization, accept, client-security-token");
            response.addHeader("Access-Control-Max-Age", "1000");
            out.print(text);
        } catch (IOException ex) {
            log.warn(ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    protected void responseText2(Object text, HttpServletResponse response, String callback) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");

            if (callback.equals("")) {
                out = response.getWriter();
                out.print(text);
            } else if (!callback.equals("")) {
                String strRes = callback + "(" + text + ")";
                Object objRes = strRes;

                out = response.getWriter();
                out.print(objRes);
            }

        } catch (IOException ex) {
            log.warn(ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    protected void responseJson(Object text, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.print(text);
        } catch (IOException ex) {
            log.warn(ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        String zAuthString = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if (ck.getName().equalsIgnoreCase(cookieName)) {
                    zAuthString = ck.getValue();
                    break;
                }
            }
        }
        return zAuthString;
    }

    public static void setCookieValue(String cookieName, String cookieValue, HttpServletRequest req, HttpServletResponse resp) {
         Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setValue(cookieValue);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");
        cookie.setDomain("tiemchung.fisgmc.com");
        resp.addCookie(cookie);
    }

    public static void deleteCookieValue(String cookieName, HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if (ck.getName().equalsIgnoreCase(cookieName)) {
                    ck.setValue(null);
                    ck.setMaxAge(0);
                    resp.addCookie(ck);
                    break;
                }
            }
        }
//        Cookie myCookie = new Cookie(cookieName, null);
//        myCookie.setPath("/");
//        resp.addCookie(myCookie);
    }

    protected static String getClientIP(HttpServletRequest request) {
        String clientIp = request.getHeader("X-FORWARDED-FOR");
        if (clientIp == null || clientIp.length() == 0) {
            clientIp = request.getHeader("X-Forwarded-For");
        }
        if (clientIp == null || clientIp.length() == 0) {
            clientIp = request.getHeader("x-forwarded-for");
        }
        if (clientIp == null || clientIp.length() == 0) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }

    private static final String ZM_POST_SALT = ":zmf@zmejava:";

    public boolean checkValidParam(HttpServletRequest request, String[] params) {
        try {
            Set<String> s = new HashSet<String>();
            Enumeration<String> requestParam = request.getParameterNames();

            while (requestParam.hasMoreElements()) {
                String param = requestParam.nextElement();
                s.add(param.toLowerCase());
            }

            for (int i = 0; i < params.length; i++) {
                if (!s.contains(params[i].toLowerCase())) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    protected void echo(Object text, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.print(text);
        } catch (IOException ex) {
            log.warn(ex.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public String getParams(HttpServletRequest req, int index) {
        String params = "";
        String uri = req.getRequestURI();
        String[] arrUri = uri.split("/");
        int len = arrUri.length;
        if (index < len) {
            params = arrUri[index];
        }
        return params;
    }
}
