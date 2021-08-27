package CourseRecords;

import java.io.Serializable;

/**
 * CourseRecord is an entity class which stores all information of a course. <p>
 * Each course has pieces of 15 informations in total, as described below: <p>
 * num a number useful for identifying a courseIndex and index pair (eg. CZ1003|10088) 
 * when multiple same courseIndex and index pairs are present. <p> For example, num = 1
 * for CZ1003|10088|LAB and num = 2 for CZ1003|10088|TUT<p>
 * courseIndex(eg. CZ1003) <p>
 * indexNum(eg. 10088) <p>
 * type(eg. LEC/TUT/LAB).<p>
 * group(eg. CS1, SS2).<p>
 * day, time, venue. <p>
 * name of the course. <p>
 * vacancy. <p>
 * remarks. <p>
 * AU. AU of the course <p>
 * SU. SU option <p>
 * CourseType. EG Core<p>
 * GERType. EG BM<p>
 * Each information has its respective get and set method.
 * 
 * @author Aaron Tay Han Yen
 *
 */
public class CourseRecord implements Serializable{
	
	private String  num;
	private String  courseIndex;
	private String  indexNum;
	private String  type;
	private String  day;
	private String  group;
	private String  time;
	private String  venue ;
	private String  remarks;
	private String  vacancy; 
	private String  name;
	private String  AU;
	private String  CourseType;
	private String  SU;
	private String  GERType;
	
	/**
	 * Class constructor specifying all 15 course informations.
	 * 
	 * @param courseIndex (eg. CZ1003)
	 * @param indexNum (eg. 10088)
	 * @param type type of this course (eg. LEC/TUT/LAB)
	 * @param group group number of this course (eg. SS2)
	 * @param day the weekday that this course is conducted
	 * @param time the time that this course is conducted
	 * @param venue the venue that this course is conducted
	 * @param remarks any remark for this course
	 * @param vacancy number of available slots left for this course
	 * @param name name of this course
	 * @param AU AU of the course 
	 * @param SU SU option
	 * @param CourseType CourseType(EG Core)
	 * @param GERType GERType(EG BM)
	 * @param num a number useful for identifying a courseIndex and index pair (eg. CZ1003|10088) 
	 * when multiple same courseIndex and index pairs are present. For example, num = 1
	 * for CZ1003|10088|LAB and num = 2 for CZ1003|10088|TUT
	 */
	public CourseRecord(String num, String courseIndex, String indexNum, String type, String group, String day, String time, String venue, 
			String remarks, String vacancy, String name, String AU,String CourseType,String SU, String GERType)
	{
		this.num = num;
		this.courseIndex = courseIndex;
		this.indexNum = indexNum;
		this.type = type;
		this.group = group;
		this.day = day;
		this.time = time;
		this.venue = venue;
		this.remarks = remarks; 
		this.vacancy = vacancy;
		this.name = name;
		this.AU = AU;
		this.CourseType = CourseType;
		this.SU = SU;
		this.GERType = GERType;

	}

	public String getAU() {
		return AU;
	}

	public void setAU(String AU) {
		this.AU = AU;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCourseIndex() {
		return courseIndex;
	}

	public void setCourseIndex(String courseIndex) {
		this.courseIndex = courseIndex;
	}

	public String getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(String indexNum) {
		this.indexNum = indexNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getVacancy() {
		return vacancy;
	}

	public void setVacancy(String vacancy) {
		this.vacancy = vacancy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseType() {
		return CourseType;
	}

	public void setCourseType(String CourseType) {
		this.CourseType = CourseType;
	}
	public String getSU() {
		return SU;
	}

	public void setSU(String SU) {
		this.SU = SU;
	}
	public String getGERType() {
		return GERType;
	}

	public void setGERType(String GERType) {
		this.GERType = GERType;
	}
	
	

}
