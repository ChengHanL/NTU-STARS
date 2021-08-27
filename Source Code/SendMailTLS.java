package SendMailTLS;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Simulates sending of mails
 * 
 * @author Aaron
 *
 */
public class SendMailTLS {
	/**
	 * Simulates sending of mail notification
	 * 
	 * @param courseIndex courseIndex
	 * @param indexNum indexNum 
	 * @param status status
	 * @param firstName firstName
	 * @param lastName lastName
	 * @param matricNum matricNum
	 * @param AU AU
	 * @param email email
	 */

	public static void mailNotification(String courseIndex, String indexNum, String status, String firstName, String lastName, String matricNum, 
			String AU, String email) {

		final String username = "ntuschoolstars@gmail.com"; // to be added
		final String password = "Origami1!"; // to be added

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("ntuschoolstars@gmail.com")); // to be added an email addr
			// InternetAddress.parse(email));
			message.setSubject("NTU STARS" + " Course " + courseIndex);
			message.setText("Dear " + firstName + " " + lastName + ","
				+ "\n\nYou have Added The Course" + "\n" + "Course Index: " + courseIndex + ", Index Number: " + indexNum + ", Status: " + status + "\nName: " + firstName + " " 
				+ lastName + "\nMatric Number:" + matricNum + "\nAU:" + AU
				+ "\n\nYour Regard,\nNTU");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
