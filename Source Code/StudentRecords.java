package StudentRecords;

import java.io.Serializable;

/**
 * StudentRecords is an entity class which stores the information of a course taken by a student,
 * and the student's identity informations. <p>
 * Each entry has 11 pieces of informations, as described below:<p>
 * key: identifies the student who takes the course <p>
 * firstName of student <p>
 * lastName of student  <p>
 * matricNum of student <p>
 * indexNum: eg 10088 <p>
 * courseIndex: eg CZ1003 <p>
 * AU: number of AU of the course<p>
 * courseType: eg. LEC/TUT/LAB <p>
 * SU: SU option <p>
 * GERType: eg CORE <p>
 * status: REGISTERED/WAITLIST <p>
 *  queue of the course <p>
 *
 * @author Aaron Tay Han Yen
 *
 */
public class StudentRecords implements Serializable{
	private String key ;
	private String firstName;
	private String lastName;
	private String matricNum;
	private String indexNum;
	private String courseIndex;
	private String AU;
	private String courseType;
	private String SU;
	private String GERType;
	private String status;
	private String queue;
	
/**
 * Class constructor specifying all informations of a StudentRecords
 * 
 * @param key identifies the student who takes the course
 * @param firstName firstName of student
 * @param lastName lastName of student
 * @param matricNum matricNum of student
 * @param courseIndex courseIndex: eg CZ1003
 * @param indexNum indexNum: eg 10088
 * @param AU number of AU of the course
 * @param courseType courseType: eg. LEC/TUT/LAB
 * @param SU SU option
 * @param GERType GERType: eg CORE
 * @param status status: REGISTERED/WAITLIST
 * @param queue queue of the course
 */
	public StudentRecords(String key, String firstName, String lastName, String matricNum, String courseIndex,String indexNum,
			String AU, String courseType, String SU, String GERType, String status, String queue)
	{
		this.key = key;
		this.firstName = firstName;
		this.lastName = lastName;
		this.matricNum = matricNum;
		this.indexNum = indexNum;
		this.courseIndex = courseIndex;
		this.AU = AU;
		this.courseType = courseType;
		this.SU = SU;
		this.GERType = GERType;
		this.status = status;
		this.queue = queue;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getAU() {
		return AU;
	}

	public void setAU(String AU) {
		this.AU = AU;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMatricNum() {
		return matricNum;
	}

	public void setMatricNum(String matricNum) {
		this.matricNum = matricNum;
	}

	public String getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(String indexNum) {
		this.indexNum = indexNum;
	}

	public String getCourseIndex() {
		return courseIndex;
	}

	public void setCourseIndex(String courseIndex) {
		this.courseIndex = courseIndex;
	}
	
	public String getStatus() {
		return status;
	}
}
