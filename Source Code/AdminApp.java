package Interface;
import CourseRecords.UpdateCourseRecord;
import Repository.CourseRecordsTextRepository;
import Repository.DatDatabase;
import Repository.GlobalAccessPeriodTextRepository;
import Repository.LoginTextRepository;
import Repository.StudentRecordTextRepository;
import Student.ChangeIndex;
import Repository.StudentPersonalTextRepository;
import CourseRecords.CheckCourseRecord;
import CourseRecords.GetCourseRecord;
import StudentInfo.CheckStudentInfo;
import StudentInfo.GetStudentInfo;
import StudentInfo.UpdateStudentInfo;
import StudentRecords.GetStudentRecord;
import StudentRecords.UpdateStudentRecord;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import AccessPeriod.GetAccessPeriod;

/**
 * AdminApp is a boundary class which interacts with the admin to perform any operation on MySTARS
 * in the admin mode.<p>
 * There are 8 options in admin mode: <p>
	 * 1. Edit student Access Period <p>
	 * 2. Add a student <p>
	 * 3. Add a Course <p>
	 * 4. Update a course <p>
	 * 5. Print Student List by Index Number <p>
	 * 6. Print Student List by Course Index <p>
	 * 7. Check Avaliable Vacancy for an index number <p>
	 * 8. logout <p>
 * 
 * @author Aaron Tay Han Yen
 *
 */
public class AdminApp {
	private String choice = "0";
	

	private  static ArrayList courseRecord;
	private  static ArrayList studentInfo;
	private  static ArrayList loginInfo;
	private  static ArrayList studentRecord;
	private  static ArrayList accessPeriodList;

	Scanner sc = new Scanner(System.in);
	/**
	 * Displays a menu for the admin, and performs the appropriate operation based on the admin's choice.<p>
	 * There are 8 possible choices in the menu: <p>
	 * 1. Edit student Access Period <p>
	 * 2. Add a student <p>
	 * 3. Add a Course <p>
	 * 4. Update a course <p>
	 * 5. Print Student List by Index Number <p>
	 * 6. Print Student List by Course Index <p>
	 * 7. Check Avaliable Vacancy for an index number <p>
	 * 8. logout <p>
	 * The database(Serial Files) are updated and read into the current program.<p>
	 * 
	 * @throws IOException If an input or output exception occurs
	 */
	public void adminInterface() throws Exception
	{	
		CourseRecordsTextRepository courseRecordsTextRepository = new CourseRecordsTextRepository();
		StudentPersonalTextRepository studentPersonalInfoTextRepository = new StudentPersonalTextRepository();
		LoginTextRepository loginTextRepository = new LoginTextRepository();
		StudentRecordTextRepository studentCoursesTextRepository = new StudentRecordTextRepository();
		GlobalAccessPeriodTextRepository globalAccessPeriodTextRepository = new GlobalAccessPeriodTextRepository();
		DatDatabase datDatabase = new DatDatabase();

		while(!choice.equals("8"))
		{
			//get Data from txtfile - can be commented out in final Version
			courseRecord = courseRecordsTextRepository.readToList();
			studentInfo = studentPersonalInfoTextRepository.readToList();
			loginInfo = loginTextRepository.readToList();
			studentRecord = studentCoursesTextRepository.readToList();
			accessPeriodList = globalAccessPeriodTextRepository.readToList();
			
			
			//write data to Dat file - can be commented out in final Version
			datDatabase.write("CourseRecords.dat", courseRecord);
			datDatabase.write("StudentInfo.dat", studentInfo);
			datDatabase.write("LoginsInfo.dat", loginInfo);
			datDatabase.write("StudentRecords.dat", studentRecord);
			datDatabase.write("GlobalAccessPeriod.dat", accessPeriodList);
			
			//read again from Serial File
 			courseRecord = (ArrayList)datDatabase.read("CourseRecords.dat");
			studentInfo = (ArrayList)datDatabase.read("StudentInfo.dat");
			loginInfo = (ArrayList)datDatabase.read("LoginsInfo.dat");
			studentRecord = (ArrayList)datDatabase.read("StudentRecords.dat");
			accessPeriodList = (ArrayList)datDatabase.read("GlobalAccessPeriod.dat");
						
 			
			System.out.println("****ADMIN INTERFACE****");
			System.out.println("1. Edit Student Access Period");
			System.out.println("2. Add a Student");
			System.out.println("3. Add a Course");
			System.out.println("4. Update Course");
			System.out.println("5. Print Student List by Index Number");
			System.out.println("6. Print Student List by Course Index");
			System.out.println("7. Check Avaliable Vacancy By Index Number");
			System.out.println("8. Logout");
			System.out.printf("Please choose a number: ");

			choice = sc.next();
			
			switch(choice)
			{
				case "1":
					editStudentAccess();
					break;
				case "2":
					addStudent();
					break;
				case "3":
					addCourse();
					break;
				case "4":
					updateCourse();
					break;
				case "5":
					printStudentListByIndexNumber();
					break;
				case "6":
					printStudentListByCourseIndex();
					break;
				case "7":
					printCourseVacancy();
					break;
				case "8":
					System.out.println("Goodbye!\n");
					LoginInterface loginInterface = new LoginInterface();
					loginInterface.loginAs();
					break;
				default:
					System.out.println("Please Enter a Valid Number!\n");
					break;
			}
		}
	}
	
	//case 1
	/**
	 * Prompts user to enter the information of a student and edits the student's access period to the database. 
	 * 
	 * @throws IOException If an input or output exception occurred
	 */
	private void editStudentAccess() throws IOException
	{	
		System.out.println("\n******EditStudent Access Period******");
		System.out.printf("Enter Student's Matric Number: ");
		String matricNum = sc.next().toUpperCase();
		String key = GetStudentInfo.getStudentKey(studentInfo, matricNum);
		String startAccessPeriod, endAccessPeriod, accessPeriod;
		if(key != "nill")
		{
			GetStudentInfo.printStudentInfo(studentInfo, key);
			System.out.printf("Enter Starting Access Period to change to (DD/MM/YYYY):");
			//enter date, day, year
			startAccessPeriod = sc.next();
			System.out.printf("Enter Ending Access Period to change to (DD/MM/YYYY):");
			endAccessPeriod = sc.next();
			accessPeriod = startAccessPeriod + " - " + endAccessPeriod;
			UpdateStudentInfo.updateStudentAC(studentInfo, accessPeriod, key);
		}
		else
			System.out.println("No matching Matric Number!");
	}
	
	//case 2
	/**
	 * Prompts user to enter the information of the student and add him/her into the database.<p>
	 * Password encryption is done and a new key is generated.
	 * 
	 * @throws IOException If an input or output exception occurred
	 */
	private void addStudent() throws IOException
	{
		System.out.printf("Enter student First Name: ");
		String firstName = sc.next();
		System.out.printf("Enter student Last Name: ");
		String lastName = sc.next();
		//check duplicate 
		String matricNum;
		while(true) {
			System.out.printf("Enter student Matric Number: ");
			matricNum = sc.next().toUpperCase();
			if(CheckStudentInfo.checkMatricNum(matricNum, studentInfo) == false) 
					break;
		}	
		System.out.printf("Enter student Gender: ");
		String gender =  sc.next();
		System.out.printf("Enter student Nationality: ");
		String nationality = sc.next();
		System.out.printf("Enter student Age: ");
		String age = sc.next();
		System.out.printf("Student Login Info: ");
		//check duplicate
		String email;
		while(true) {
			System.out.printf("Enter Student email:");
			email = sc.next().toLowerCase();
			if(CheckStudentInfo.checkEmail(email, studentInfo) == false)
				break;
		}
		String username;
		//check duplicate
		while(true) {
			System.out.printf("Enter Student login Username: ");
			username = sc.next().toLowerCase();
			if(CheckStudentInfo.checkUsername(username, loginInfo) == false) 
					break;
		}	
		System.out.printf("Enter student password: ");
		String password = sc.next();
		GetAccessPeriod.printAccessPeriod(accessPeriodList);
		String accessPeriod;
		//check dupicate
		while(true) {
			System.out.printf("\nChoose a value which Access Period you want to add: ");
			accessPeriod = sc.next();
			accessPeriod = GetAccessPeriod.getAccessPeriod(accessPeriodList, accessPeriod);
			if(!accessPeriod.equals("nill"))
				break;	
		}
		//create student login info and Student Info
		UpdateStudentInfo.addStudent(studentInfo, loginInfo, firstName, lastName, matricNum, gender, nationality, age, username, password, accessPeriod, email);
	}
	
	//case 3
	/**
	 * Prompts user to enter the information of a course and add it into the database.<p>
	 * 
	 * @throws IOException If an input or output exception occurred
	 */
	private void addCourse() throws IOException
	{
		String courseIndex, indexNum, AU, SU, GERType;
		//cannot add if already exist
		while(true)
		{
			System.out.printf("Enter the course Index you want to add: ");
			courseIndex = sc.next().toUpperCase();
			System.out.printf("Enter Index Number: ");
			indexNum = sc.next();
			if(CheckCourseRecord.checkDuplicate(courseRecord, courseIndex, indexNum) == false)
				break;
			System.out.println("Try again!\n");
		}

		String num;
		//check string is a number
		while(true)
		{
			System.out.printf("How many rows you want to add:");
			 num = sc.next();
			if( num.matches("-?\\d+(\\.\\d+)?") == true)
				break;
		}		
		
		int number = Integer.parseInt(num);
		for(int i=0; i < number; i++)
		{
			System.out.printf("Enter Type: ");
			String type = sc.next();
			System.out.printf("Enter Group Index: ");
			String group = sc.next();
			System.out.printf("Enter Day: ");
			String day = sc.next();
			System.out.printf("Enter time: ");
			String time = sc.next();
			System.out.printf("Enter Venue: ");
			String venue = sc.next();
			System.out.printf("Enter Any remarks you want to add(enter '-' if nothing): ");
			String remarks = sc.next();
			System.out.printf("Enter Vacancy number: ");
			String vacancy = sc.next();
			System.out.printf("Name of the Course: ");
			String courseName = sc.next();
			while(true)
			{
				System.out.printf("Enter AU (1,2,3): ");
				AU = sc.next();
				if(AU.equals("1") || AU.equals("2") || AU.equals("3"))
					break;
				else 
					System.out.println("Invalid Input");
			}
			
			
			System.out.printf("Enter Course Type: ");
			String courseType = sc.next();
			while(true)
			{
				System.out.printf("Enter SU (Yes or No): ");
				SU = sc.next();
				if(SU.equals("Yes") || SU.equals("No"))
					break;
				else 
					System.out.println("Invalid Input");
			}
			while(true)
			{
				System.out.printf("Enter GERType (BM or LA or STS): ");
				GERType = sc.next();
				if(GERType.equals("BM") || GERType.equals("LA") || GERType.equals("STS"))
					break;
				else 
					System.out.println("Invalid Input");
			}
			//add course
			num = Integer.toString(number);
			UpdateCourseRecord.addCourse(courseRecord, num, courseIndex, indexNum, type, group, day, time, venue, remarks, vacancy, courseName, AU, courseType, SU,GERType);
		}
	}
	
	//case 4
	/**
	 * Prompts user to select a course to edit, key in the new course's information and update the database.
	 * 
	 * @throws If an input or output exception occurred
	 */
	private void updateCourse() throws Exception
	{
		GetCourseRecord.printCourseCode(courseRecord);
		String courseIndex, indexNum;
		//check duplicate
		while(true)
		{
			System.out.printf("Enter Course Number you want to Edit: ");
			courseIndex = sc.next().toUpperCase();
			System.out.printf("Enter Index Number you want to Edit: ");
			indexNum = sc.next();
			if(CheckCourseRecord.checkCourse(courseRecord, courseIndex, indexNum) == true)
				break;
		}
		GetCourseRecord.printCourseCode(courseRecord, courseIndex, indexNum);
		
		String num;
		while(true)
		{
			System.out.printf("Which No. you want to Edit: ");
			num = sc.next();
			if(CheckCourseRecord.checkNum(courseRecord, num, courseIndex, indexNum) == true)
				break;
		}
		String vacancy = "-";
		
		System.out.printf("Enter Type: ");
		String type = sc.next();
		System.out.printf("Enter Group: ");
		String group = sc.next();
		System.out.printf("Enter Day: ");
		String day = sc.next();
		System.out.printf("Enter time: ");
		String time = sc.next();
		System.out.printf("Enter venue: ");
		String venue = sc.next();
		System.out.printf("Enter remarks(enter '-' if none): ");
		String remarks = sc.next();
		System.out.printf("Enter AU: ");
		String AU = sc.next();
		System.out.printf("Enter Course Type: ");
		String CourseType = sc.next();
		System.out.printf("Enter SU Option: ");
		String SU = sc.next();
		System.out.printf("Enter GER Type: ");
		String GERType = sc.next();
		if(num.equals("1"))
		{
			System.out.printf("Enter Vacancy: ");
			vacancy = sc.next();
		}
		//update Course Record
		UpdateCourseRecord.editCourse(courseRecord,  num, courseIndex, indexNum, type,  group, 
				day, time, venue, remarks, vacancy, AU, CourseType, SU, GERType);
		//update Student Record
		UpdateStudentRecord.updateStudentRecord(studentRecord, courseIndex, indexNum, AU);
	}
	
	//case 5
	/**
	 * prompts user to select an indexNum (eg. 10088)
	 * from the list of all possible indexNum and displays all students who have registered for a course 
	 * under this index.
	 * 
	 * @throws IOException If an input or output exception occurred
	 */
	private void printStudentListByIndexNumber() throws IOException {
		// TODO Auto-generated method stub
		List aList = GetCourseRecord.getIndexList(courseRecord); 
		System.out.printf("Enter Index Number to check list of Student:");
		String indexNum = sc.next();
		if (aList.contains(indexNum) == true)
			GetStudentRecord.printStudentListByCourse(studentRecord, studentInfo, indexNum);
		else
			System.out.println("Error! No such Index Number.\n");
	}
	
	//case 6
	/**
	 * prompts user to select an CourseIndex (eg. CZ1003)
	 * from the list of all possible CourseIndex and displays all students who have registered for a course 
	 * under this index.
	 * 
	 * @throws IOException If an input or output exception occurred
	 */
	private void printStudentListByCourseIndex() throws IOException{
		// TODO Auto-generated method stub
		List cList = GetCourseRecord.getCourseList(courseRecord);
		System.out.printf("Enter Course Index to check list of Student:");
		String courseIndex = sc.next().toUpperCase();
		if(cList.contains(courseIndex) == true)
			GetStudentRecord.printStudentListByCourse(studentRecord, studentInfo, courseIndex);
		else
			System.out.println("Error! No such Course");
	}
	
	//case 7
	/**
	 * Prompts user to enter a indexNum and displays the vacancies 
	 * and other course informations for courses with this indexNum.
	 * 
	 * @throws IOException If an input or output exception occurred
	 */
	private void printCourseVacancy() throws IOException
	{
		List aList = GetCourseRecord.getIndexList(courseRecord); 
		GetCourseRecord.printIndexNum(courseRecord); 
		System.out.printf("\nEnter Index Number to check avaliable Vacancy: ");
		String indexNum = sc.next();
		if (aList.contains(indexNum) == true)
			GetCourseRecord.printVacancy(indexNum, courseRecord);
		else
			System.out.println("Error! No such Index Number.\n");

	}
}
