package Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * TextDatabase is a boundary class which performs read and write operations on 
 * text files.
 * 
 * @author Aaron Tay Han Yen
 * @author Li Haoyang
 */
public class TextDatabase implements Database{
	
	/**
	 * Reads all data from a txt file
	 * 
	 * @param fileName name of the file to read from
	 * @return all data in the file as an ArrayList 
	 */
	public List read(String fileName) { //enter from readStudentLogin
		List data = new ArrayList(); 
	   Scanner scanner = null;
	try {
		scanner = new Scanner(new FileInputStream(fileName));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   try {
	      while (scanner.hasNextLine()){
	        data.add(scanner.nextLine());
	      }
	   }
	    finally{
	      scanner.close();
	    }
	    return data;
	  }	  
	
	/**
	 * writes data to a txt file
	 * 
	 * @param fileName name of the file to write to
	 * @param data list data to be written to the file
	 */
	public void write(String fileName, List data)  {
	    PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    try {
			for (int i =0; i < data.size() ; i++) {
	      		out.println((String)data.get(i));
			}
	    }
	    finally {
	      out.close();
	    }
	 }
}
