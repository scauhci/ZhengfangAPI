package zhengfang.bean;

/**
 * 成绩类
 * 
 * @author Vincent
 * 
 */
public class ReportCartItem {
	String year; // 学年
	String term; // 学期
	String courseCode;// 课程代码
	String courseName; // 课程名字
	String courseProperty; // 课程性质
	String credit; // 学分
	String point; // 绩点
	String regularGrade;// 平时成绩
	String midtermGrade;// 期中成绩
	String finalGrade;// 期末成绩
	String grade;// 成绩
	String academy;// 开课学院

	private static String[] names = { "学年", "学期", "课程代码", "课程名称", "课程性质",
			"课程归属", "学分", "绩点", "平时成绩", "期中成绩", "期末成绩", "实验成绩", "成绩", "辅修标记",
			"补考成绩", "重修成绩", "开课学院", "备注", "重修标记" };

	public static void setReportCardItem(ReportCartItem item, int index,
			String string) {

		switch (index) {
		case 0:
			item.setYear(string);
			break;
		case 1:
			item.setTerm(string);
			break;
		case 2:
			item.setCourseCode(string);
			break;
		case 3:
			item.setCourseName(string);
			break;
		case 4:
			item.setCourseProperty(string);
			break;
		case 6:
			item.setCredit(string);
			break;
		case 7:
			item.setPoint(string);
			break;
		case 8:
			item.setRegularGrade(string);
			break;
		case 9:
			item.setMidtermGrade(string);
			break;
		case 10:
			item.setFinalGrade(string);
			break;
		case 12:
			item.setGrade(string);
			break;
		case 16:
			item.setAcademy(string);
			break;
		default:
			break;
		}

	}

	public void setMidtermGrade(String midtermGrade) {
		this.midtermGrade = midtermGrade;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public String getMidtermGrade() {
		return midtermGrade;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public void setCourseProperty(String courseProperty) {
		this.courseProperty = courseProperty;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public void setRegularGrade(String regularGrade) {
		this.regularGrade = regularGrade;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCourseProperty() {
		return courseProperty;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCredit() {
		return credit;
	}

	public String getFinalGrade() {
		return finalGrade;
	}

	public String getGrade() {
		return grade;
	}

	public String getAcademy() {
		return academy;
	}

	public String getPoint() {
		return point;
	}

	public String getRegularGrade() {
		return regularGrade;
	}

	public String getTerm() {
		return term;
	}

	public String getYear() {
		return year;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return getYear() + " " + getTerm() + " " + getCourseCode() + " "
				+ getCourseName() + " " + getCourseProperty() + " "
				+ getCredit() + " " + getPoint() + " " + getRegularGrade()
				+ " " + getMidtermGrade() + " " + getFinalGrade() + " "
				+ getGrade() + " " + getAcademy();
	}

	public static String[] getNames() {
		return names;
	}
}
