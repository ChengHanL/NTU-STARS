package AccessPeriod;

import java.util.ArrayList;

/**
 * GetAccessPeriod displays all possible access periods from the GlobalAccessPeriod database
 * and returns the chosen access period to the user.
 * 
 * @author ?
 *
 */
public class GetAccessPeriod {
	
	/**
	 * Displays all possible access periods.
	 * 
	 * @param accessPeriod An ArrayList of all possible access periods in the GlobalAccessPeriod database
	 */
	public static void printAccessPeriod(ArrayList accessPeriod)
	{
		for (int i = 0 ; i < accessPeriod.size() ; i++) {
			AccessPeriod accessPeriodObj = (AccessPeriod)accessPeriod.get(i);

					System.out.println((i+1) + ". Starting Access Period: " + accessPeriodObj.getStartAccessPeriod() + ", Ending Access Period: " + accessPeriodObj.getEndAccessPeriod());
		}
		System.out.println("\n");
	}
	
	/**
	 * Returns the accessPeriod chosen to be added for the student.
	 *
	 * @param accessPeriod An ArrayList of all possible access periods in the GlobalAccessPeriod database
	 * @param accessPeriodValue The choice of the access period to be added for the student
	 * @return accessPeriod to be added for the student. Returns nill if the choice is invalid.
	 */
	public static String getAccessPeriod(ArrayList accessPeriod, String accessPeriodValue)
	{
		for (int i = 0 ; i < accessPeriod.size() ; i++) {
			AccessPeriod accessPeriodObj = (AccessPeriod)accessPeriod.get(i);
			if(accessPeriodValue.equals(Integer.toString(i+1)))
			{
				return (accessPeriodObj.getStartAccessPeriod() + " - " + accessPeriodObj.getEndAccessPeriod());
				//System.out.println((i+1) + ". Starting Access Period: " + accessPeriodObj.getStartAccessPeriod() + ", Ending Access Period: " + accessPeriodObj.getEndAccessPeriod());
			}
		}
		System.out.println("Invalid Value");
		return "nill";	
	}	
}
