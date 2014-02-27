package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * HTTP工具类
 * 
 * @author Vincent
 * 
 */
public class HTTPUtils {

	public static HttpURLConnection getConnection(String urlStr)
			throws IOException {
		URL url = new URL(urlStr);
		return (HttpURLConnection) url.openConnection();
	}

}
