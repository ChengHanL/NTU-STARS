package Repository;

import java.util.List;

public interface Database {
	public List read(String filename);
	public void write(String filename, List list);

}
