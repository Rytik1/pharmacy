package by.onlinepharmacy.filter;

import by.onlinepharmacy.util.date.UserType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//filter do redirect to index.jsp if role is false to select page (http://localhost:8080/controler?command=HOME)
@WebFilter(dispatcherTypes = { DispatcherType.FORWARD },urlPatterns = {"/jsp/admin/*"})
public class SecurityForvardAdminFilter implements Filter {
    private static Logger logger = LogManager.getLogger(SecurityForvardAdminFilter.class);

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String URI = httpServletRequest.getRequestURI();

        HttpSession session = httpServletRequest.getSession();
         UserType type =(UserType)   session.getAttribute("role");
          if (  type != UserType.ADMIN ) {
              logger.info("SecurityForvardAdminFilter redirect to index");

             httpServletResponse.sendRedirect("/index.jsp");
             return;
         }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}