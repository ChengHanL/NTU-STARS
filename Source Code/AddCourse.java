package Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import CourseRecords.CourseRecord;
import Repository.CourseRecordsTextRepository;
import Repository.DatDatabase;
import Repository.StudentRecordTextRepository;
import SendMailTLS.SendMailTLS;
import StudentInfo.GetStudentInfo;
import StudentInfo.Student;
import StudentRecords.CheckStudentRecord;
import StudentRecords.StudentRecords;
import WaitList.GetWaitList;

/**
 * AddCourse implements the logic of adding a course
 * for the student if the student enters a valid courseIndex.
 * 
 * @author Aaron
 *
 */
public class AddCourse{
	/**
	 * add a course for the student
	 * 
	 * @param studentsRecords An ArrayList of all studentsRecords from the database
	 * @param courseRecordList An ArrayList of all courseRecords from the database
	 * @param studentInfo An ArrayList of all studentInfo from the database
	 * @param waitList waitList
	 * @param key key used to identify a student
	 * @param courseIndex courseIndex
	 * @param indexNum indexNum
	 * @param index index
	 * @throws IOException if input or output exception occurs
	 */
	public static void addCourse(ArrayList studentsRecords, ArrayList courseRecordList, ArrayList studentInfo, ArrayList waitList, String key, String courseIndex, String indexNum, int index) throws IOException
	{

		//print non Distinct information
		for (int i=1; i<courseRecordList.size(); i++) { 
			CourseRecord courseRecords = (CourseRecord)courseRecordList.get(i);	
			if(courseRecords.getCourseIndex().equals(courseIndex) && courseRecords.getIndexNum().equals(indexNum)) 
			{
				System.out.println("Course Index: "+courseRecords.getCourseIndex() + ", Index Number: " + courseRecords.getIndexNum()+ ", Day:" +
			courseRecords.getDay()+ ", Time:" + courseRecords.getTime()+", Type: "+ courseRecords.getType() + ", Group: " + courseRecords.getGroup() + ", venue: " + courseRecords.getVenue()
			+ ", Remarks: " + courseRecords.getRemarks()); 		  
			}
		}
		//print Distinct information
		System.out.println();
		CourseRecord courseRecords = (CourseRecord)courseRecordList.get(index);
		System.out.println("Name: " + courseRecords.getName() +  ", AU:" + courseRecords.getAU() + ", CourseType: " + courseRecords.getCourseType() + ", SU: " + 
		courseRecords.getSU() + ", GERType: " + courseRecords.getGERType()+ ", Vacancy Left: " + courseRecords.getVacancy());
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you Want to add this Course(Yes or No): ");
		String add = sc.next();
		if(add.equals("Yes"))
		{
			String status;
			CourseRecord courseRecord = (CourseRecord)courseRecordList.get(index);
			
			String vacancy = courseRecords.getVacancy();
			int intVacancy = Integer.parseInt(vacancy);
			
			String queue = null;
			int queueNum = 0;
			if(intVacancy == 0)
			{		
				status = "WAITLIST";
				queue = GetWaitList.getQueue(waitList, courseIndex, indexNum, queue);
				if(queue != null)
					queueNum = Integer.parseInt(queue);
				queueNum +=1;
				queue = String.valueOf(queueNum);
			}
			else
			{
				status = "REGISTERED";
				intVacancy -= 1;
				String strVacancy = String.valueOf(intVacancy);
				courseRecords.setVacancy(strVacancy);
			}
			//update courseRecord
			CourseRecordsTextRepository courseRecordsTextRepository = new CourseRecordsTextRepository();
			courseRecordsTextRepository.saveList(courseRecordList);
			
			
			//get courseRecord Information
			String AU = courseRecords.getAU(), courseType = courseRecords.getCourseType(); 
			String SU = courseRecords.getSU(), GERType = courseRecords.getGERType();
			
			// get student Information index
			int studentInfoIndex = GetUserInfo.getStudentIndex(studentInfo, key);
			if(studentInfoIndex != -1)
			{
				//get student Information
				Student student = (Student)studentInfo.get(studentInfoIndex);
				String firstName = student.getFirstName(), lastName = student.getLastName(), matricNum = student.getMatricNum();
				String email = student.getEmail();
				
				//insert onto Student Record
				StudentRecords newStudentRecord = new StudentRecords(key,firstName,lastName,matricNum,courseIndex,indexNum,AU, courseType, SU, GERType, status, queue);
				studentsRecords.add(newStudentRecord);
				StudentRecordTextRepository studentRecordTextRepository = new StudentRecordTextRepository();
				studentRecordTextRepository.saveList(studentsRecords);
				
				System.out.println("Course Added!");
				//trigger email
				SendMailTLS.mailNotification(courseIndex, indexNum, status, firstName, lastName, matricNum, AU, email);
			}
		}
		else
		{
			System.out.println("Exited\n");
			return;
		}

		
		
		

		}
	}
