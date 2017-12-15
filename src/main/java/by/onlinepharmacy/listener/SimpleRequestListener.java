package by.onlinepharmacy.listener;

import by.onlinepharmacy.controller.Controller;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
@WebListener
public class SimpleRequestListener implements ServletRequestListener {
    private static Logger logger = LogManager.getLogger(SimpleRequestListener.class);

    public void requestInitialized(ServletRequestEvent ev) {
        HttpServletRequest req = (HttpServletRequest) ev.getServletRequest();
        String command= req.getParameter("command");
        String name=req.getParameter("name" );
        //logger.info(command);
        //logger.info(name);

    }
    public void requestDestroyed(ServletRequestEvent ev) {

    }
}