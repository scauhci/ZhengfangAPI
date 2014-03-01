package models;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import sun.misc.BASE64Encoder;
import util.HTMLUtils;
import util.HTTPUtils;

/**
 * 华农宝客户端
 * 
 * @author Vincent
 * 
 */
public class ISCAUClient {

	private static final String host = "115.28.144.49";

	private static BASE64Encoder encoder = new BASE64Encoder();

	/**
	 * base64加密
	 * 
	 * @param password
	 * @return
	 */
	private static String encrypt(String str) {
		return encoder.encode(str.getBytes());
	}

	/**
	 * 获取学生成绩
	 * 
	 * @param year
	 * @param term
	 * @return
	 * @throws IOException
	 */
	public static JSONObject getGrade(String account, String password,
			int portal, String year, String term) throws IOException {
		String urlStr = String.format("http://%s/edusys/goal/%s/%s/%d/%s/%s",
				host, account, encrypt(password), portal, year, term);
		HttpURLConnection connection = HTTPUtils.getConnection(urlStr);
		if (connection.getResponseCode() != 200)
			return getErrorMsg(connection);
		JSONObject json = new JSONObject(HTMLUtils.getContent(connection));
		return json;
	}

	/**
	 * 获取学生课表
	 * @param account
	 * @param password
	 * @param portal
	 * @return
	 * @throws IOException
	 */
	public static JSONObject getTimeTable(String account, String password,
			int portal) throws IOException {
		String urlStr = String.format("http://%s/edusys/classtable/%s/%s/%d",
				host, account, encrypt(password), portal);
		HttpURLConnection connection = HTTPUtils.getConnection(urlStr);
		if (connection.getResponseCode() != 200)
			return getErrorMsg(connection);
		JSONObject json = new JSONObject(HTMLUtils.getContent(connection));
		return json;
	}

	/**
	 * 获取选修课
	 * @param account
	 * @param password
	 * @param portal
	 * @return
	 * @throws IOException
	 */
	public static JSONObject getOptionalCourse(String account, String password,
			int portal) throws IOException {
		String urlStr = String.format(
				"http://%s/edusys/pickclassinfo/%s/%s/%d", host, account,
				encrypt(password), portal);
		HttpURLConnection connection = HTTPUtils.getConnection(urlStr);
		if (connection.getResponseCode() != 200)
			return getErrorMsg(connection);
		JSONObject json = new JSONObject(HTMLUtils.getContent(connection));
		return json;
	}

	/**
	 * 获取已借阅图书
	 * @param account
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public static JSONObject getBooks(String account, String password)
			throws IOException {
		String urlStr = String.format("http://%s/lib/list/now/%s/%s", host,
				account, encrypt(password));
		HttpURLConnection connection = HTTPUtils.getConnection(urlStr);
		if (connection.getResponseCode() != 200)
			return getErrorMsg(connection);
		JSONObject json = new JSONObject(HTMLUtils.getContent(connection));
		return json;
	}

	/**
	 * 获取考试信息
	 * @param account
	 * @param password
	 * @param portal
	 * @return
	 * @throws IOException
	 */
	public static JSONObject getExamInfo(String account, String password,
			int portal) throws IOException {
		String urlStr = String.format("http://%s/edusys/exam/%s/%s/%d", host,
				account, encrypt(password), portal);
		HttpURLConnection connection = HTTPUtils.getConnection(urlStr);
		if (connection.getResponseCode() != 200)
			return getErrorMsg(connection);
		JSONObject json = new JSONObject(HTMLUtils.getContent(connection));
		return json;
	}

	/**
	 * 查找图书
	 * @param keyWord
	 * @return
	 * @throws IOException
	 */
	public static JSONObject searchBook(String keyWord) throws IOException {
		String urlStr = String.format("http://%s/lib/search/%s/1", host,
				encrypt(keyWord));
		HttpURLConnection connection = HTTPUtils.getConnection(urlStr);
		if (connection.getResponseCode() != 200)
			return getErrorMsg(connection);
		JSONObject json = new JSONObject(HTMLUtils.getContent(connection));
		return json;
	}

	/**
	 * 获取出错信息
	 * @param connection
	 * @return
	 * @throws IOException
	 */
	private static JSONObject getErrorMsg(HttpURLConnection connection)
			throws IOException {
		return new JSONObject(String.format("{error:%s,msg:%s}",
				connection.getResponseCode(), connection.getResponseMessage()));
	}

}
