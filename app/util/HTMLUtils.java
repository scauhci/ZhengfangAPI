package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * HTML工具类
 * 
 * @author Vincent
 * 
 */
public class HTMLUtils {

	/**
	 * 获取返回的内容
	 * 
	 * @param connection
	 * @return
	 * @throws IOException
	 */
	public static String getContent(HttpURLConnection connection)
			throws IOException {
		StringBuffer html = new StringBuffer();
		String string = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "GB2312"));
		while ((string = reader.readLine()) != null) {
			html.append(string);
		}
		return html.toString();
	}

	public static String getContent(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		return getContent(connection);
	}

}
