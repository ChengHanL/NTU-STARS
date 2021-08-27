package Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository is an interface which various read and write operations on different files
 * and file types in the database will be implemented
 * 
 * @author Aaron Tay Han Yen
 * @author Li Haoyang
 */
public interface Repository {
	
	//1. For TxtFile, 2. For DatFile
	//int fileType = 1;
	
	public ArrayList readToList() throws IOException;
	public void saveList(List al) throws IOException;
}
