package StudentRecords;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import CourseRecords.CourseRecord;

/**
 * CheckStudentRecord ensures a course is not added if there is a clash or duplicate
 * with an existing course the student already have in the database.
 * 
 * @author Aaron
 *
 */
public class CheckStudentRecord {
		/**
	 * Checks if the particular student is already taking the course.
	 * 
	 * @param studentRecordsList An ArrayList of all student records in the database
	 * @param courseIndex courseIndex to be checked
	 * @param key key used to identify student
	 * @return true if student is already taking the course, false otherwise
	 */
	public static boolean checkStudent(ArrayList studentRecordsList, String courseIndex, String key)
	{
		for(int i=0; i< studentRecordsList.size(); i++)
		{
			StudentRecords studentRecord = (StudentRecords)studentRecordsList.get(i);
			if(studentRecord.getCourseIndex().equals(courseIndex) && studentRecord.getKey().equals(key))
			{
				System.out.println("Cannot Add this Course. You already have this course!\n");
				return true;
			}
				
		}
		return false;
	}
	/**
	 * 	Check if there is a clash in timing for the new course registered by the student  
	 *  against the courses he is already taking
	 * 
	 * @param courseRecordList An ArrayList of all course records in the database
	 * @param studentsRecords An ArrayList of all student records in the database
	 * @param courseIndex courseIndex to be checked
	 * @param indexNum courseIndex to be checked
	 * @param key used to identify the student
	 * @return false if there is a clash, true otherwise
	 */
	public static boolean checkTiming(ArrayList courseRecordList, ArrayList studentsRecords, String courseIndex, String indexNum, String key)
	{
		String SEPARATOR = "|";
		ArrayList<String> cList = new ArrayList<String>();
		for(int i=0; i< studentsRecords.size(); i++)
		{
			StudentRecords studentRecordList = (StudentRecords)studentsRecords.get(i);
			// find registered courses of student
			if(studentRecordList.getKey().equals(key) && studentRecordList.getStatus().equals("REGISTERED"))
			{
				cList.add(studentRecordList.getCourseIndex() + "|" + studentRecordList.getIndexNum());
			}
		}
		// get day and time for added course
		ArrayList<String> courseListTiming = new ArrayList<String>();
		for(int i=0; i< courseRecordList.size(); i++)
		{
			CourseRecord courseRecord = (CourseRecord)courseRecordList.get(i);
			if(courseRecord.getCourseIndex().equals(courseIndex) && courseRecord.getIndexNum().equals(indexNum))
				courseListTiming.add(courseRecord.getDay() + "|" + courseRecord.getTime());
		}
		
		// check day and time against other registered course day and times
		for(int j=0; j<cList.size(); j++) {
			String st = (String)cList.get(j);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
			String  courseInd = star.nextToken().trim();
			String  indexNo = star.nextToken().trim();
			
			for(int i=0; i< courseRecordList.size(); i++)
			{
				CourseRecord courseRecord = (CourseRecord)courseRecordList.get(i);
				for(int x =0;x < courseListTiming.size();x++) {
					String ct = (String)courseListTiming.get(x);
					StringTokenizer str = new StringTokenizer(ct , SEPARATOR);	
					String  day = str.nextToken().trim();
					String  time = str.nextToken().trim();
					//check if day clash
					if(courseRecord.getCourseIndex().equals(courseInd) && courseRecord.getIndexNum().equals(indexNo)&& courseRecord.getDay().equals(day)) {
						//check if timing clash on same day - check if added course starting time < other courses end time. time format eg 0930 - 1330
						int addStart = Integer.parseInt(time.substring(0, 4)); //get first 4 characters
						//int addEnd = Integer.parseInt(time.substring(courseRecord.getTime().length()-4)); 
						int otherStart = Integer.parseInt(courseRecord.getTime().substring(0,4)); 
						int otherEnd = Integer.parseInt(courseRecord.getTime().substring(courseRecord.getTime().length()-4)); //get last 4 characters
						//System.out.println("Start" + addStart + "End"+ otherEnd);
						//GET THE DIFF COMBINATIONS OF TIMINGS
						if(addStart < otherEnd && addStart > otherStart ) {
							//clash
							System.out.println("Course Clashes with "+ courseInd + " with index " + indexNo + " for class type "+ courseRecord.getType());
							return false;
						}
					}
				}			
			}			
		}

		return true;
	}
}


