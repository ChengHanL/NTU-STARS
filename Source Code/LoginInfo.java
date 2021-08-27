package Login;

import java.io.Serializable;

/**
 * LoginInfo is an entity class which stores the login information of a user. <p>
 * The 4 pieces of informations stored for a user are described below: <p>
 * username <p>
 * password<p>
 * type (Student or Admin)<p>
 * key: Uniquely identifies a user in the database (In case 2 users have the same 
 * username, password and type) <p>
 * Each information has its respective get and set method.
 * 
 * @author Aaron Tay Han Yen
 *
 */

public class LoginInfo implements Serializable{
	
	private String  username;
	private String  password;
	private String  type;
	private String  key ;
	
	/**
	 * Class constructor specifying all 4 login informations of a user
	 * 
	 * @param username username
	 * @param password password
	 * @param type (Student or Admin)
	 * @param key Uniquely identifies a user in the database
	 */
	public LoginInfo(String username, String password, String type, String key)
	{
		this.username = username;
		this.password = password;
		this.type = type;
		this.key = key;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
