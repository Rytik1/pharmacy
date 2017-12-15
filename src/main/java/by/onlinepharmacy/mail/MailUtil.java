package by.onlinepharmacy.mail;


import by.onlinepharmacy.resource.MessageManager;

import java.util.ResourceBundle;

public final class MailUtil {
    private static ResourceBundle mailResource = ResourceBundle.getBundle("configuration.mail");
    private static final String  SITE_LINK = "http://localhost:8080/";

    public static void sendEmail(String medicamentName,  String email ) {

        String subject = "Receipt "+medicamentName+" in OnlinePharmacy" ;
         String link = SITE_LINK ;
         StringBuilder message = new StringBuilder();

         message.append("<div>")
                .append(MessageManager.getProperty("mail.hello")+"<br/><br/>")
                .append(MessageManager.getProperty("mail.letter")+"<h3>"+medicamentName+"</h3>"+"<br/>")
                .append(MessageManager.getProperty("mail.link") + " <a href=\"" + link + "\">"+"OnlinePharmacy"+"</a><br/><br/>")
                .append(MessageManager.getProperty ("mail.goodDay") + "<br/>")
                .append("</div>");

        String body = message.toString();
        MailThread mailOperator = new MailThread(email, subject, body, mailResource);
        mailOperator.start();
    }



}
