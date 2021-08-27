package Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import AccessPeriod.AccessPeriod;

/**
 * GlobalAccessPeriodTextRepository is a boundary class which performs read and write operations 
 * on GlobalAccessPeriod, a database that stores all time periods that students are allowed
 * to access the STAR System.<p>
 * Each AccessPeriod has a startAccessPeriod(dd/mm/yyyy) and a endAccessPeriod(dd/mm/yyyy): <p>
 * 
 * @author Aaron Tay Han Yen
 * @author Li Haoyang
 */
public class GlobalAccessPeriodTextRepository implements Repository {
	private static final String SEPARATOR = "|";
	private static final String FILENAME = "GlobalAccessPeriod.txt" ;
	private static final String DATFILENAME = "GlobalAccessPeriod.dat" ;
	
	TextDatabase textDatabase = new TextDatabase();
	DatDatabase datDatabase = new DatDatabase();
    	/**
	 * Gets all time periods that students are allowed to access the STAR System.<p>
     	 * Each time period is stored as a AccessPeriod object.<p> 
     	 * An ArrayList of AccessPeriod objects are returned.<p>
	 * 
	 * @return An ArrayList of AccessPeriod objects storing 
	 * all allowable access periods for students from the database is returned.
	 * @throws IOException If an input or output exception occurred
	 */
	public ArrayList readToList() throws IOException {
		
		// read String from text file
		ArrayList stringArray = (ArrayList)textDatabase.read(FILENAME);
		ArrayList alr = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

				String  startAccessPeriod = star.nextToken().trim();	// first token
				String  endAccessPeriod = star.nextToken().trim();	// first token
				//int  vacancy = Integer.parseInt(star.nextToken().trim());
				// create Professor object from file data
				AccessPeriod accessPeriodObj = new AccessPeriod(startAccessPeriod, endAccessPeriod);
				// add to Professors list
				alr.add(accessPeriodObj);
			}
			return alr ;
	}

	/**
	 * Saves an ArrayList of AccessPeriod objects into the database.
	 * 
	 * @param al an ArrayList of AccessPeriod object to be stored into the database
	 * @throws IOException If an input or output exception occurred
	 */
	public void saveList(List al) throws IOException {
			List alw = new ArrayList() ;// to store Professors data
	
	        for (int i = 0 ; i < al.size() ; i++) {
	        	AccessPeriod accessPeriodObj = (AccessPeriod)al.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(accessPeriodObj.getStartAccessPeriod().trim());
					st.append(SEPARATOR);
					st.append(accessPeriodObj.getEndAccessPeriod().trim());
					alw.add(st.toString()) ;
				}
	        textDatabase.write(FILENAME,alw);
	        datDatabase.write(DATFILENAME, al);
	}
}
