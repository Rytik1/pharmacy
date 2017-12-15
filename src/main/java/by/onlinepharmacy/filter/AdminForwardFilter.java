package by.onlinepharmacy.filter;

import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.util.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by User on 22.07.2017.
 */
@WebFilter(dispatcherTypes = { DispatcherType.FORWARD }, urlPatterns = { "/jsp/administrator/*" } )
public class AdminForwardFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        UserType type = (UserType) session.getAttribute("role");
        if( !type.equals(UserType.ADMIN))  {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
        chain.doFilter(request, response);
    }


    public void destroy()  {
    }
}

