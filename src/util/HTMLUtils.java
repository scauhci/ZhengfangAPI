package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import zhengfang.bean.ReportCartItem;
import zhengfang.bean.StudentInfo;
import zhengfang.bean.TimeTableItem;

/**
 * HTML工具类
 * 
 * @author Vincent
 * 
 */
public class HTMLUtils {
	/**
	 * 页面的编码方式
	 */
	private static String CHARSET = "GB2312";

	/**
	 * 从html页面中抓取学生信息
	 * 
	 * @param studentInfo
	 * @param html
	 * @throws IOException
	 */
	public static void parseStudentInfo(StudentInfo studentInfo,
			HttpURLConnection connection) throws IOException {
		Document document = Jsoup.parse(connection.getInputStream(), CHARSET,
				"");
		parseStudentInfo(studentInfo, document);
	}

	/**
	 * 从html页面中抓取学生信息
	 * 
	 * @param studentInfo
	 * @param html
	 * @throws IOException
	 */
	public static void parseStudentInfo(StudentInfo studentInfo,
			Document document) throws IOException {
		String id = document.getElementById("xh").text();
		String name = document.getElementById("xm").text();
		String sex = document.getElementById("lbl_xb").text();
		String birthPlace = document.getElementById("lbl_jg").text();
		String idNumber = document.getElementById("lbl_sfzh").text();
		String academy = document.getElementById("lbl_xy").text();
		String phone = document.getElementById("lbl_lxdh").text();
		String birthday = document.getElementById("lbl_csrq").text();
		String grade = document.getElementById("lbl_xzb").text();
		String major = document.getElementById("lbl_zymc").text();
		String address = document.getElementById("lbl_jtszd").text();

		studentInfo.setId(id);
		studentInfo.setName(name);
		studentInfo.setSex(sex);
		studentInfo.setBirthPlace(birthPlace);
		studentInfo.setIdNumber(idNumber);
		studentInfo.setAcademy(academy);
		studentInfo.setPhone(phone);
		studentInfo.setBirthday(birthday);
		studentInfo.setGrade(grade);
		studentInfo.setMajor(major);
		studentInfo.setAddress(address);
	}

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

	/**
	 * 从html页面抓取课程表
	 * 
	 * @param timeTable
	 * @param html
	 * @throws IOException
	 */
	public static void parseTimeTable(List<TimeTableItem> timeTable,
			HttpURLConnection connection) throws IOException {
		Document document = Jsoup.parse(connection.getInputStream(), CHARSET,
				"");
		parseTimeTable(timeTable, document);
	}

	/**
	 * 从html页面抓取课程表
	 * 
	 * @param timeTable
	 * @param html
	 * @throws IOException
	 */
	public static void parseTimeTable(List<TimeTableItem> timeTable,
			Document document) throws IOException {
		Elements elements = document.getElementsByAttributeValue("align",
				"Center");

		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			if (!element.hasAttr("rowspan"))
				continue;
			TimeTableItem.setData(timeTable, element.text());
		}

	}

	/**
	 * 从html中抓取成绩单
	 * 
	 * @param reportCard
	 * @param html
	 * @throws IOException
	 */
	public static void parseReportCard(List<ReportCartItem> reportCard,
			HttpURLConnection connection) throws IOException {
		Document document = Jsoup.parse(connection.getInputStream(), CHARSET,
				"");
		parseReportCard(reportCard, document);
	}

	/**
	 * 从html中抓取成绩单
	 * 
	 * @param reportCard
	 * @param html
	 * @throws IOException
	 */
	public static void parseReportCard(List<ReportCartItem> reportCard,
			Document document) throws IOException {

		Elements elements = document.getElementsByAttributeValue("class",
				"datelist");
		Element table = elements.get(0);
		Element tbody = table.child(0);
		Elements trs = tbody.children();

		for (int i = 0; i < trs.size(); i++) {
			Element tr = trs.get(i);
			if (tr.hasClass("datelisthead"))
				continue;

			String str = trs.get(i).text();
			String[] strs = str.split(" ");

			ReportCartItem item = new ReportCartItem();
			for (int j = 0; j < strs.length; j++) {
				ReportCartItem.setReportCardItem(item, j, strs[j]);
			}
			reportCard.add(item);

		}
	}

	/**
	 * 获取页面中的参数VIEWSTATE
	 * 
	 * @param html
	 * @return
	 * @throws Exception
	 */
	public static String getViewState(HttpURLConnection connection)
			throws Exception {
		Document document = Jsoup.parse(connection.getInputStream(), CHARSET,
				"");
		return getViewState(document);
	}

	/**
	 * 获取页面中的参数VIEWSTATE
	 * 
	 * @param html
	 * @return
	 * @throws Exception
	 */
	public static String getViewState(Document document) throws Exception {
		Elements elements = document.getElementsByTag("input");
		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			if (element.attr("name").equals("__VIEWSTATE")) {
				return element.attr("value");
			}
		}
		return null;
	}
}
