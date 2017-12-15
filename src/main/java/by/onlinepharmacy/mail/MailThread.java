package by.onlinepharmacy.mail;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 public class MailThread extends Thread {
     private static Logger logger = LogManager.getLogger(MailThread.class);


    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private ResourceBundle resourceBundle;
    public MailThread(String sendToEmail,
                      String mailSubject, String mailText, ResourceBundle resourceBundle) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.resourceBundle = resourceBundle;
    }

     // Mail thread object initializing
     private void init() {
        Session mailSession = (new SessionCreator(resourceBundle)).createSession();
        mailSession.setDebug(true);
// create mail message
        message = new MimeMessage(mailSession);
        try {
// add parameter mail message
            message.setSubject(mailSubject);
            message.setContent(mailText, "text/html ; charset=utf-8");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));

        } catch (AddressException e) {
            logger.error("Incorrect recipient email address: " + sendToEmail, e);
         } catch (MessagingException e) {
            logger.error("Error when form message: ", e);
         }
    }
    public void run() {
        init();
        try {
     // send email
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Problem when send message: ", e);
         }
    }
}