package net.therap.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/19/14
 * Time: 2:38 PM
 */
@WebFilter({"/*"})
public class LoginFilter implements Filter {
    private ServletRequest servletRequest;
    private ServletResponse servletResponse;
    private FilterChain filterChain;
    private String requestedURI;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        initialize(request, response, chain);

        boolean userLoggedIn = userExistsInSession();
        if (userLoggedIn) {
            doFilterForAuthenticatedUser();
        } else {
            doFilterForNewUser();
        }
    }

    private void initialize(ServletRequest request, ServletResponse response, FilterChain chain) {
        servletRequest = request;
        servletResponse = response;
        filterChain = chain;
        requestedURI = ((HttpServletRequest) servletRequest).getRequestURI();
    }

    private boolean userExistsInSession() {
        HttpSession session = getSessionFromRequest();
        return (session != null && session.getAttribute("authenticatedUser") != null);
    }

    private HttpSession getSessionFromRequest() {
        return ((HttpServletRequest) servletRequest).getSession(false);
    }

    private void doFilterForAuthenticatedUser() throws IOException, ServletException {
        String[] uriToRedirect = {"/login"};
        boolean requestUriContainsUriToRedirect = requestUriContainsElementOf(uriToRedirect);
        if (requestUriContainsUriToRedirect) {
            ((HttpServletResponse) servletResponse).sendRedirect("/home");
        } else {
            callFilterChain();
        }
    }

    private void doFilterForNewUser() throws IOException, ServletException {
        String[] uriToRedirect = {"/home", "/welcome", "/logout", "/showPlan", "/updatePlan", "/updateSuccess"};
        boolean requestUriContainsUriToRedirect = requestUriContainsElementOf(uriToRedirect);
        if (requestUriContainsUriToRedirect) {
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        } else {
            callFilterChain();
        }
    }

    private boolean requestUriContainsElementOf(String[] uriArray) {
        boolean contains = false;
        for (String uri : uriArray) {
            if (requestedURI.contains(uri)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    private void callFilterChain() throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
