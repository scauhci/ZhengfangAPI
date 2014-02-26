package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import util.JSONUtils;
import zhengfang.ZFClient;
import zhengfang.bean.ReportCartItem;
import zhengfang.bean.StudentInfo;
import zhengfang.bean.TimeTableItem;

import com.opensymphony.xwork2.ActionContext;

public class MainAction {
	private int portal;

	private String account;
	private String password;
	private String checkCode;

	private InputStream inputStream;

	private String term;
	private String year;

	private String error;

	private String timeTableJson;
	private String reportCardJson;
	private String studentInfoJson;

	public void setStudentInfoJson(String studentInfoJson) {
		this.studentInfoJson = studentInfoJson;
	}

	public String getStudentInfoJson() {
		return studentInfoJson;
	}

	public String getReportCardJson() {
		return reportCardJson;
	}

	public String getTimeTableJson() {
		return timeTableJson;
	}

	public void setReportCardJson(String reportCardJson) {
		this.reportCardJson = reportCardJson;
	}

	public void setTimeTableJson(String timeTableJson) {
		this.timeTableJson = timeTableJson;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getAccount() {
		return account;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public String getPassword() {
		return password;
	}

	public int getPortal() {
		return portal;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setPortal(int portal) {
		this.portal = portal;
	}

	public String getYear() {
		return year;
	}

	public String getTerm() {
		return term;
	}

	public ZFClient getZFClient() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (!session.containsKey("ZFClient")) {
			session.put("ZFClient", new ZFClient());
		}
		return (ZFClient) session.get("ZFClient");
	}

	/**
	 * 登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		ZFClient client = getZFClient();
		boolean ok = client.login(account, password, checkCode);
		if (ok) {
			StudentInfo studentInfo = client.getStudentInfo();
			studentInfoJson = JSONUtils.getStudentInfoJson(studentInfo);
			return "showStudentInfo";
		}
		setError("登录失败");
		return "error";
	}

	/**
	 * 获取验证码图片
	 * 
	 * @throws Exception
	 */
	public String getCheckCodeImage() throws Exception {
		ZFClient client = getZFClient();
		client.setPortal(portal);
		inputStream = client.getCheckCodeInputStream();
		return "showCheckCodeImage";
	}

	/**
	 * 获取学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getStudentInfo() throws Exception {
		StudentInfo studentInfo = getZFClient().getStudentInfo();
		studentInfoJson = JSONUtils.getStudentInfoJson(studentInfo);
		return "showStudentInfo";
	}

	/**
	 * 获取课程表
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getTimeTable() throws IOException {
		List<TimeTableItem> timeTable = getZFClient().getTimeTable();
		timeTableJson = JSONUtils.getTimeTableJson(timeTable);
		return "showTimeTable";
	}

	/**
	 * 获取学生成绩
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getReportCard() throws Exception {
		List<ReportCartItem> reportCard = getZFClient().getReportCard(year,
				term);
		reportCardJson = JSONUtils.getReportCardJson(reportCard);
		return "showReportCard";
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	public String index() {
		return "index";
	}

}
