package Repository;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import StudentInfo.Student;

/**
 * StudentPersonalInfoTextRepository is a boundary class which performs read and write operations 
 * on StudentsInfo, a database that stores the information of all students.<p>
 * The information stored for each student is listed below: <p>
 * key: Uniquely identifies a student <p>
 * firstName, lastName, matricNum, gender, nationality, age <p>
 * accessPeriod: The period of time that the student is allowed to access MySTARS <p>
 * email of student <p>
 * 
 * @author Aaron Tay Han Yen
 * @author Li Haoyang
 */
public class StudentPersonalTextRepository implements Repository{
	private static final String SEPARATOR = "|";
	private static final  String FILENAME = "StudentsInfo.txt" ;
	private static final  String DATFILENAME = "StudentsInfo.dat" ;
	
	TextDatabase textDatabase = new TextDatabase();
	DatDatabase datDatabase = new DatDatabase();

    	/**
	 * Gets the information of all students from the database.<p>
     * Each student's information is stored as a Student object.<p> 
     * An ArrayList of Student objects are returned.<p>
	 * 
	 * @return An ArrayList of Student objects storing 
	 * the information of all students from the database is returned.
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

				String  key = star.nextToken().trim();
				String  firstName = star.nextToken().trim();
				String  lastName = star.nextToken().trim();	
				String  matricNum = star.nextToken().trim();	
				String  gender = star.nextToken().trim();
				String  nationality = star.nextToken().trim();
				String  age = star.nextToken().trim();
				String accessPeriod = star.nextToken().trim();
				String email = star.nextToken().trim();

				Student student = new Student(key, firstName, lastName, matricNum, gender, nationality, age, accessPeriod, email);
	
				alr.add(student) ;
			}
			return alr ;
	}

		/**
	 * Saves an ArrayList of Student objects into the database.
	 * 
	 * @param al an ArrayList of Student object to be stored into the database
	 * @throws IOException If an input or output exception occurred
	 */
	public void saveList(List al) throws IOException {
			List alw = new ArrayList() ;// to store Professors data
	
	        for (int i = 0 ; i < al.size() ; i++) {
	        	Student student = (Student)al.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(student.getKey().trim());
					st.append(SEPARATOR);
					st.append(student.getFirstName().trim());
					st.append(SEPARATOR);
					st.append(student.getLastName().trim());
					st.append(SEPARATOR);
					st.append(student.getMatricNum().trim());
					st.append(SEPARATOR);
					st.append(student.getGender().trim());
					st.append(SEPARATOR);
					st.append(student.getNationality().trim());
					st.append(SEPARATOR);
					st.append(student.getAge().trim());
					st.append(SEPARATOR);
					st.append(student.getAccessPeriod().trim());
					st.append(SEPARATOR);
					st.append(student.getEmail().trim());
					alw.add(st.toString()) ;
				}
				textDatabase.write(FILENAME,alw);
				datDatabase.write(DATFILENAME, al);
	}
	
}
