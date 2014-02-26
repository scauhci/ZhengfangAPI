package zhengfang.bean;

import java.util.List;

public class TimeTableItem {

	private String courseName; // 课程名字

	private String week; // 星期
	private String courseTime;// 上课时间
	private String startEndTime; // 课程开始结束时间

	private String teacherName; // 老师名字

	private String classroomId; // 教室编号

	public static void setData(List<TimeTableItem> timeTable, String htmlStr) {
		String str[] = htmlStr.split(" ");
		if (str.length <= 4) {
			TimeTableItem item = new TimeTableItem();
			setItem(item, str, 0);
			timeTable.add(item);
		}

		else if (str.length <= 8) {
			TimeTableItem item1 = new TimeTableItem();
			setItem(item1, str, 0);
			timeTable.add(item1);
			TimeTableItem item2 = new TimeTableItem();
			setItem(item2, str, 4);
			timeTable.add(item2);
		}

	}

	private static void setItem(TimeTableItem item, String[] str, int index) {
		item.setCourseName(str[index + 0]);
		StringBuffer string = new StringBuffer(str[index + 1]);
		item.setWeek(string.substring(0, string.indexOf("第")));
		item.setCourseTime(string.substring(string.indexOf("第") + 1,
				string.indexOf("节")));
		item.setStartEndTime(string.substring(string.indexOf("{") + 1,
				string.indexOf("}")));
		item.setTeacherName(str[index + 2]);
		// 可能有些课程没有教室
		if (index + 3 < str.length)
			item.setClassroomId(str[index + 3]);
	}

	public void setStartEndTime(String startEndTime) {
		this.startEndTime = startEndTime;
	}

	public String getClassroomId() {
		return classroomId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public String getWeek() {
		return week;
	}

	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getStartEndTime() {
		return startEndTime;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return getCourseName() + " " + getTeacherName() + " " + getWeek() + " "
				+ getCourseTime() + " 课程开始-结束时间" + getStartEndTime() + " "
				+ getClassroomId();
	}
}
