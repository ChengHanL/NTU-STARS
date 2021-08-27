package Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import WaitList.WaitList;

/**
 * WaitListTextRepository is a boundary class which performs read and write operations 
 * on WaitList, a database that stores information about the number of students on
 * waitlist for each course.<p>
 * 
 * @author Aaron
 * @author Li Haoyang
 */
public class WaitListTextRepository implements Repository{
	private static final String SEPARATOR = "|";
	private static final String FILENAME = "WaitList.txt" ;
	private static final String DATFILENAME = "WaitList.dat" ;
	
	TextDatabase textDatabase = new TextDatabase();
	DatDatabase datDatabase = new DatDatabase();
	
    	/**
	 * Reads all information from the WaitList database. 
	 * 
	 * @return An ArrayList of WaitList objects storing 
	 * @throws IOException If an input or output exception occurred
	 */
	public ArrayList readToList() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDatabase.read(FILENAME);
		ArrayList alr = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);

				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
				String  courseIndex = star.nextToken().trim();
				String  indexNum = star.nextToken().trim();	
				String  totalQueue = star.nextToken().trim();	
		
				WaitList waitList = new WaitList(courseIndex, indexNum, totalQueue);
				alr.add(waitList) ;
			}
			return alr ;
	}

	/**
	 * Saves an ArrayList of WaitList objects into the database.
	 * 
	 * @param al an ArrayList of WaitList object to be stored into the database
	 * @throws IOException If an input or output exception occurred
	 */
public void saveList(List al) throws IOException {
		List alw = new ArrayList() ;// to store Professors data
		
        for (int i = 0 ; i < al.size() ; i++) {
        	WaitList waitList = (WaitList)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(waitList.getCourseIndex().trim());
				st.append(SEPARATOR);
				st.append(waitList.getIndexNum().trim());
				st.append(SEPARATOR);
				st.append(waitList.getTotalQueue().trim());
				alw.add(st.toString()) ;
				
			}
			textDatabase.write(FILENAME,alw);
			datDatabase.write(DATFILENAME, al);
	}
}
