package by.onlinepharmacy.listener;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@WebListener()
public class HttpSessionListenerImpl implements HttpSessionListener {
    private static Logger logger = LogManager.getLogger(HttpSessionListenerImpl.class);

    @Override
    public void sessionCreated(HttpSessionEvent ev) {
        logger.info("create session");
        HttpSession session = ev.getSession();
        Long creationTime = session.getCreationTime();
        session.setAttribute("creationTime", creationTime);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent ev) {
        HttpSession session = ev.getSession();
        String login = (String) session.getAttribute("users");
        if (login == null) {
            logger.info("session destroyed ");
        }
    }
}