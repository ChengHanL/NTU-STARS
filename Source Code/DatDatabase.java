package Repository;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;


/**
 * DatRepository is a boundary class which performs read and write operations on 
 * serializable files.
 * 
 * @author Aaron Tay Han Yen
 * @author Li Haoyang
 */

//Note : When structure of the Object type (the class file) in the list changed
//the Serialized file may fail.
public class DatDatabase implements Database
{
	/**
	 * reads all data from a serializable file
	 * 
	 * @param filename name of the file to read from
	 * @return all data in the file as an ArrayList 
	 */
	public List read(String filename) {
		List pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		// print out the size
		//System.out.println(" Details Size: " + pDetails.size());
		//System.out.println();
		return pDetails;
	}
	
	/**
	 * writes data to a serializable file
	 * 
	 * @param filename name of the file to write to
	 * @param list data to be written to the file
	 */
	public void write(String filename, List list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
