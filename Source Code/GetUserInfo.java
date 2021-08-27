package Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Student.ChangeIndex;
import StudentInfo.Student;
import StudentRecords.StudentRecords;

/**
 * GetStudentInfo displays student informations based on a some given information.
 * 
 * @author Aaron Tay Han Yen
 *
 */
public class GetUserInfo {
	//for student Interface
		/**
	 * gets the row number in the studentInfo database which a particular student is found
	 * 
	 * @param studentInfo An Arraylist of all studentInfo in the database
	 * @param key key used to identify a student
	 * @return the row number in the studentInfo database the row number in the studentInfo database
	 */
	public static int getStudentIndex(ArrayList studentInfo, String key)
	{
		for (int i = 0; i < studentInfo.size(); i++)
		{
			Student student = (Student)studentInfo.get(i);
			if(student.getKey().equals(key))
				return i;
		}
		return -1;
	}
	//for student interface
		/**
	 * gets access period of a particular student
	 * 
	 * @param studentInfo An Arraylist of all studentInfo in the database
	 * @param key key used to identify a student
	 * @return access period of student
	 */
	public static String getStudentAccessPeriod(ArrayList studentInfo, String key)
	{
		for (int i = 0; i < studentInfo.size(); i++)
		{
			Student student = (Student)studentInfo.get(i);
			if(student.getKey().equals(key))
				return student.getAccessPeriod();
		}
		return "-";
	}
}
