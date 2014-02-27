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
	 * @param password
	 * @param portal
	 * @throws IOException
	 */
	public static void getTimeTable(String account, String password,
			String portal) throws IOException {
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
	 * @param term
	 * @throws JSONException
	 * @throws IOException
	 */
	public static void getGrade(String account, String password, String portal,
			String year, String term) throws JSONException, IOException {
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
			String portal) throws JSONException, IOException {
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
	public static void getExamInfo(String account, String password,
			String portal) throws JSONException, IOException {
		JSONObject json = ISCAUClient.getExamInfo(account, password, portal);
		renderJSON(json.toString());
	}

}
