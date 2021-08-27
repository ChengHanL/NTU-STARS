package WaitList;

import java.io.Serializable;

/**
 * WaitList is an entity class which stores the number of people on the waiting
 *  queue for each course
 * 
 * @author Aaron
 *
 */
public class WaitList implements Serializable{
	private String courseIndex;
	private String indexNum;
	private String totalQueue;
	
	/**
	 * Constructor
	 * 
	 * @param courseIndex courseIndex
	 * @param indexNum indexNum
	 * @param totalQueue number of people on the waiting
	 */
	public WaitList(String courseIndex, String indexNum, String totalQueue)
	{
		this.courseIndex = courseIndex;
		this.indexNum = indexNum;
		this.totalQueue = totalQueue;
	}

	public String getCourseIndex() {
		return courseIndex;
	}

	public void setCourseIndex(String courseIndex) {
		this.courseIndex = courseIndex;
	}

	public String getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(String indexNum) {
		this.indexNum = indexNum;
	}

	public String getTotalQueue() {
		return totalQueue;
	}

	public void setTotalQueue(String totalQueue) {
		this.totalQueue = totalQueue;
	}
	
}
