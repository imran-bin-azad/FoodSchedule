package net.therap.listeners;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/25/14
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
@WebListener()
public class ActiveUserCounter implements HttpSessionListener {
    private static int activeUsers;

    public static int getActiveUsers() {
        return activeUsers;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeUsers++;
        setUserCountInServletContext(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        activeUsers--;
        setUserCountInServletContext(se);
    }

    private void setUserCountInServletContext(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("activeUsers", String.valueOf(activeUsers));
    }
}
