package Interface;

import Student.*;
import StudentInfo.GetStudentInfo;
import StudentRecords.CheckStudentRecord;
import StudentRecords.GetStudentRecord;
import StudentRecords.StudentRecords;
import WaitList.GetWaitList;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Calendar.MyCalendar;
import CourseRecords.*;
import Repository.CourseRecordsTextRepository;
import Repository.DatDatabase;
import Repository.GlobalAccessPeriodTextRepository;
import Repository.StudentRecordTextRepository;
import Repository.WaitListTextRepository;
import Repository.StudentPersonalTextRepository;

/**
 * StudentInterface is a boundary class which interacts with the student to perform any operation on MySTARS
 * in the student mode.<p>
 * There are 6 options in student mode: <p>
 * 1. Add a Course <p>
 * 2. Drop a Course <p>
 * 3. Check/Print Courses Registered <p>
 * 4. Check Vacancies Available <p>
 * 5. Change Index Number of Course <p>
 * 6. Swap Index Number with Another Student <p>
 * 7. Log out
 * 
 * @author 1 Samantha Tan Swee Yun
 */
public class StudentInterface {
	private String choice = "0";
	
	private static ArrayList courseRecordList;
	private static ArrayList studentInfo;
	private static ArrayList waitList;
	private static ArrayList studentsRecords;
	private static ArrayList accessPeriodList;
	
	static Scanner sc = new Scanner(System.in);

	/**
	 * Reads in data from the database, prompts user to select a choice from the menu and passes 
	 * control to the respective class/method to perform the tasks.
	 * 
	 * @param key The key used to identify a student 
	 * @throws IOException If an input or output exception occurs
	 * @throws ParseException if ParseException occurs
	 */
	public void inStudentInterface(String key) throws IOException, ParseException
	{
		CourseRecordsTextRepository courseRecordsTextRepository = new CourseRecordsTextRepository();
		StudentPersonalTextRepository studentPersonalInfoTextRepository = new StudentPersonalTextRepository();
		StudentRecordTextRepository studentCoursesTextRepository = new StudentRecordTextRepository();
		WaitListTextRepository waitListTextRepository = new WaitListTextRepository();
		GlobalAccessPeriodTextRepository globalAccessPeriodTextRepository = new GlobalAccessPeriodTextRepository();
		DatDatabase datDatabase = new DatDatabase();

		while (!choice.equals("7")) {
			courseRecordList = courseRecordsTextRepository.readToList(); 
			studentInfo = studentPersonalInfoTextRepository.readToList();
			waitList = waitListTextRepository.readToList();
			studentsRecords = studentCoursesTextRepository.readToList();
			accessPeriodList = globalAccessPeriodTextRepository.readToList();
			
			
			datDatabase.write("CourseRecords.dat", courseRecordList);
			datDatabase.write("StudentInfo.dat", studentInfo);
			datDatabase.write("WaitList.dat", waitList);
			datDatabase.write("StudentRecords.dat", studentsRecords);
			datDatabase.write("GlobalAccessPeriod.dat", accessPeriodList);
			
			//read again from Serial File
			courseRecordList = (ArrayList)datDatabase.read("CourseRecords.dat");
			studentInfo = (ArrayList)datDatabase.read("StudentInfo.dat");
			waitList = (ArrayList)datDatabase.read("waitList.dat");
			studentsRecords = (ArrayList)datDatabase.read("StudentRecords.dat");
			accessPeriodList = (ArrayList)datDatabase.read("GlobalAccessPeriod.dat");
			
			String accessPeriod = GetUserInfo.getStudentAccessPeriod(studentInfo, key);
			boolean canAccess = true;
			//canAccess = MyCalendar.checkAccessPeriod(accessPeriod);
			if (canAccess == false)
			{
				System.out.println("Cannot Access STARS");
				return;
			}
			
			
			System.out.println("****STUDENT INTERFACE****");
			System.out.println("1. Add Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check/Print Courses Registered");
			System.out.println("4. Check Vacancies Available");
			System.out.println("5. Change Index Number of Course");
			System.out.println("6. Swap Index Number with Another Student");
			System.out.println("7. Logout");
			System.out.printf("Please choose a number: ");
			
			choice = sc.next();

			switch(choice)
			{
				case "1":
					addCourse(key);
					break;
				case "2":
					dropCourse(key);
					break;
				case "3":
					printCourseDetails(key);
					break;
				case "4":
					getCourseVacancy();
					break;
				case "5":
					changeIndex(key);
					break;
				case "6":
					SwapIndex(key);
					break;
				case "7":
					System.out.println("Goodbye!\n");
					LoginInterface loginInterface = new LoginInterface();
					loginInterface.loginAs();
					break;
				default:
					System.out.println("Invalid Number!\n");
					break;
			}	
		}
	}
	
	//case 1
	/**
	 * Prompts user to enter a courseIndex and adds the course for the student into the 
	 * StudentRecords database.
	 * 
	 * @param key The key used to identify a student
	 * @throws IOException If an input or output exception occurs
	 */
	private static void addCourse(String key) throws IOException{
		String courseIndex, indexNum;;
		
		System.out.println("*****************Course List*****************");
		GetCourseRecord.printCourseCode(courseRecordList);
		
		System.out.printf("Enter Course Index:");
		courseIndex = sc.next().toUpperCase();
		System.out.printf("Enter Index Number: "); 
		indexNum = sc.next();
		
		//get index of courseRecord
		int index = GetCourseRecord.returnCourseIndex(courseRecordList, courseIndex, indexNum);
		//check course already or not
		boolean alreadyExistCourse = CheckStudentRecord.checkStudent(studentsRecords, courseIndex, key);
		//check timing
		//boolean test = CheckStudentRecord.checkTiming(courseRecordList, studentsRecords, courseIndex, indexNum, key);
		if(index != -1 && alreadyExistCourse == false)
			AddCourse.addCourse(studentsRecords, courseRecordList, studentInfo, waitList, key, courseIndex, indexNum, index);
	}
	
	//case 2
	/**
	 * Prompts user to enter a indexNum to drop and drops the relevant entries from the
	 * StudentRecords database.
	 * 
	 * @param key The key used to identify a student
	 * @throws IOException If an input or output exception occurs
	 */
	private static void dropCourse(String key) throws IOException {
		List aList = ChangeIndex.getIndexList(studentsRecords, key);
		System.out.printf("Enter Course Number:");
		String courseIndex = sc.next();
		System.out.printf("Enter Index Numer:");
		String indexNum = sc.next();
		boolean hasQueue = GetWaitList.hasQueue(waitList, courseIndex, indexNum);
		if (aList.contains(courseIndex) == true)
			DropCourse.dropCourse(studentsRecords, courseRecordList, waitList, key, courseIndex, indexNum, hasQueue);
		else
			System.out.println("You are not registered for this index.\n");
	}
	
	//case 3
	/**
	 * Displays the detail of all courses registered by the student and his total AU. 
	 * 
	 * @param key The key used to identify a student
	 * @throws IOException If an input or output exception occurs
	 */
	private static void printCourseDetails(String key) throws IOException {
		GetStudentRecord.getCourseDetails(studentsRecords, key);	
	}

	//case 4
	/**
	 * Prompts user to enter a indexNum and
	 * displays the vacancies and other course informations for this indexNum.
	 * 
	 * @throws IOException If an input or output exception occurs
	 */
	private static void getCourseVacancy() throws IOException
	{
		List aList = GetCourseRecord.getIndexList(courseRecordList); 
		System.out.println("Enter Index Number:");
		String indexNum = sc.next();
		if (aList.contains(indexNum) == true)
			GetCourseRecord.printVacancy(indexNum, courseRecordList);
		else
			System.out.println("Error! No such Index Number.\n");
	}
	
	//case 5
	/**
	 * Prompts user to enter the indexNums to swap for a course he registered and swap them.
	 * 
	 * @param key The key used to identify a student
	 * @throws IOException If an input or output exception occurs
	 */
	private static void changeIndex(String key) throws IOException
	{
		List aList = ChangeIndex.getIndexList(studentsRecords, key); // index of registered course for student
		
		System.out.println("Enter Old Index Number:");
		String oldIndex = sc.next();
		
		if (aList.contains(oldIndex) == true) {
			List bList = ChangeIndex.getIndexListByCourse(courseRecordList,oldIndex); // list of all index in specific course
			
			System.out.println("Enter New Index Number:");
			String newIndex = sc.next();
			
			if(bList.contains(newIndex) == true){
				ChangeIndex.changeIndex(studentsRecords,courseRecordList, oldIndex, newIndex, key);
			} else {
				System.out.println("Error! No such Index Number in Course.\n");
			}		
		} else {
			System.out.println("You are not registered for this index.\n");
		}

	}
	
	//case 6
	/**
	 * Prompts user to enter the indexNums to swap and his friend's matriculation number and 
	 * swap their indexes.
	 * 
	 * @param key The key used to identify a student
	 * @throws IOException If an input or output exception occurs
	 */
	private void SwapIndex(String key)  throws IOException
	{
		List aList = ChangeIndex.getIndexList(studentsRecords, key);
		String matricNum;
		System.out.println("Student #1");
		for (int i = 0 ; i < studentsRecords.size() ; i++) {
			StudentRecords studentRecord = (StudentRecords)studentsRecords.get(i);
			if(studentRecord.getKey().equals(key)) {
				matricNum = studentRecord.getMatricNum();
				System.out.println("Your Matric Number: "+ matricNum + "\n");
				break;
				}	
		}
		System.out.println("Enter Your Index Number:");
		String oldIndex = sc.next();
		if (aList.contains(oldIndex) == true) {
			List mList = SwapIndex.getMatricList(studentInfo);
	
			System.out.println("Student #2");
			System.out.println("Enter Friend Matric Number:");
			String MatricNum = sc.next();
			
			if (mList.contains(MatricNum)) {
				System.out.println("Enter Your friend's Index Number:");
				String newIndex = sc.next();
				// list of index in of a particular course
				List bList = ChangeIndex.getIndexListByCourse(courseRecordList,oldIndex);
				// list of index registered by friend 
				List sList = SwapIndex.getIndexList(studentsRecords, MatricNum); 
				
				if(bList.contains(newIndex) == true )
					if(sList.contains(newIndex) == true )
						SwapIndex.swapIndex(studentsRecords,courseRecordList,MatricNum, oldIndex, newIndex, key);
					else
						System.out.println("Your friend is not registered for this index");
				else 
					System.out.println("This index is not registered in same course");
			} else {
				System.out.println("No such student.");
			}
			

		}
		else {
			System.out.println("You are not registered for this index.\n");
		}
	}

}
