package StudentInfo;

import java.util.ArrayList;

import Login.LoginInfo;

/**
 * CheckStudentInfo checks if given matricNum/username/email already exits in the database.
 * Its methods will return true if this happens, and print a message to inform the user.
 * Otherwise, it will return false.
 * 
 * @author Aaron Tay Han Yen
 *
 */
public class CheckStudentInfo {
	
	/**
	 * Checks if given matricNum already exits in the database.
	 * 
	 * @param matricNum matriculation number
	 * @param studentInfo An ArrayList of all students' information in the studentInfo database
	 * @return true if the given matricNum already exits in the database, false if otherwise
	 */
	public static boolean checkMatricNum(String matricNum, ArrayList studentInfo)
	{
		for(int i=0; i<studentInfo.size(); i++)
		{
			Student student = (Student)studentInfo.get(i);
			if(student.getMatricNum().equals(matricNum))
			{
				System.out.println("Matric Number already Exist.\n");
				return true;
			}	
		}
		return false;
	}
	
	/**
	 * Checks if given username already exits in the database.
	 * 
	 * @param username username of the student
	 * @param LoginArray An ArrayList of all students' login information in the LoginsInfo database
	 * @return true if the given user already exits in the database, false if otherwise
	 */
	public static boolean checkUsername(String username, ArrayList LoginArray)
	{
		for(int i=0; i<LoginArray.size(); i++)
		{
			LoginInfo loginInfo = (LoginInfo)LoginArray.get(i);
			if(loginInfo.getUsername().equals(username))
			{
				System.out.println("Username already Exist.\n");
				return true;
			}		
		}
		return false;
		
	}
	
	/**
	 * Checks if given email already exits in the database.
	 * 
	 * @param email the email of the student
	 * @param studentInfo An ArrayList of all students' information in the studentInfo database
	 * @return true if the given email already exits in the database, false if otherwise
	 */
	public static boolean checkEmail(String email, ArrayList studentInfo)
	{
		for(int i=0; i<studentInfo.size(); i++)
		{
			Student student = (Student)studentInfo.get(i);
			if(student.getEmail().equals(email))
			{
				System.out.println("Email already Exist.\n");
				return true;
			}	
		}		
		return false;
	}
}
