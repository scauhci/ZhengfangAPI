package util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import zhengfang.bean.ReportCartItem;
import zhengfang.bean.StudentInfo;
import zhengfang.bean.TimeTableItem;

/**
 * Json工具类
 * 
 * @author Vincent
 * 
 */
public class JSONUtils {

	public static String getStudentInfoJson(StudentInfo studentInfo) {
		JSONObject json = new JSONObject();
		json.put("id", studentInfo.getId());
		json.put("name", studentInfo.getName());
		json.put("birthday", studentInfo.getBirthday());
		json.put("grade", studentInfo.getGrade());
		json.put("address", studentInfo.getAddress());
		json.put("academy", studentInfo.getAcademy());
		json.put("birthPlace", studentInfo.getBirthPlace());
		json.put("major", studentInfo.getMajor());
		json.put("sex", studentInfo.getSex());
		json.put("phone", studentInfo.getPhone());
		return json.toString();

	}

	public static String getReportCardJson(List<ReportCartItem> reportCard) {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		json.put("data", jsonArray);
		for (int i = 0; i < reportCard.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			ReportCartItem item = reportCard.get(i);
			jsonObject.put("year", item.getYear());
			jsonObject.put("academy", item.getAcademy());
			jsonObject.put("term", item.getTerm());
			jsonObject.put("courseName", item.getCourseName());
			jsonObject.put("courseProperty", item.getCourseProperty());
			jsonObject.put("grade", item.getGrade());
			jsonObject.put("courseCode", item.getCourseCode());
			jsonArray.put(jsonObject);
		}
		return json.toString();
	}

	public static String getTimeTableJson(List<TimeTableItem> timeTable) {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		json.put("data", jsonArray);
		for (int i = 0; i < timeTable.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			TimeTableItem item = timeTable.get(i);
			jsonObject.put("courseName", item.getCourseName());
			jsonObject.put("teacherName", item.getTeacherName());
			jsonObject.put("classroomId", item.getClassroomId());
			jsonObject.put("week", item.getWeek());
			jsonObject.put("courseTime", item.getCourseTime());
			jsonObject.put("startEndTime", item.getStartEndTime());
			jsonArray.put(jsonObject);
		}
		return json.toString();

	}
}
