package CourseRecords;

import java.io.IOException;
import java.util.ArrayList;

/**
 * GetCourseRecord contains multiple options to display
 * information of courses in the CourseRecords database.
 * 
 * @author 1 Aaron Tay Han Yen
 * @author 2 Samantha Tan Swee Yun
 */
public class GetCourseRecord {
	
	/**
	 * Gets the list of all distinct indexNum in the database.
	 * 
	 * @param courseRecord An ArrayList of all course informations in the CourseRecords database
	 * @throws IOException If an input or output exception occurred
	 * @return list of all distinct indexNum in the database
	 */
	public static ArrayList getIndexList(ArrayList courseRecord) throws IOException
	{
		ArrayList<String> indexList = new ArrayList<String>();
		for (int i=0; i<courseRecord.size(); i++)
		{
			//get array object 
			CourseRecord courseRecords = (CourseRecord)courseRecord.get(i);
			if(!courseRecords.getVacancy().equals("-"))
				indexList.add(courseRecords.getIndexNum());
		}
		return indexList;
	}
	
	/**
	 * Displays indexNum of all courses in the database.
	 * 
	 * @param courseRecord An ArrayList of all course informations in the CourseRecords database
	 * @throws IOException If an input or output exception occurred
	 */
	public static void printIndexNum(ArrayList courseRecord) throws IOException
	{
		System.out.println("List of Index Number:");
		for (int i=0; i<courseRecord.size(); i++)
		{
			//get array object 
			CourseRecord courseRecords = (CourseRecord)courseRecord.get(i);
			if(!courseRecords.getVacancy().equals("-"))
				System.out.println("Index Number: " + courseRecords.getIndexNum() + ", Name: " + courseRecords.getName());
		}
	}
	
	/**
	 * Displays the vacancy and other course informations for courses with a particular indexNum.
	 * 
	 * @param indexNum The indexNum used to selectively displays the vacancies left courses 
	 * @param courseRecord An ArrayList of all course informations in the CourseRecords database
	 */
	public static void printVacancy(String indexNum, ArrayList courseRecord)
	{
//		for (int i=0; i<courseRecord.size(); i++)
//		{
//			CourseRecord courseRecords = (CourseRecord)courseRecord.get(i);
//			if(courseRecords.getIndexNum().equals(indexNum)) {
//				System.out.println("Index Number: "+ indexNum + "     Course: "+ courseRecords.getCourseIndex() + "\n");
//				break;
//			}
//		}
		for (int i=0; i<courseRecord.size(); i++)
		{
			//get array object 
			CourseRecord courseRecords = (CourseRecord)courseRecord.get(i);
			if(courseRecords.getIndexNum().equals(indexNum)) {
				System.out.println("Class Type: " + courseRecords.getType());
				System.out.println("Group: " + courseRecords.getGroup());
				System.out.println("Day: " + courseRecords.getDay());
				System.out.println("Time: " + courseRecords.getTime());
				System.out.println("Venue: " + courseRecords.getVenue());
				System.out.println("Remarks: " + courseRecords.getRemarks());
				System.out.println("\n");
			}
		}
		
		for (int i=0; i<courseRecord.size(); i++)
		{
			CourseRecord courseRecords = (CourseRecord)courseRecord.get(i);
			if(courseRecords.getIndexNum().equals(indexNum)) {
				System.out.println("Place Available: " + courseRecords.getVacancy() + "\n");
				//System.out.println("Length of Waitlist: " + "\n");
				break;
			}
		}
	}
	
	/**
	 * Displays all informations of all courses in the CourseRecords database.
	 * 
	 * @param courseRecord An ArrayList of all course informations in the CourseRecords database
	 * @throws IOException If an input or output exception occurred
	 */
	public static void printAllCourseData(ArrayList courseRecord) throws IOException
	{
		for (int i = 0 ; i < courseRecord.size() ; i++) {
			CourseRecord courseRecordList = (CourseRecord)courseRecord.get(i);
				System.out.println("CourseIndex: " + courseRecordList.getCourseIndex() );
				System.out.println("IndexNum: " + courseRecordList.getIndexNum() );
				System.out.println("Type: " + courseRecordList.getType() );
				System.out.println("Group: " + courseRecordList.getGroup() );
				System.out.println("Day: " + courseRecordList.getDay() );
				System.out.println("Time: " + courseRecordList.getTime() );
				System.out.println("Venue: " + courseRecordList.getVenue() );
				System.out.println("Remarks: " + courseRecordList.getRemarks() );
				System.out.println("Vacancy: " + courseRecordList.getVacancy() );
				System.out.println("Name: " + courseRecordList.getName() );
				System.out.println("AU: " + courseRecordList.getAU());
				System.out.println("Course Type: " + courseRecordList.getCourseType());
				System.out.println("SU: " + courseRecordList.getSU());
				System.out.println("GER Type: " + courseRecordList.getGERType());
				System.out.println("");
			}
	}
	
	/**
	 * Displays all informations of courses with matching courseIndex and indexNum
	 * in the CourseRecords database.
	 * 
	 * @param courseRecord An ArrayList of all course informations in the CourseRecords database
	 * @param courseIndex The courseIndex used to extract course information
	 * @param indexNum The IndexNum useds to extract course information
	 */
	public static void printCourseCode(ArrayList courseRecord, String courseIndex, String indexNum)
	{
		for (int i = 0 ; i < courseRecord.size() ; i++) {
			CourseRecord courseRecordList = (CourseRecord)courseRecord.get(i);
			if(courseRecordList.getCourseIndex().equals(courseIndex) && courseRecordList.getIndexNum().equals(indexNum))
			{
				System.out.println("\n");
				System.out.println("*********No." + courseRecordList.getNum() + "*********");
				System.out.println("CourseIndex: " + courseRecordList.getCourseIndex() );
				System.out.println("IndexNum: " + courseRecordList.getIndexNum() );
				System.out.println("Type: " + courseRecordList.getType() );
				System.out.println("Group: " + courseRecordList.getGroup() );
				System.out.println("Day: " + courseRecordList.getDay() );
				System.out.println("Time: " + courseRecordList.getTime() );
				System.out.println("Venue: " + courseRecordList.getVenue() );
				System.out.println("Remarks: " + courseRecordList.getRemarks() );
				System.out.println("Vacancy: " + courseRecordList.getVacancy() );
				System.out.println("Name: " + courseRecordList.getName() );	
				System.out.println("AU: " + courseRecordList.getAU());
				System.out.println("Course Type: " + courseRecordList.getCourseType());
				System.out.println("SU: " + courseRecordList.getSU());
				System.out.println("GER Type: " + courseRecordList.getGERType());
			}

		}
	}
	
	/**
	 * Displays the courseIndex and indexNum of all courses in the CourseRecords database.
	 * 
	 * @param courseRecord An ArrayList of all course informations in the CourseRecords database
	 * @throws IOException If an input or output exception occurred
	 */
	public static void printCourseCode(ArrayList courseRecord) throws IOException
	{
		for (int i = 0 ; i < courseRecord.size() ; i++) {
			CourseRecord courseRecordList = (CourseRecord)courseRecord.get(i);
			if(!courseRecordList.getVacancy().equals("-"))
				System.out.println("Course Index: " + courseRecordList.getCourseIndex() + ", Index Number: " + courseRecordList.getIndexNum());
		}
		System.out.println();
	}
	//for student interface
		public static int returnCourseIndex(ArrayList courseRecord, String courseIndex, String indexNum)
		{
			for (int i = 0 ; i < courseRecord.size() ; i++) {
				CourseRecord courseRecordList = (CourseRecord)courseRecord.get(i);
				if(courseRecordList.getCourseIndex().equals(courseIndex) && courseRecordList.getIndexNum().equals(indexNum))
					return i;
			}
			return -1;
		}
	
	/**
	 * Gets the list of all distinct courseIndex in the database.
	 * 
	 * @param courseRecord An ArrayList of all course informations in the CourseRecords database
	 * @throws IOException If an input or output exception occurred
	 * @return list of all distinct courseIndex in the database
	 */
	public static ArrayList getCourseList(ArrayList courseRecord) throws IOException
	{
		ArrayList<String> courseList = new ArrayList<String>();
		for (int i = 0 ; i < courseRecord.size() ; i++) {
			CourseRecord courseRecordList = (CourseRecord)courseRecord.get(i);
			if(!courseRecordList.getVacancy().equals("-"))
				courseList.add(courseRecordList.getCourseIndex());
		}
		return courseList;
	}
}
