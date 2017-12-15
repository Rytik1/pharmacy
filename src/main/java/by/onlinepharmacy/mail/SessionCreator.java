package by.onlinepharmacy.mail;

import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
public class SessionCreator {
    private String smtpHost;
    private String smtpPort;
    private String userName;
    private String userPassword;
    private Properties sessionProperties;

    public SessionCreator(ResourceBundle resourceBundle) {
        smtpHost = resourceBundle.getString("mail.smtp.host");
        smtpPort = resourceBundle.getString("mail.smtp.port");
        userName = resourceBundle.getString("mail.user.name");
        userPassword = resourceBundle.getString("mail.user.password");
// загрузка параметров почтового сервера в свойства почтовой сессии
        sessionProperties = new Properties();
        sessionProperties.setProperty("mail.transport.protocol", "smtp");
        sessionProperties.setProperty("mail.host", smtpHost);
        sessionProperties.put("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.port", smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.port", smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sessionProperties.put("mail.smtp.socketFactory.fallback", "false");
        sessionProperties.setProperty("mail.smtp.quitwait", "false");
    }

    public Session createSession() {
        return Session.getDefaultInstance(sessionProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}