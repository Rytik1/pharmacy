package by.onlinepharmacy.controller;

import by.onlinepharmacy.command.AbstractCommand;
import by.onlinepharmacy.util.date.NavigationType;
import by.onlinepharmacy.content.RequestContent;
import by.onlinepharmacy.content.RequestResult;
import by.onlinepharmacy.exception.CommandException;
import by.onlinepharmacy.factory.FactoryCommand;
import by.onlinepharmacy.pool.ConnectionPool;
import by.onlinepharmacy.resource.MessageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;


@WebServlet("/controler")
@MultipartConfig

public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            AbstractCommand executionCommand;
            RequestContent requestContent = new RequestContent();
            requestContent.extractValues(request);
            MessageManager.setContent(requestContent);
            executionCommand = new FactoryCommand().initCommand(requestContent);
            RequestResult requestResult = executionCommand.execute();

            requestContent.insertAttributes(request, session);

            String page = requestResult.getPage();
            NavigationType navigationType = requestResult.getNavigationType();

            if (navigationType == NavigationType.FORWARD) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
                requestDispatcher.forward(request, response);
            } else if (navigationType == NavigationType.REDIRECT) {
                response.sendRedirect(page);
            }
        } catch (CommandException e) {
            logger.error("Problem when process request: ", e);
            throw new ServletException("Problem when process request: ", e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        //closing connection pool when destroy application
        ConnectionPool.getInstance().closePool();
    }
}


