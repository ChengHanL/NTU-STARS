package Calendar;

import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Sends an email to student after a course is added.
 * 
 * @author Aaron
 *
 */
public class MyCalendar {

	public static boolean checkAccessPeriod(String accessPeriod) throws ParseException
	{
	    Calendar cal = Calendar.getInstance();
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);      // NOTE!!! : Month from 0 to 11
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    int hour = cal.get(Calendar.HOUR_OF_DAY);
	    int minute = cal.get(Calendar.MINUTE);
	    int second = cal.get(Calendar.SECOND);
	    		
		String startingAccessPeriod = accessPeriod.substring(0,10);
		String endingAccessPeriod = accessPeriod.substring(13,23);
		System.out.println(startingAccessPeriod);
		System.out.println(endingAccessPeriod);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date startingPeriod = sdf.parse(startingAccessPeriod); //starting
		Date endingPeriod = sdf.parse(endingAccessPeriod); //ending
		Date today = sdf.parse(day + "/" + (month+1) + "/" + year);
		
		
		System.out.println("Today is: " + day + "/" + (month+1) + "/" + year + ", Your Access Period is " + accessPeriod);
		if(today.compareTo(startingPeriod) > 0 && today.compareTo(endingPeriod) < 0)
			return true;
		else
			return false;
			
	 
	   
	}
}
