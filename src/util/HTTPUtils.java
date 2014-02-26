package util;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

;

/**
 * Http工具类
 */
public class HTTPUtils {
	/**
	 * 获取cookies
	 * 
	 * @param connection
	 * @return
	 */
	public static Map<String, String> getCookies(HttpURLConnection connection) {
		Map<String, String> cookies = new HashMap<String, String>();
		String cookiesStr = connection.getHeaderField("Set-Cookie");
		String KVpairs[] = cookiesStr.split(";");
		for (int i = 0; i < KVpairs.length; i++) {
			String[] kv = KVpairs[i].split("=");
			String key = kv[0];
			String value = kv[1];
			cookies.put(key, value);
		}
		return cookies;
	}
}
