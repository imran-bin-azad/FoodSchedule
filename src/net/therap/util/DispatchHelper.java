package net.therap.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/19/14
 * Time: 2:30 PM
 */
public class DispatchHelper {
    public static void dispatchRequest(String dispatcherPath, ServletRequest req, ServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(dispatcherPath);
        dispatcher.forward(req, resp);
    }
}
