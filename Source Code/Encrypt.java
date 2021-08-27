package Login;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import Repository.LoginTextRepository;

/**
 * Encrypt is used for the encryption of password (using MD5) and generation of new keys.
 * 
 * @author Aaron Tay Han Yen
 *
 */
public class Encrypt {
	
	/**
	 * Hashing of password using MD5
	 * 
	 * @param password the password to be encrypted
	 * @return encrypted password
	 */
	public static String hashing(String password)
	    {
		 	String passwordToHash = password;
	        String generatedPassword = null;
	        try {
	            // Create MessageDigest instance for MD5
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            //Add password bytes to digest
	            md.update(passwordToHash.getBytes());
	            //Get the hash's bytes 
	            byte[] bytes = md.digest();
	            //This bytes[] has bytes in decimal format;
	            //Convert it to hexadecimal format
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            //Get complete hashed password in hex format
	            generatedPassword = sb.toString();
	        } 
	        catch (NoSuchAlgorithmException e) 
	        {
	            e.printStackTrace();
	        }
	       // System.out.println(generatedPassword);
	        return generatedPassword;
	    }
	
	/**
	 * Generates a random key of length 2 using characters between 'a' and 'z'. 
	 * 
	 * @return a random key of length 2 using characters between 'a' and 'z'. 
	 */
	public static String generateKey()
	{
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 2;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    return generatedString.toUpperCase();
	}
	
	/**
	 * Generates a new key that is different from existing keys.<p>
	 * Key generations are done by calling the generateKey method.
	 * 
	 * @return a new(and valid) key 
	 * @throws IOException If an input or output exception occurred
	 */
	public static String checkKey() throws IOException
	{
		LoginTextRepository loginTextRepository = new LoginTextRepository();
		ArrayList loginDetailList = loginTextRepository.readToList();
		String key = null;
		
		//there must not be two identifical key
		boolean findKey = true;
		while(findKey) {
			key = Login.Encrypt.generateKey();
			findKey = false;
			for (int i=0; i<loginDetailList.size(); i++)
			{
				LoginInfo loginInfo = (LoginInfo)loginDetailList.get(i);
				if(loginInfo.getKey().equals(key))
				{
					findKey = true;
					System.out.println("duplicate key!");
					break;
				}
			}		
		}
		return key;
	}
}
