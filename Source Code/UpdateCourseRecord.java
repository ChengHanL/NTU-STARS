package CourseRecords;

import java.io.IOException;
import java.util.ArrayList;

import Repository.CourseRecordsTextRepository;

/**
 * UpdateCourseRecord does the manipulation on the database 
 * storing all courses' informations(CourseRecords).<p>
 * The class can:<p>
 * Adds a new entry in the CourseRecords database<p>
 * Edits an existing entry in the CourseRecords database<p>
 * Checks if a courseIndex is present in the database.<p>
 * 
 * @author Aaron Tay Han Yen
 *
 */
public class UpdateCourseRecord {
	
	/**
	 * Adds a new entry in the CourseRecords database based on the information of
	 * the course to be added. <p>
	 * 
	 * @param courseRecord An ArrayList of all courses' information in the CourseRecords database 
	 * @param courseIndex courseIndex (eg. CZ1003)
	 * @param indexNum indexNum (eg. 10088)
	 * @param type type (eg. LECT/TUT/LAB)
	 * @param group grou (eg. CS1)
	 * @param day day
	 * @param time time
	 * @param venue venue
	 * @param remarks remarks
	 * @param vacancy vacancy
	 * @param name name
	 * @param AU AU of the course
	 * @param SU SU option
	 * @param courseType courseType(EG CORE)
	 * @param GERType GERType(EG BM)
	 * @param num a number useful for identifying a courseIndex and index pair (eg. CZ1003|10088) 
	 * when multiple same courseIndex and index pairs are present. For example, num = 1
	 * for CZ1003|10088|LAB and num = 2 for CZ1003|10088|TUT
	 * @throws IOException If an input or output exception occurred
	 */
	public static void addCourse(ArrayList courseRecord, String num, String courseIndex, String indexNum, String type, 
			String group, String day, String time, String venue, String remarks, String vacancy, String name, String AU, String courseType, String SU,String GERType) throws IOException
	{
		CourseRecord newCourseRecord = new CourseRecord(num, courseIndex, indexNum, type, group, day, time, venue, remarks, vacancy, name, AU, courseType,  SU, GERType);
		courseRecord.add(newCourseRecord);
		
		CourseRecordsTextRepository courseRecordsTextRepository = new CourseRecordsTextRepository();
		courseRecordsTextRepository.saveList(courseRecord);
		
		System.out.println("Course Index: " + newCourseRecord.getCourseIndex() );
		System.out.println("Index Number: " + newCourseRecord.getIndexNum() );
		System.out.println("Class Type: " + newCourseRecord.getType() );
		System.out.println("Group: " + newCourseRecord.getGroup() );
		System.out.println("Day: " + newCourseRecord.getDay() );
		System.out.println("Time: " + newCourseRecord.getTime() );
		System.out.println("Venue: " + newCourseRecord.getVenue() );
		System.out.println("Remarks: " + newCourseRecord.getRemarks() );
		System.out.println("Vacancy: " + newCourseRecord.getVacancy() );
		System.out.println("Course Name: " + newCourseRecord.getName() );
		System.out.println("AU: " + newCourseRecord.getAU() );
		System.out.println("CourseType: " + newCourseRecord.getCourseType());
		System.out.println("GERType: " + newCourseRecord.getGERType() );
		System.out.println("Course Successfully added!\n");
	}
	
	/**
	 * Checks if a courseIndex is present in the database. 
	 * 
	 * @param courseRecord An ArrayList of all courses' information in the CourseRecords database 
	 * @param courseIndex the courseIndex to be checked
	 * @return true if the courseIndex is found, false otherwise.
	 */
	public static boolean checkCourseIndex(ArrayList courseRecord, String courseIndex)
	{
		for(int i=0; i<courseRecord.size(); i++)
		{
			CourseRecord courseRecords = (CourseRecord)courseRecord.get(i);
			if(courseRecords.getCourseIndex().equals(courseIndex))
			{
				return true;
			}	
		}
		System.out.println("Course Index not found.\n");
		return false;
	}
	
	/**
	 * Edits the course in the database with the selected courseIndex, indexNum and num with 
	 * the new informations that are supplied into the method.
	 * 
	 * @param courseRecord An ArrayList of all courses' information in the CourseRecords database 
	 * @param courseIndex courseIndex (eg. CZ1003)
	 * @param indexNum indexNum (eg. 10088)
	 * @param type type (eg. LECT/TUT/LAB)
	 * @param group group (eg. CS1)
	 * @param day day
	 * @param time time
	 * @param venue venue
	 * @param remarks remarks
	 * @param vacancy vacancy
	 * @param AU AU of the course
	 * @param SU SU option
	 * @param courseType courseType(EG CORE)
	 * @param GERType GERType(EG BM)
	 * @param num a number useful for identifying a courseIndex and index pair (eg. CZ1003|10088) 
	 * when multiple same courseIndex and index pairs are present. For example, num = 1
	 * for CZ1003|10088|LAB and num = 2 for CZ1003|10088|TUT
	 * @throws IOException If an input or output exception occurred
	 */
	public static void editCourse(ArrayList courseRecord, String num, String courseIndex, String indexNum, String type,
			String group, String day, String time, String venue, String remarks, String vacancy, String AU,String courseType,String  SU,String GERType) throws IOException
	{
		for(int i=0; i<courseRecord.size(); i++)
		{
			CourseRecord courseRecords = (CourseRecord)courseRecord.get(i);
			
			if(courseRecords.getCourseIndex().equals(courseIndex) && courseRecords.getIndexNum().equals(indexNum) && courseRecords.getNum().equals(num))
			{
				courseRecords.setTime(type);
				courseRecords.setGroup(group);
				courseRecords.setDay(day);
				courseRecords.setTime(time);
				courseRecords.setVenue(venue);
				courseRecords.setRemarks(remarks);
				courseRecords.setVacancy(vacancy);
				courseRecords.setAU(AU);
				courseRecords.setCourseType(courseType) ;
				courseRecords.setSU(SU);
				courseRecords.setGERType(GERType);
				CourseRecordsTextRepository courseRecordsTextRepository = new CourseRecordsTextRepository();
				courseRecordsTextRepository.saveList(courseRecord);
				
				System.out.println("CourseIndex: " + courseRecords.getCourseIndex() );
				System.out.println("IndexNum: " + courseRecords.getIndexNum() );
				System.out.println("Type: " + courseRecords.getType() );
				System.out.println("Group: " + courseRecords.getGroup() );
				System.out.println("Day: " + courseRecords.getDay() );
				System.out.println("Time: " + courseRecords.getTime() );
				System.out.println("Venue: " + courseRecords.getVenue() );
				System.out.println("Remarks: " + courseRecords.getRemarks() );
				System.out.println("Vacancy: " + courseRecords.getVacancy() );
				System.out.println("Name: " + courseRecords.getName() );
				System.out.println("AU: " + courseRecords.getAU() );
				System.out.println("course Type: " + courseRecords.getCourseType() );
				System.out.println("SU: " + courseRecords.getSU() );
				System.out.println("GERType: " + courseRecords.getGERType() );
				System.out.println("\n");
				
				System.out.println("Successful edited!\n");
				return;
			}
			
		}
	}
}
