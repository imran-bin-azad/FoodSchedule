package net.therap.controller;

import net.therap.listeners.ActiveUserCounter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/20/14
 * Time: 10:49 AM
 */
@WebServlet({"/logout"})
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        session.invalidate();
        System.out.println("Logged in Users: " + ActiveUserCounter.getActiveUsers());
        resp.sendRedirect("/login");
    }
}
