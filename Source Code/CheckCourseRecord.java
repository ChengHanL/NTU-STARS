package CourseRecords;

import java.util.ArrayList;

/**
 * CheckCourseRecord searches the existence of an entry in the CourseRecords
 * database based on an query(partial informations of a course).
 * 
 * @author Aaron Tay Han Yen
 *
 */
public class CheckCourseRecord {
	
	/**
	 * Checks if a course with the same courseIndex and indexNum is currently in the database.
	 * 
	 * @param courseRecord An ArrayList of all course informations in the CourseRecords database
	 * @param courseIndex courseIndex to be checked
	 * @param indexNum indexNum to be checked
	 * @return true if a matching courseIndex and indexNum pair is found in the database, false otherwise.
	 */
	public static boolean checkCourse(ArrayList courseRecord, String courseIndex, String indexNum)
	{
		for (int i = 0 ; i < courseRecord.size() ; i++) {
			CourseRecord courseRecordList = (CourseRecord)courseRecord.get(i);
			if(courseRecordList.getCourseIndex().equals(courseIndex) && courseRecordList.getIndexNum().equals(indexNum))
				return true;
		}
		System.out.println("Invalid Course Index & Index Number\n");
		return false;
	}
	
	
	/**
	 * Checks if a course with the same courseIndex, indexNum and num is currently 
	 * in the database.
	 * 
	 * @param courseRecord An ArrayList of all course informations in the CourseRecords database
	 * @param num a number useful for identifying a courseIndex and index pair (eg. CZ1003|10088) 
	 * when multiple same courseIndex and index pairs are present. <p> For example, num = 1
	 * for CZ1003|10088|LAB and num = 2 for CZ1003|10088|TUT
	 * @param courseIndex courseIndex to be checked
	 * @param indexNum indexNum indexNum to be checked
	 * @return true if a matching entry with the same num, courseIndex and indexNum 
	 * is found in the database, false otherwise.
	 */
	public static boolean checkNum(ArrayList courseRecord, String num, String courseIndex, String indexNum)
	{
		for (int i = 0 ; i < courseRecord.size() ; i++) {
			CourseRecord courseRecordList = (CourseRecord)courseRecord.get(i);
			if(courseRecordList.getNum().equals(num) && courseRecordList.getCourseIndex().equals(courseIndex) && courseRecordList.getIndexNum().equals(indexNum))
				return true;
		}
		System.out.println("Invalid No. \n");
		return false;
	}
	
	/**
	 * Checks if a course to be added into the system already exists in the system.
	 * 
	 * @param courseRecord An ArrayList of all course informations in the CourseRecords database
	 * @param courseIndex courseIndex to be checked
	 * @param indexNum indexNum to be checked
	 * @return true if the course to be added already exists, false otherwise
	 */
	public static boolean checkDuplicate(ArrayList courseRecord, String courseIndex, String indexNum)
	{
		for (int i = 0 ; i < courseRecord.size() ; i++) {
			CourseRecord courseRecordList = (CourseRecord)courseRecord.get(i);
			if(courseRecordList.getCourseIndex().equals(courseIndex) && courseRecordList.getIndexNum().equals(indexNum))
			{
				System.out.println("Cannot add a course that already exist");
				return true;
			}

		}
		return false;
	}

	
}
