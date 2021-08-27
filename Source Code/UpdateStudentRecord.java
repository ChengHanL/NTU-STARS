package StudentRecords;

import java.io.IOException;
import java.util.ArrayList;

import Repository.StudentRecordTextRepository;

/**
 * Updates the AU of a selected course in the StudentRecords database
 * 
 * @author Aaron Tay Han Yen
 *
 */
public class UpdateStudentRecord {

	/**
	 * Updates the AU of a selected course in the StudentRecords database
	 * 
	 * @param StudentRecord An ArrayList of all entries in the StudentRecords database 
	 * @param courseIndex The courseIndex to change the AU 
	 * @param indexNum The indexNum to change the AU 
	 * @param AU new AU to be changed to
	 * @throws IOException If an input or output exception occurred
	 */
	public static void updateStudentRecord(ArrayList StudentRecord, String courseIndex, String indexNum, String AU) throws IOException
	{
		boolean updateRecord = false;
		for (int i = 0 ; i < StudentRecord.size() ; i++) {
			StudentRecords studentRecord = (StudentRecords)StudentRecord.get(i);
			
			if(studentRecord.getIndexNum().equals(indexNum) && studentRecord.getCourseIndex().equals(courseIndex))
			{
				updateRecord = true;
				System.out.println("Old AU: " + studentRecord.getAU() + ", New AU: " + AU);
				studentRecord.setAU(AU);
			}
		}
		if(updateRecord == true)
		{
			StudentRecordTextRepository studentRecordTextRepository = new StudentRecordTextRepository();
			studentRecordTextRepository.saveList(StudentRecord);
		}
		
	}
}
