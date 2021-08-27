package WaitList;

import java.util.ArrayList;

/**
 * GetWaitList performs query on the WaitList database
 * 
 * @author Aaron
 *
 */
public class GetWaitList {
	/**
	 * gets the number of people waiting for the particular course and index
	 * 
	 * @param waitList the waitList database
	 * @param courseIndex the courseIndex to be checked
	 * @param indexNum the indexNum to be checked
	 * @param queue queue
	 * @return the number of people waiting for the particular course and index
	 */
	public static String getQueue(ArrayList waitList, String courseIndex, String indexNum, String queue)
	{
		for (int i = 0 ; i < waitList.size() ; i++) {
        		WaitList myWaitList = (WaitList)waitList.get(i);
        		if(myWaitList.getCourseIndex().equals(courseIndex) && myWaitList.getIndexNum().equals(indexNum))
        			return queue;
		}
		return null;
	}
	
	/**
	 * Checks if a course has a queue (1 or more people on the waiting list)
	 * 
	 * @param waitList the waitList database
	 * @param courseIndex the courseIndex to be checked
	 * @param indexNum the indexNum to be checked
	 * @return false if there is no queue for this course, true otherwise
	 */
	public static boolean hasQueue(ArrayList waitList, String courseIndex, String indexNum)
	{
		for (int i = 0 ; i < waitList.size() ; i++) {
    		WaitList myWaitList = (WaitList)waitList.get(i);
    		if(myWaitList.getCourseIndex().equals(courseIndex) && myWaitList.getIndexNum().equals(indexNum) && myWaitList.getTotalQueue().equals("0"))
    			return false;
		}
		return true;
	}
	
}
