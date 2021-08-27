package StudentInfo;

import java.io.Serializable;

/**
 * Student is an entity class which stores all the information of a student. <p>
 * Each student has 9 pieces of informations in total, as described below: <p>
 * key: Uniquely identifies a student <p>
 * firstName, lastName, matricNum, gender, nationality, age, email <p>
 * accessPeriod: The period of time that the student is allowed to access MySTARS <p>
 * 
 * @author Aaron Tay Han Yen
 *
 */
public class Student implements Serializable{

	private String  key ;
	private String  firstName;
	private String  lastName;
	private String  matricNum;
	private String  gender;
	private String  nationality;
	private String  age ;
	private String accessPeriod;
	private String email;
	
	/**
	 * Class constructor specifying all 9 pieces of informations of this student
	 * @param key Uniquely identifies a user in the database
	 * @param firstName firstName
	 * @param lastName lastName
	 * @param matricNum matriculation number
	 * @param gender gender
	 * @param nationality nationality
	 * @param age age
	 * @param accessPeriod accessPeriod for MYSTARS
	 * @param email the student's school email address
	 */
	public Student(String key, String firstName, String lastName, String matricNum, String gender, 
			String nationality, String age, String accessPeriod, String email)
	{
		this.key = key;
		this.firstName = firstName;
		this.lastName = lastName;
		this.matricNum = matricNum;
		this.gender = gender;
		this.nationality = nationality;
		this.age = age;
		this.accessPeriod = accessPeriod;
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAccessPeriod() {
		return accessPeriod;
	}

	public void setAccessPeriod(String accessPeriod) {
		this.accessPeriod = accessPeriod;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
