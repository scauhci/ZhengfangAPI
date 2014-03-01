package controllers;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import models.ISCAUClient;
import play.mvc.Controller;
import util.HTMLUtils;
import util.HTTPUtils;

public class ZhengFangAPIS extends Controller {
	/**
	 * 获取课程表
	 * 
	 * @param account
	 *            学号
	 * @param password
	 *            密码
	 * @param portal
	 *            服务器号（1-6）
	 * @throws IOException
	 */
	public static void getTimeTable(String account, String password, int portal)
			throws IOException {
		portal = (portal >= 1 && portal <= 6) ? portal : 1;
		JSONObject json = ISCAUClient.getTimeTable(account, password, portal);
		renderJSON(json.toString());
	}

	/**
	 * 获取成绩
	 * 
	 * @param account
	 * @param password
	 * @param portal
	 * @param year
	 *            学年
	 * @param term
	 *            学期
	 * @throws JSONException
	 * @throws IOException
	 */
	public static void getGrade(String account, String password, int portal,
			String year, String term) throws JSONException, IOException {
		portal = (portal >= 1 && portal <= 6) ? portal : 1;
		JSONObject json = ISCAUClient.getGrade(account, password, portal, year,
				term);
		renderJSON(json.toString());
	}

	/**
	 * 获取选修课
	 * 
	 * @param account
	 * @param password
	 * @param portal
	 * @throws JSONException
	 * @throws IOException
	 */
	public static void getOptionalCourse(String account, String password,
			int portal) throws JSONException, IOException {
		portal = (portal >= 1 && portal <= 6) ? portal : 1;
		JSONObject json = ISCAUClient.getOptionalCourse(account, password,
				portal);
		renderJSON(json.toString());
	}

	/**
	 * 获取当前借阅的图书
	 * 
	 * @param account
	 * @param password
	 * @throws IOException
	 */
	public static void getBooks(String account, String password)
			throws IOException {
		JSONObject json = ISCAUClient.getBooks(account, password);
		renderJSON(json.toString());
	}

	/**
	 * 查找图书
	 * 
	 * @param keyWord
	 * @return
	 * @throws IOException
	 */
	public static void searchBook(String keyWord) throws IOException {
		JSONObject json = ISCAUClient.searchBook(keyWord);
		renderJSON(json.toString());
	}

	/**
	 * 获取考试信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public static void getExamInfo(String account, String password, int portal)
			throws JSONException, IOException {
		portal = (portal >= 1 && portal <= 6) ? portal : 1;
		JSONObject json = ISCAUClient.getExamInfo(account, password, portal);
		renderJSON(json.toString());
	}

}
