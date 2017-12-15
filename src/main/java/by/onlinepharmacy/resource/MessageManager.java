package by.onlinepharmacy.resource;

import by.onlinepharmacy.content.RequestContent;

import java.util.Locale;
import java.util.ResourceBundle;


public class MessageManager {
    private static Locale locale;
    private static RequestContent content;

    private MessageManager() {
    }

    public static String getProperty(String key) {
        String localeFromJsp = content.getSessionAttribute("language");
        switch (localeFromJsp) {
            case "ru_RU":
                locale = new Locale("ru", "RU");
                break;
            case "en_US":
                locale = new Locale("en", "US");
                break;
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("localization.messages", locale);

        return resourceBundle.getString(key);

    }

    public static void setContent(RequestContent content) {
        MessageManager.content = content;
    }

}