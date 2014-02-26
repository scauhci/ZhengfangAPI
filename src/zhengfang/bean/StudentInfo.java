package zhengfang.bean;

/**
 * 学生信息类
 * 
 * @author Vincent
 * 
 */
public class StudentInfo {
	String name; // 姓名
	String id; // 学号
	String sex; // 性别
	String birthday; // 出生日期
	String birthPlace; // 籍贯
	String idNumber; // 身份证号码
	String academy; // 学院
	String major; // 专业
	String grade; // 班级
	String address;// 家庭住址
	String phone;// 联系电话

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAcademy() {
		return academy;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public String getGrade() {
		return grade;
	}

	public String getId() {
		return id;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public String getMajor() {
		return major;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return getId() + " " + getName() + " " + getGrade() + " " + getMajor();
	}
}
