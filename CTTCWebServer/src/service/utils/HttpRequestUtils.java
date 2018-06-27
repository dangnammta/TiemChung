package service.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tungvc
 */
public class HttpRequestUtils {

	public static String buildCurrentUri(HttpServletRequest req) {
		return "http://" + req.getServerName() + req.getRequestURI() + "?" + req.getQueryString();
	}

	public static void redirect(HttpServletResponse resp, String urlRedirect) {
		try {
			resp.sendRedirect(urlRedirect);
		} catch (Exception ex) {
		}
	}

	public static void setCookie(String cookieName, String value, int expire, boolean httponly, String path, String domain, HttpServletRequest req, HttpServletResponse resp) {
		try {
			String strExpire = "";
			if (expire == 0) { // expire now
				Calendar cal = Calendar.getInstance();
				cal.add(cal.YEAR, -1);
				String ex = formatDate("EEE, dd-MMM-yyyy HH:mm:ss zzz", cal.getTime());
				strExpire = ";Expires=" + ex;
			} else if (expire > 0) {
				Calendar cal = Calendar.getInstance();
				cal.add(cal.MILLISECOND, expire * 1000);
				String ex = formatDate("EEE, dd-MMM-yyyy HH:mm:ss zzz", cal.getTime());
				strExpire = ";Expires=" + ex;
			}
			// else expire < -1: expires after browser is closed.

			String strHttponly = "";
			if (httponly == true) {
				strHttponly = ";HttpOnly";
			}

			String headerValue = cookieName + "=" + value + ";Path=" + path + ";Domain=" + domain + strExpire + strHttponly;
			resp.setHeader("P3P", "CP=\"NOI ADM DEV PSAi COM NAV OUR OTRo STP IND DEM\"");
			resp.addHeader("Set-Cookie", headerValue);
		} catch (Exception e) {
		}
	}

	public static String getCookie(HttpServletRequest req, String name) {
		Map<String, String> ret = getCookieMap(req);
		if (ret == null) {
			return null;
		}
		return ret.get(name);
	}

	public static Map<String, String> getCookieMap(HttpServletRequest req) {
		Map<String, String> ret = (Map<String, String>) req.getAttribute("tc");
		if (ret != null) {
			return ret;
		}
		ret = new HashMap<String, String>();

		try {
			Cookie[] cookies = req.getCookies();

			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					try {
						//					String name = cookies[i].getName();
						String name = URLDecoder.decode(cookies[i].getName(), "UTF-8");
						String value = cookies[i].getValue();
						if (value.toLowerCase().equals("deleted")) {
							continue;
						}

						ret.put(name, value);
						if (i > 50) {
							break;
						}
					} catch (Exception ex) {
					}
				}
			}
		} catch (Exception ex) {
		}
		req.setAttribute("zme.cookies", ret);
		return ret;
	}

	public static String formatDate(String format, Date d) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(d);
		} catch (Exception e) {
			return null;
		}
	}

	public static String buildParamRequestString(HashMap<String, String> mapParam) {
		String str = "";
		for (Map.Entry<String, String> entry : mapParam.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (str.equals("")) {
				str += key + "=" + value;
			} else {
				str += "&" + key + "=" + value;
			}
		}
		return str;
	}

//	public static String buildParamRequestString(HashMap<String, String> params) {
//		try {
//			StringBuffer requestParams = new StringBuffer();
//			if (params != null && params.size() > 0) {
//				Iterator<String> paramIterator = params.keySet().iterator();
//				while (paramIterator.hasNext()) {
//					String key = paramIterator.next();
//					String value = params.get(key);
//					requestParams.append(URLEncoder.encode(key, "UTF-8"));
//					requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
//					requestParams.append("&");
//				}
//				return requestParams.toString();
//			}
//		} catch (Exception e) {
//		}
//		return "";
//	}
	/**
	 *
	 * @param requestUrl
	 * @param method POST or GET
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String[] sendHttpRequest(String requestUrl, String method, HashMap<String, String> params) throws IOException {
//		List<String> response = new ArrayList<String>();
//		String requestParam = buildParamRequestString(params);
//
//		String newRequestUrl = requestUrl;
//		if ("POST".equals(method)) {
//		} else {
//			newRequestUrl = requestUrl + "?" + requestParam;
//			System.out.println(newRequestUrl);
//		}
//
//		URL url = new URL(newRequestUrl);
//                URLConnection urlConn = url.openConnection();
//                urlConn.setConnectTimeout(3000);
//		urlConn.setReadTimeout(3000);
//		urlConn.setUseCaches(false);
//                
//                urlConn.setRequestProperty("Cookie", "zsession=TwE7.3568340.743.5PrGo1DgyosH5YukI6GOBZSHUbSQ3NPnDX1tG20H7dgFDyx2SsIcy-0rDx4=");
//
//		// the request will return a response
//		urlConn.setDoInput(true);
//
//		if ("POST".equals(method)) {
//			// set request method to POST
//			urlConn.setDoOutput(true);
//		} else {
//			// set request method to GET
//			urlConn.setDoOutput(false);
//		}
//		if ("POST".equals(method) && params != null && params.size() > 0) {
//			OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
//			writer.write(requestParam);
//			writer.flush();
//		}
//
//		// reads response, store line by line in an array of Strings
//		BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
//
//		String line = "";
//		while ((line = reader.readLine()) != null) {
//			response.add(line);
//		}
//
//		reader.close();
//		return (String[]) response.toArray(new String[0]);

		return sendHttpRequest(requestUrl, method, params, new HashMap<String, String>());
	}

	public static String[] sendHttpRequest(String requestUrl, String method, HashMap<String, String> params, HashMap<String, String> mapCookie) throws IOException {
		List<String> response = new ArrayList<String>();
		String requestParam = buildParamRequestString(params);

		String newRequestUrl = requestUrl;
		if ("POST".equals(method)) {
		} else {
			if (requestParam.length() == 0) {
				newRequestUrl = requestUrl;
			} else {
				newRequestUrl = requestUrl + "?" + requestParam;
			}
			System.out.println(newRequestUrl);
		}

		URL url = new URL(newRequestUrl);
		URLConnection urlConn = url.openConnection();
		urlConn.setConnectTimeout(3000);
		urlConn.setReadTimeout(3000);
		urlConn.setUseCaches(false);
		if (!mapCookie.isEmpty()) {
			String strCookie = buildCookieFromMap(mapCookie);
			urlConn.setRequestProperty("Cookie", strCookie);
		}
//                urlConn.setRequestProperty("Cookie", "zsession=OCqr.3568340.745.zrTjr1i7-q2PUoP3G0aGGpzySZeIO7uSFdr_BIXy5XU7MiQlUmd-SUfOxzq=");


		// the request will return a response
		urlConn.setDoInput(true);

		if ("POST".equals(method)) {
			// set request method to POST
			urlConn.setDoOutput(true);
		} else {
			// set request method to GET
			urlConn.setDoOutput(false);
		}
		if ("POST".equals(method) && params != null && params.size() > 0) {
			OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
			writer.write(requestParam);
			writer.flush();
		}

		// reads response, store line by line in an array of Strings
		BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

		String line = "";
		while ((line = reader.readLine()) != null) {
			response.add(line);
		}

		reader.close();
		return (String[]) response.toArray(new String[0]);
	}

	/**
	 *
	 * @param requestUrl
	 * @param method
	 * @param params
	 * @return a String
	 * @throws IOException
	 */
	public static String sendHttpRequest2(String requestUrl, String method, HashMap<String, String> params) throws IOException {
		String[] arrResponse = sendHttpRequest(requestUrl, method, params);
		if (arrResponse != null && arrResponse.length > 0) {
			return arrResponse[0];
		}
		return "";
	}

	/**
	 *
	 * @param requestUrl
	 * @param method
	 * @param params
	 * @param mapCookie { "zsession": ksjflsfls ; "authvng": ksdflsl}
	 * @return a String
	 * @throws IOException
	 */
	public static String sendHttpRequest2(String requestUrl, String method, HashMap<String, String> params, HashMap<String, String> mapCookie) throws IOException {
		String[] arrResponse = sendHttpRequest(requestUrl, method, params, mapCookie);
		if (arrResponse != null && arrResponse.length > 0) {
			return arrResponse[0];
		}
		return "";
	}

	public static String getClientIP(HttpServletRequest request) {
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

	public static String buildCookieFromMap(HashMap<String, String> mapParam) {
		String str = "";
		for (Map.Entry<String, String> entry : mapParam.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (str.equals("")) {
				str += key + "=" + value;
			} else {
				str += "; " + key + "=" + value;
			}
		}
		return str;
	}
//	public static void main(String[] args) {
//		try {
//			//http://api.photo.apps.zing.vn/photo/api/rest?method=pushfeedphoto&pid=1876449958
//			String requestUrl = "http://api.photo.apps.zing.vn/photo/api/rest";
//			String method = "GET";
//			HashMap<String, String> params = new HashMap<String, String>();
//			params.put("method", "pushfeedphoto");
//			params.put("pid", 1876449958L + "");
//
//			String response = sendHttpRequest2(requestUrl, method, params);
//			System.out.println(response);
////			String[] response = sendHttpRequest(requestUrl, method, params);
////			System.out.println(response.length);
////			if (response != null && response.length > 0) {
////				System.out.println("RESPONSE FROM: " + requestUrl);
////				for (String line : response) {
////					System.out.println(line);
////					System.out.println("###############");
////				}
////			}
//
//		} catch (IOException ex) {
//			System.out.println("ERROR: " + ex.getMessage());
//		}
//	}
}
