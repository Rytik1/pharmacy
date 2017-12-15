package by.onlinepharmacy.customtag;

import by.onlinepharmacy.resource.MessageManager;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;


@SuppressWarnings("serial")
public class CountLuckyTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        int resultWrite = 0;

        //generate Eagle & Rescue
        int number = (int) (Math.random() * 2);

        String numbers = String.valueOf(number);
        //check coin by client
        String checkCoin = String.valueOf(pageContext.getRequest().getParameter("checkCoin"));
        HttpSession session = pageContext.getSession();
        //number trying
        Integer counterChech = (Integer) session.getAttribute("counterChech");

        if (counterChech == null) {
            session.setAttribute("counterChech", 0);
            counterChech = 0;
        }
        //attr to print result picture in jsp  Eagle or Rescue
        session.setAttribute("resultCoin", numbers);

        //check result
        if (numbers.equals(checkCoin)) {
            counterChech++;
            session.setAttribute("counterChech", counterChech);
        } else {
            session.setAttribute("counterChech", 0);
            counterChech = 0;
            resultWrite = 4;
            //information when first enter to game
            if (checkCoin.equals("null")) {
                resultWrite = 5;
            }
        }


        String resultString = null;

        switch (counterChech) {
            case 1:
                resultWrite = 1;
                break;
            case 2:
                resultWrite = 2;
                break;
            case 3:
                resultWrite = 3;
                break;
        }

//different printer to jsp depends on result
        switch (resultWrite) {
            case 1:
                resultString = "  <h3 class=\"glow\">" + MessageManager.getProperty("message.one") + " </h3> ";
                break;
            case 2:
                resultString = "  <h3 class=\"glow\">" + MessageManager.getProperty("message.two") + "</h3> ";
                break;
            case 3:
                resultString = " <h2 class=\"glow\"> " + MessageManager.getProperty("message.five") +
                        "<img src=../resources/image/money.gif width=200 height=200  alt=игра >" + MessageManager.getProperty("message.six") +
                        "<img src=../resources/image/boyar.png style=\"background-color: inherit\" width=200 height=200  alt=игра >" +
                        " </h2> ";
                session.setAttribute("counterChech", 0);
                break;
            case 4:
                resultString = " <h3 class=\"glow\">" + MessageManager.getProperty("message.tree") + "</h3> ";
                break;
            case 5:
                resultString = " <h3 class=\"glow\">" + MessageManager.getProperty("message.four") + "</h3> ";
                break;

        }

        try {
            JspWriter out = pageContext.getOut();
            out.write(resultString);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}