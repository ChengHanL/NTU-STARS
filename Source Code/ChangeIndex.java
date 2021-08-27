package Student;

import java.io.IOException;
import java.util.ArrayList;

import CourseRecords.CourseRecord;
import Repository.StudentRecordTextRepository;
import StudentRecords.StudentRecords;

/**
 * ChangeIndex swaps old index with a new index for the course registered by the user.
 * 
 * @author Samantha Tan Swee Yun
 *
 */
public class ChangeIndex {
	
	/**
	 * gets all indexNum (courses taken) by a particular student
	 * 
	 * @param studentRecords An ArrayList of StudentRecords currently in the database
	 * @param key The key used to identify a student
	 * @return all indexNum (courses taken) by a particular student
	 * @throws IOException If an input or output exception occurs
	 */
	public static ArrayList getIndexList(ArrayList studentRecords, String key) throws IOException
	{
		ArrayList<String> indexList = new ArrayList<String>();
		for (int i=0; i<studentRecords.size(); i++)
		{
			//get array object 
			StudentRecords studentRecord = (StudentRecords)studentRecords.get(i);
			if(studentRecord.getKey().contentEquals(key)){
				indexList.add(studentRecord.getIndexNum());
			}
		}
		return indexList;
	}
	
	
	public static ArrayList getIndexListByCourse(ArrayList courseRecord,String oldIndex) {
		ArrayList<String> indexList = new ArrayList<String>();
		String courseIndex;
		for (int i=0; i<courseRecord.size(); i++)
		{
			CourseRecord courseRecords = (CourseRecord)courseRecord.get(i);
			if(courseRecords.getIndexNum().equals(oldIndex)) {
				 courseIndex = courseRecords.getCourseIndex();
				 for (int j=0; j<courseRecord.size(); j++){
					CourseRecord courseRec = (CourseRecord)courseRecord.get(j);
					if(courseRec.getCourseIndex().equals(courseIndex)) {
							indexList.add(courseRec.getIndexNum());
					}
				 }
				 break;
			}
	}
		return indexList;
	}
	
	/**
	 * Changes old index to a new index for a course registered by the user.
	 * 
	 * @param studentsRecords An ArrayList of StudentRecords currently in the database
	 * @param courseRecordList An ArrayList of courseRecord currently in the database
	 * @param oldIndexNum oldIndexNum
	 * @param newIndexNum newIndexNum
	 * @param key The key used to identify a student
	 * @throws IOException If an input or output exception occurs
	 */
	public static void changeIndex(ArrayList studentsRecords, ArrayList courseRecordList, String oldIndexNum, String newIndexNum, String key) throws IOException {
	    int count = 0;
	    //print details of old and new index
		for (int i=0; i<courseRecordList.size(); i++)
		{
			CourseRecord courseRecords = (CourseRecord)courseRecordList.get(i);
			if(courseRecords.getIndexNum().equals(oldIndexNum)) {
				System.out.println("Course: "+ courseRecords.getCourseIndex() + "\n");
				break;
			}
		}
		System.out.println("Current Index Number: "+ oldIndexNum + "\n");

		for (int i=0; i<courseRecordList.size(); i++)
		{
			CourseRecord courseRecords = (CourseRecord)courseRecordList.get(i);
			if(courseRecords.getIndexNum().equals(oldIndexNum)) {
				System.out.println("Class Type: " + courseRecords.getType());
				System.out.println("Group: " + courseRecords.getGroup());
				System.out.println("Day: " + courseRecords.getDay());
				System.out.println("Time: " + courseRecords.getTime());
				System.out.println("Venue: " + courseRecords.getVenue());
				System.out.println("Remarks: " + courseRecords.getRemarks());
				System.out.println("\n");
			}
		}
		System.out.println("New Index Number: "+ newIndexNum + "\n");

		for (int i=0; i<courseRecordList.size(); i++)
		{
			CourseRecord courseRecords = (CourseRecord)courseRecordList.get(i);
			if(courseRecords.getIndexNum().equals(newIndexNum)) {
				System.out.println("Class Type: " + courseRecords.getType());
				System.out.println("Group: " + courseRecords.getGroup());
				System.out.println("Day: " + courseRecords.getDay());
				System.out.println("Time: " + courseRecords.getTime());
				System.out.println("Venue: " + courseRecords.getVenue());
				System.out.println("Remarks: " + courseRecords.getRemarks());
				System.out.println("\n");
			}
		}
		
		//change index in StudentRecord
		for (int i = 0 ; i < studentsRecords.size() ; i++) {
			StudentRecords studentRecord = (StudentRecords)studentsRecords.get(i);
			if(studentRecord.getKey().equals(key) && studentRecord.getIndexNum().contentEquals(oldIndexNum)) {
				studentRecord.setIndexNum(newIndexNum);
				
				StudentRecordTextRepository studentRecordsTextRepository = new StudentRecordTextRepository();
				studentRecordsTextRepository.saveList(studentsRecords);
				count++;
				break;
			}	
		}
		
		if (count > 0) {
			System.out.println("Change Index Successful!\n");
		}
	}
}
