package Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import Login.LoginInfo;

/**
 * LoginTextRepository is a boundary class which performs read and write operations 
 * on LoginsInfo, a database that stores login information of all users in NTU's MySTARS.<p>
 * The information stored for each user is listed below: <p>
 * username <p>
 * password<p>
 * type (Student or Admin)<p>
 * key: Uniquely identifies a user in the database (In case 2 users have the same 
 * username, password and type) <p>
 * 
 * @author Aaron Tay Han Yen
 * @author Li Haoyang
 */
public class LoginTextRepository implements Repository{
	private static final String SEPARATOR = "|";	
	private static final String FILENAME = "LoginsInfo.txt" ;
	private static final String DATFILENAME = "LoginsInfo.dat" ;
	
	TextDatabase textDatabase = new TextDatabase();
	DatDatabase datDatabase = new DatDatabase();
	
	/**
	 * Reads in and checks username's login detail and return the result of the check. 
	 * 
	 * @param userUsername user entered username
	 * @param userPassword user entered password
	 * @param userType userType: Student/Admin
	 * @return a key. If key is not null, login will be sucessful
	 * @throws IOException If an input or output exception occurred
	 */
	public String readLoginDetail(String userUsername, String userPassword, String userType) throws IOException { //enter from LoginController
		ArrayList stringArray = (ArrayList)textDatabase.read(FILENAME); // read String from text file
		ArrayList alr = new ArrayList() ;// to store Professors data
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	//read String from text filepass in the string to the string tokenizer using delimiter ","
				String username = star.nextToken().trim();	
				String password = star.nextToken().trim();	
				String type = star.nextToken().trim();
				if(userUsername.equals(username) && userPassword.equals(password) && type.equals(userType))
				{
					return star.nextToken().trim();
				}
			}
			return null;
	}	
	
	/**
	 * Gets login information of all users from the database.<p>
     * Each user's login information is stored as a LoginInfo object.<p> 
     * An ArrayList of LoginInfo objects are returned.<p>
	 * 
	 * @return An ArrayList of LoginInfo objects storing 
	 * the login information of all users is returned.
	 * @throws IOException If an input or output exception occurred
	 */
	public ArrayList readToList() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)textDatabase.read(FILENAME);
		ArrayList alr = new ArrayList();

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	

				String  username = star.nextToken().trim();
				String  password = star.nextToken().trim();	
				String  type = star.nextToken().trim();	
				String  key = star.nextToken().trim();

				LoginInfo loginInfo = new LoginInfo(username, password, type, key);
				
				alr.add(loginInfo) ;
			}
			return alr ;
	}

	/**
	 * Saves an ArrayList of LoginInfo objects into the database.
	 * 
	 * @param al an ArrayList of LoginInfo object to be stored into the database
	 * @throws IOException If an input or output exception occurred
	 */
	public void saveList(List al) throws IOException {
			List alw = new ArrayList() ;// to store Professors data
	
	        for (int i = 0 ; i < al.size() ; i++) {
	        	LoginInfo loginInfo = (LoginInfo)al.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(loginInfo.getUsername().trim());
					st.append(SEPARATOR);
					st.append(loginInfo.getPassword().trim());
					st.append(SEPARATOR);
					st.append(loginInfo.getType().trim());
					st.append(SEPARATOR);
					st.append(loginInfo.getKey().trim());
					alw.add(st.toString()) ;
				}
				textDatabase.write(FILENAME,alw);
				datDatabase.write(DATFILENAME, al);
		}
}
