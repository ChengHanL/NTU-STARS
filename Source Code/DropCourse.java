package Student;

import java.io.*;
import java.util.ArrayList;

import CourseRecords.CourseRecord;
import Repository.CourseRecordsTextRepository;
import Repository.StudentRecordTextRepository;
import Repository.WaitListTextRepository;
import StudentRecords.StudentRecords;
import WaitList.WaitList;

/**
 * DropCourse implements the logic of dropping a course
 * for the student
 * 
 * @author Samantha Tan Swee Yun
 *
 */
public class DropCourse {
	/**
	 * check whether the course and indexNum has a queue
	 * 
	 * @param studentsRecords An ArrayList of all studentsRecords in the database
	 * @param courseRecordList courseRecordList An ArrayList of all courseRecords in the database
	 * @param waitList waitList
	 * @param key key to identify student
	 * @param courseIndex courseIndex
	 * @param indexNum indexNum
	 * @param hasQueue hasQueue
	 * @throws IOException if input or output exception occurs
	 */
	public static void dropCourse(ArrayList studentsRecords, ArrayList courseRecordList, ArrayList waitList, String key, String courseIndex, String indexNum, boolean hasQueue) throws IOException {
	    //print details of dropped course
		for (int i = 0 ; i < studentsRecords.size() ; i++) {
			StudentRecords studentRecord = (StudentRecords)studentsRecords.get(i);
			if(studentRecord.getKey().equals(key) && studentRecord.getCourseIndex().equals(courseIndex)) {
				indexNum = studentRecord.getIndexNum();
				System.out.println("Index Number: "+ courseIndex + "\tCourse: "+ studentRecord.getCourseIndex());
				System.out.println("Course Type: "+ studentRecord.getCourseType() + "\tStatus: "+ studentRecord.getStatus());
				System.out.println("\n");
				break;
			}	
		}
		
		//delete row from StudentRecords
		for (int i = 0 ; i < studentsRecords.size() ; i++) {
			StudentRecords studentRecord = (StudentRecords)studentsRecords.get(i);
			if(studentRecord.getKey().equals(key) && studentRecord.getCourseIndex().contentEquals(courseIndex)) {
				studentsRecords.remove(studentsRecords.get(i));
				StudentRecordTextRepository studentRecordsTextRepository = new StudentRecordTextRepository();
				studentRecordsTextRepository.saveList(studentsRecords);
				System.out.println("Drop Course Successful!\n");
				break;
			}	
		}
		//check is there is queue 
		for(int i = 0; i < courseRecordList.size(); i++)
		{
			CourseRecord courseRecord = (CourseRecord)courseRecordList.get(i);
			if(courseRecord.getIndexNum().equals(indexNum) && courseRecord.getCourseIndex().equals(courseIndex))
				if(hasQueue == false)
				{
					int intVacancy = Integer.parseInt(courseRecord.getVacancy());
					intVacancy+=1;
					String vacancy = String.valueOf(intVacancy);
					courseRecord.setVacancy(vacancy);
					CourseRecordsTextRepository courseRecordsTextRepository = new CourseRecordsTextRepository();
					courseRecordsTextRepository.saveList(courseRecordList);
					break;
				}
				else
				{
					//minus wait list by one
					for (int j = 0 ; i < waitList.size() ; j++) {
			    		WaitList myWaitList = (WaitList)waitList.get(j);
			    		if(myWaitList.getIndexNum().equals(indexNum) && myWaitList.getCourseIndex().equals(courseIndex))
			    		{
			    			int totalQueue = Integer.parseInt(myWaitList.getTotalQueue());
			    			totalQueue -= 1;
			    			String strQueue = String.valueOf(totalQueue);
			    			myWaitList.setTotalQueue(strQueue);
			    			WaitListTextRepository waitListExtRepository = new WaitListTextRepository();
			    			waitListExtRepository.saveList(waitList);
			    			break;
			    		}
			    			
					}
					for(int j = 0; j < studentsRecords.size(); j++)
					{
						//give the course to the queue number 1
						StudentRecords studentRecord = (StudentRecords)studentsRecords.get(j);
						if(studentRecord.getCourseIndex().equals(courseIndex) && studentRecord.getIndexNum().equals(indexNum) && studentRecord.getQueue().equals("1"))
						{
							studentRecord.setStatus("REGISTERED");
							studentRecord.setQueue("0");
							break;
						}
					}
					//minus 1 all student Record based on course & index 
					for(int j = 0; j < studentsRecords.size(); j++)
					{
						//give the course to the queue number 1
						StudentRecords studentRecord = (StudentRecords)studentsRecords.get(j);
						if(studentRecord.getCourseIndex().equals(courseIndex) && studentRecord.getIndexNum().equals(indexNum) && !studentRecord.getQueue().equals("1") && !studentRecord.getQueue().equals("0"))
						{
							int intQueue = Integer.parseInt(studentRecord.getQueue());
							intQueue -= 1;
							String strQueue = String.valueOf(intQueue);
							studentRecord.setQueue(strQueue);
						}
					}
					//save to txtfile & dat
					StudentRecordTextRepository studentRecordsTextRepository = new StudentRecordTextRepository();
					studentRecordsTextRepository.saveList(studentsRecords);
				}
		}
		
	}
}
