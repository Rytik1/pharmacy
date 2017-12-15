
package by.onlinepharmacy.filter;


import by.onlinepharmacy.entity.User;
import by.onlinepharmacy.exception.BusinessLogicException;
import by.onlinepharmacy.logic.ImplLogic.CommonLogic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebFilter(
        filterName = "UserInfoFilter",
        urlPatterns = {"/jsp/*"}
)
public class UserInfoFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession session = httpRequest.getSession();
            String login = (String) session.getAttribute("user");
            CommonLogic commonLogic=new CommonLogic();
            User user = commonLogic.findUser(login);
            session.setAttribute("USER", user);

            chain.doFilter(request, response);

        } catch (BusinessLogicException e) {
            throw new ServletException("Problem when add user to session: ", e);
        }
    }

    @Override
    public void destroy() {

    }
}

