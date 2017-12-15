package by.onlinepharmacy.listener;

/**
 * Created by User on 22.07.2017.
 */



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;



    @WebListener
    public class ServletRequestAttributeListenerImpl implements ServletRequestAttributeListener {
        private static Logger logger = LogManager.getLogger(ServletRequestAttributeListenerImpl.class);

        @Override
        public void attributeAdded(ServletRequestAttributeEvent event) {
            logger.info("add: " + event.getClass().getSimpleName() + " : "+ event.getName()
                   + " : " + event.getValue());
        }

        @Override
        public void attributeRemoved(ServletRequestAttributeEvent event) {
        }

        @Override
        public void attributeReplaced(ServletRequestAttributeEvent event) {
        }
    }

