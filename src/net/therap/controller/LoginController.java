package net.therap.controller;

import net.therap.InvalidUserException;
import net.therap.domain.User;
import net.therap.listeners.ActiveUserCounter;
import net.therap.service.UserService;
import net.therap.util.DispatchHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/18/14
 * Time: 10:46 AM
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private UserService userService;

    public LoginController() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dispatcherPath = getServletContext().getInitParameter("LoginPage");
        DispatchHelper.dispatchRequest(dispatcherPath, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName, password, message, dispatcherPath;
        userName = req.getParameter("handle");
        password = req.getParameter("password");

        try {
            User user = userService.verifyUserAndGetLoginDetails(new User(userName, password));
            startSessionForUser(req, user);
            resp.sendRedirect("/welcome");
        } catch (InvalidUserException e) {
            message = e.getMessage();
            req.setAttribute("message", message);
            dispatcherPath = getServletContext().getInitParameter("LoginPage");
            DispatchHelper.dispatchRequest(dispatcherPath, req, resp);
        }
    }

    private void startSessionForUser(HttpServletRequest req, User user) {
        HttpSession session = req.getSession();
        session.setAttribute("authenticatedUser", user);
        System.out.println("Logged in Users: " + ActiveUserCounter.getActiveUsers());
    }
}
