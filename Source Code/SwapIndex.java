package Student;

import java.io.IOException;
import java.util.ArrayList;

import CourseRecords.CourseRecord;
import Repository.StudentRecordTextRepository;
import StudentInfo.Student;
import StudentRecords.StudentRecords;

/**
 * SwapIndex swaps the indexes of a course between 2 students.
 * @author 1 Esmond Heng Fuwei
 * @author 2 Samantha Tan Swee Yun
 *
 */
public class SwapIndex {
	
	/**
	 * Gets all indexNum of particular student (via matriculation number)
	 * 
	 * @param studentRecords An ArrayList of StudentRecords currently in the database
	 * @param matricNum matricNum used to select student
	 * @return all indexNum of particular student (via matriculation number)
	 * @throws IOException If an input or output exception occurs
	 */
	public static ArrayList getIndexList(ArrayList studentRecords, String matricNum) throws IOException
	{
		ArrayList<String> indexList = new ArrayList<String>();
		for (int i=0; i<studentRecords.size(); i++)
		{
			//get array object 
			StudentRecords studentRecord = (StudentRecords)studentRecords.get(i);
			if(studentRecord.getMatricNum().contentEquals(matricNum)){
				indexList.add(studentRecord.getIndexNum());
			}
		}
		return indexList;
	}

	/**
	 * Gets all matriculation numbers.
	 * 
	 * @param studentInfo An ArrayList of all studentInfo currently in the database
	 * @return all matriculation numbers
	 * @throws IOException If an input or output exception occurs
	 */ 
	public static ArrayList getMatricList(ArrayList studentInfo) throws IOException
	{
		ArrayList<String> matricList = new ArrayList<String>();
		for (int i=0; i<studentInfo.size(); i++)
		{
			//get array object 
			Student studentInfos = (Student)studentInfo.get(i);
			matricList.add(studentInfos.getMatricNum());		
		}
		return matricList;
	}
	
	/**
	 * Gets the friend's index number for the course to be swapped.
	 * 
	 * @param StudentRecord An ArrayList of all StudentRecord currently in the database
	 * @param matricNum matricNum of friend
	 * @param course course intending to be swapped
	 * @return friend's indexNum for the course to be swapped
	 * @throws IOException If an input or output exception occurs
	 */
	public static ArrayList getFriendIndex(ArrayList StudentRecord, String matricNum, String course) throws IOException
	{
		ArrayList<String> iList = new ArrayList<String>();
		for (int i=0; i<StudentRecord.size(); i++)
		{
			//get array object 
			StudentRecords studentRecord = (StudentRecords)StudentRecord.get(i);
			if(studentRecord.getMatricNum().equals(matricNum) && studentRecord.getCourseIndex().equals("course"))
				iList.add(studentRecord.getIndexNum());		
		}
		return iList;
	}
	
	/**
	 * swaps the indexes of a course between 2 students.
	 * 
	 * @param studentsRecords An ArrayList of StudentRecords currently in the database
	 * @param courseRecordList An ArrayList of courseRecord currently in the database
	 * @param MatricNum friend's MatricNum
	 * @param oldIndexNum user's IndexNum
	 * @param newIndexNum friend's IndexNum
	 * @param key The key used to identify the user
	 * @throws IOException IOException If an input or output exception occurs
	 */
	public static void swapIndex(ArrayList studentsRecords, ArrayList courseRecordList,String MatricNum, String oldIndexNum, String newIndexNum, String key) throws IOException {
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
	
	for (int i = 0 ; i < studentsRecords.size() ; i++) {
		StudentRecords studentRecord = (StudentRecords)studentsRecords.get(i);
		if(studentRecord.getMatricNum().equals(MatricNum) && studentRecord.getIndexNum().contentEquals(newIndexNum)) {
			studentRecord.setIndexNum(oldIndexNum);
			
			StudentRecordTextRepository studentRecordsTextRepository = new StudentRecordTextRepository();
			studentRecordsTextRepository.saveList(studentsRecords);
			count++;
			break;
		}	
	}
	
	if (count > 0) {
		System.out.println("Swap Index Successful!\n");
	}
}
}
