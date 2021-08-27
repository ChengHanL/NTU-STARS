package AccessPeriod;

import java.io.Serializable;

/**
 * AccessPeriod is an entity class which stores the period of time
 * that students are allowed to access the STAR System. <p>
 * Each AccessPeriod has a startAccessPeriod(dd/mm/yyyy) and a endAccessPeriod(dd/mm/yyyy): <p>
 * 
 * @author ?
 *
 */
public class AccessPeriod implements Serializable{
	private String startAccessPeriod;
	private String endAccessPeriod;
	
	public AccessPeriod(String startAccessPeriod, String endAccessPeriod)
	{
		this.startAccessPeriod = startAccessPeriod;
		this.endAccessPeriod = endAccessPeriod;
	}
	
	public String getStartAccessPeriod() {
		return startAccessPeriod;
	}

	public void setStartAccessPeriod(String startAccessPeriod) {
		this.startAccessPeriod = startAccessPeriod;
	}

	public String getEndAccessPeriod() {
		return endAccessPeriod;
	}

	public void setEndAccessPeriod(String endAccessPeriod) {
		this.endAccessPeriod = endAccessPeriod;
	}
	
}
