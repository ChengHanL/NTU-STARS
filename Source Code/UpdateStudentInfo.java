package StudentInfo;

import java.io.IOException;
import java.util.ArrayList;
import Login.Encrypt;
import Login.LoginInfo;
import Repository.LoginTextRepository;
import Repository.StudentPersonalTextRepository;

/**
 * UpdateStudentInfo does the manipulation the database related to 
 * students' general informations(StudentsInfo) and login informations(LoginsInfo).<p>
 * The class can:<p>
 * Adds a new entry in both the StudentsInfo and LoginsInfo database<p>
 * Update the access period of a student<p>
 * 
 * @author Aaron Tay Han Yen
 *
 */
public class UpdateStudentInfo {
	
	/**
	 * Adds a new entry in both the StudentsInfo and LoginsInfo database based on the information of
	 * the student to be added. <p>
	 * The password is encrypted and the new key is generated for the student using the Encrypt class. 
	 * 
	 * @param StudentInfoArray An ArrayList of all students' information in the StudentsInfo database 
	 * @param loginArray An ArrayListStudent of all students' login information in the LoginsInfo database
	 * @param firstName  firstName
	 * @param lastName lastName
	 * @param matricNum matricNum
	 * @param gender gender
	 * @param nationality nationality
	 * @param age age
	 * @param username username
	 * @param password password
	 * @param accessPeriod accessPeriod
	 * @param email email
	 * @throws IOException If an input or output exception occurred
	 */
	public static void addStudent(
			ArrayList StudentInfoArray, ArrayList loginArray, String firstName, String lastName, 
			String matricNum, String gender, String nationality, String age, 
			String username, String password, String accessPeriod, String email) throws IOException
	{
		String key = Encrypt.checkKey();
		String hashPassword = Encrypt.hashing(password);
		
		
		Student student = new Student(key, firstName, lastName, matricNum, gender, nationality, age, accessPeriod, email);
		StudentInfoArray.add(student) ;
		
		LoginInfo loginInfo = new LoginInfo(username, hashPassword, "Student", key);
		loginArray.add(loginInfo);
		
		StudentPersonalTextRepository studentPersonalInfoTextRepository = new StudentPersonalTextRepository();
		LoginTextRepository loginTextRepository = new LoginTextRepository();
		studentPersonalInfoTextRepository.saveList(StudentInfoArray);
		loginTextRepository.saveList(loginArray);
		
		System.out.println("******Student Info*******");
		System.out.println("FirstName: " + student.getFirstName() );
		System.out.println("LastName: " + student.getLastName() );
		System.out.println("Matric Number: " + student.getMatricNum() );
		System.out.println("Gender: " + student.getGender() );
		System.out.println("Nationality: " + student.getNationality() );
		System.out.println("Age: " + student.getAge() );
		System.out.println("Access Period: " + student.getAccessPeriod());
		System.out.println("\n");
		
		System.out.println("Student Successfully added!\n");
	}
	
	/**
	 * Updates the access period of selected students in the studentInfo database.
	 * 
	 * @param studentInfo An ArrayList of all students' information in the database
	 * @param accessPeriod The accessPeriod to be updated
	 * @param key The key of the student that the updation will be performed
	 * @throws IOException If an input or output exception occurred
	 */
	public static void updateStudentAC(ArrayList studentInfo, String accessPeriod, String key) throws IOException
	{
		for (int i = 0 ; i < studentInfo.size() ; i++) {
			Student student = (Student)studentInfo.get(i);
			if(student.getKey().equals(key))
			{
				System.out.println("Old Access Period: " + student.getAccessPeriod() + ", New Access Period: " + accessPeriod);
				student.setAccessPeriod(accessPeriod);
				System.out.println("Access Period Updated!\n");	
			}
			StudentPersonalTextRepository studentPersonalInfoTextRepository = new StudentPersonalTextRepository();
			studentPersonalInfoTextRepository.saveList(studentInfo);
		}
	}
}
