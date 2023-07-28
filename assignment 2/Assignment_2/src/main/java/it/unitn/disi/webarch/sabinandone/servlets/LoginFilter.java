package it.unitn.disi.webarch.sabinandone.servlets;

import it.unitn.disi.webarch.sabinandone.utilities.UserBean;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;


@WebFilter(filterName = "LoginFilter", urlPatterns = {"/banner.jsp", "/mainpage.jsp", "/gamepage.jsp", "/controlpage.jsp", "/PrepareServlet", "/LoadServlet", "/UpdateServlet"})
public class LoginFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;
    private HttpServletRequest httpRequest;

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("LoginFilter:Initializing filter");
            }
        }
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String loginURL = req.getContextPath() + "/login.jsp";

        //set some boolean variables and take the user from session
        boolean loggedIn = session != null && session.getAttribute("logged") != null;
        UserBean userbean = (UserBean)session.getAttribute("UserBean");
        String controlpageURI = req.getContextPath() + "/controlpage.jsp";
        boolean isControlRequest = req.getRequestURI().equals(controlpageURI);

        //check if logged
        if (loggedIn) {
            //check if it is the admin or not
            if(!userbean.getUser().equals("admin")){
                //the user is not the admin, so we check also if s/he tries to access the control page
                if (isControlRequest){
                    String errorURL = req.getContextPath() + "/error.jsp";
                    res.sendRedirect(errorURL);
                }
            }
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURL);
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("LoginFilter()");
        }
        StringBuffer sb = new StringBuffer("LoginFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }


    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
