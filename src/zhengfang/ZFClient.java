package zhengfang;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import util.HTMLUtils;
import util.HTTPUtils;
import zhengfang.bean.ReportCartItem;
import zhengfang.bean.StudentInfo;
import zhengfang.bean.TimeTableItem;

/**
 * 正方教务系统客户端
 * 
 * @author Vincent
 * 
 */
public class ZFClient {

	/**
	 * 登录入口(目前只有3个入口)
	 */
	private int portal = 0;
	/**
	 * 正方服务器地址
	 */
	private String host = Context.getHost(portal);
	/**
	 * 验证码url
	 */
	private String checkCodeUrl;
	/**
	 * 登录时提交表单的url
	 */
	private String loginUrl;
	/**
	 * 请求头Referer(不带上的话会失败)
	 */
	private String referer;
	/**
	 * 参数的编码方式
	 */
	private static String ENCODE = "UTF-8";
	/**
	 * 学生个人信息
	 */
	private StudentInfo studentInfo = new StudentInfo();

	/**
	 * 从正方系统获取的sessionId
	 */
	private String sessionId = null;
	/**
	 * 是否已经登录
	 */
	private boolean logined = false;

	public boolean isLogined() {
		return logined;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getHost() {
		return host;
	}

	private void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getPortal() {
		return portal;
	}

	public ZFClient() {
		this(0);
	}

	public ZFClient(int portal) {
		setPortal(portal);
		HttpURLConnection.setFollowRedirects(false);
	}

	/**
	 * 设置登录入口
	 * 
	 * @param portal
	 */
	public void setPortal(int portal) {
		this.portal = portal;
		host = Context.getHost(portal);
		referer = "http://" + host + "/default2.aspx";
		loginUrl = "http://" + host + "/default2.aspx";
		checkCodeUrl = "http://" + host + "/CheckCode.aspx";
	}

	/**
	 * 获取验证码图片输入流
	 * 
	 * @return
	 * @throws Exception
	 */
	public InputStream getCheckCodeInputStream() throws Exception {
		URL url = new URL(checkCodeUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 设置请求头
		connection.addRequestProperty("Host", host);
		connection.setRequestProperty("Cookie", "ASP.NET_SessionId="
				+ sessionId);
		// 获取sessionId
		if (sessionId == null) {
			Map<String, String> cookies = HTTPUtils.getCookies(connection);
			// System.out.println(cookies.get("ASP.NET_SessionId"));
			setSessionId(cookies.get("ASP.NET_SessionId"));
		}
		if (connection.getResponseCode() != 200) {
			System.out.println("获取验证码失败");
			return null;
		}
		return connection.getInputStream();
	}

	/**
	 * 对参数url编码
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String encode(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str, ENCODE);
	}

	/**
	 * 登录
	 * 
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public boolean login(String account, String password, String checkCode)
			throws IOException {
		String viewState = Context.getViewState(portal);
		String queryStr = String
				.format("__VIEWSTATE=%s&TextBox1=%s&TextBox2=%s&TextBox3=%s&lbLanguage=&__VIEWSTATEGENERATOR=92719903&Button1=",
						viewState, account, password, checkCode);
		URL url = new URL(loginUrl + "?" + queryStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Host", host);
		connection.setRequestProperty("Cookie", "ASP.NET_SessionId="
				+ sessionId);

		if (connection.getResponseCode() == 302) {
			if (connection.getHeaderField("Location").indexOf("/xs_main.aspx?") != -1) {
				logined = true;
				studentInfo.setId(account);
				return true;
			}
		}
		System.out.println("登录失败");
		return false;
	}

	/**
	 * 获取学生个人信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public StudentInfo getStudentInfo() throws Exception {
		URL url = new URL("http://" + host + "/xsgrxx.aspx?xh="
				+ studentInfo.getId() + "&gnmkdm=N121501");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Host", host);
		connection.setRequestProperty("Cookie", "ASP.NET_SessionId="
				+ sessionId);
		connection.setRequestProperty("Referer", referer);

		if (connection.getResponseCode() != 200) {
			System.out.println("获取个人信息失败");
			return null;
		}

		HTMLUtils.parseStudentInfo(studentInfo, connection);
		return studentInfo;
	}

	/**
	 * 获取学生课表
	 * 
	 * @param year
	 * @param term
	 * @return
	 * @throws IOException
	 */
	public ArrayList<TimeTableItem> getTimeTable() throws IOException {
		URL url = new URL("http://" + host + "/xskbcx.aspx?xh="
				+ studentInfo.getId() + "&xm=" + encode(studentInfo.getName())
				+ "&gnmkdm=N121603");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Host", host);
		connection.setRequestProperty("Cookie", "ASP.NET_SessionId="
				+ sessionId);
		connection.setRequestProperty("Referer", referer);

		if (connection.getResponseCode() != 200) {
			System.out.println("获取课程表失败");
			return null;
		}

		ArrayList<TimeTableItem> timeTable = new ArrayList<TimeTableItem>();
		HTMLUtils.parseTimeTable(timeTable, connection);
		return timeTable;
	}

	/**
	 * 获取学生考试成绩
	 * 
	 * @param year
	 * @param term
	 * @return
	 * @throws Exception
	 */
	public List<ReportCartItem> getReportCard(String year, String term)
			throws Exception {
		// 获取viewstate(不同页面的viewstate不同)
		Connection conn = Jsoup.connect("http://" + host + "/xscjcx.aspx?xh="
				+ studentInfo.getId() + "&xm=" + encode(studentInfo.getName())
				+ "&gnmkdm=N121603");
		conn.timeout(3000);
		conn.followRedirects(false);
		conn.cookie("ASP.NET_SessionId", sessionId);
		conn.header("Host", host);
		conn.header("Referer", referer);
		Document document = conn.get();
		String viewState = HTMLUtils.getViewState(document);
		// 获取成绩单
		URL url = new URL("http://" + host + "/xscjcx.aspx?xh="
				+ studentInfo.getId() + "&xm=" + encode(studentInfo.getName())
				+ "&gnmkdm=N121603");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Host", host);
		connection.setRequestProperty("Cookie", "ASP.NET_SessionId="
				+ sessionId);
		connection.setRequestProperty("Referer", referer);

		String requestContent = String
				.format("ddlXN=%s&ddlXQ=%s&btn_xq=%s&__VIEWSTATE=%s&__EVENTARGUMENT=&__EVENTTARGET=&btn_xq=&ddl_kcxz=&hidLanguage=&__VIEWSTATEGENERATOR=9727EB43",
						year, term, encode("学期成绩"), encode(viewState));

		PrintWriter writer = new PrintWriter(connection.getOutputStream());
		writer.write(requestContent);
		writer.flush();

		if (connection.getResponseCode() != 200) {
			System.out.println("获取成绩失败");
			return null;
		}

		List<ReportCartItem> reportCard = new ArrayList<ReportCartItem>();
		HTMLUtils.parseReportCard(reportCard, connection);
		return reportCard;
	}

	/**
	 * 获取选修课
	 */
	public void getElectiveCourse() {

	}

}
