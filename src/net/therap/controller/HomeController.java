package net.therap.controller;

import net.therap.domain.DailyMealPlan;
import net.therap.domain.User;
import net.therap.service.MealPlanService;
import net.therap.service.UserService;
import net.therap.util.DispatchHelper;
import net.therap.util.TimeFormatter;

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
 * Date: 5/19/14
 * Time: 1:08 PM
 */
@WebServlet({"/home", "/welcome"})
public class HomeController extends HttpServlet {
    private UserService userService;

    public HomeController() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromRequest(req);
        user = userService.getUserInfoOf(user);
        setWelcomeMessageForUserInRequest(user, req);

        DailyMealPlan todaysMealPlan = getTodaysMealPlan();
        setTodaysMealPlanInRequest(todaysMealPlan, req);

        String dispatcherPath = getServletContext().getInitParameter("WelcomePage");
        DispatchHelper.dispatchRequest(dispatcherPath, req, resp);
    }

    private User getUserFromRequest(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return (User) session.getAttribute("authenticatedUser");
    }

    private void setWelcomeMessageForUserInRequest(User user, HttpServletRequest req) {
        String welcomeMessage = "hi " + user.getName();
        req.setAttribute("message", welcomeMessage);
    }

    private DailyMealPlan getTodaysMealPlan() {
        String today = TimeFormatter.getDayOfClientZone("BDT");
        MealPlanService mealPlanService = new MealPlanService();
        return mealPlanService.getMealPlanOf(today);
    }

    private void setTodaysMealPlanInRequest(DailyMealPlan todaysMealPlan, HttpServletRequest req) {
        req.setAttribute("breakfast", todaysMealPlan.getBreakfastMenu());
        req.setAttribute("lunch", todaysMealPlan.getLunchMenu());
    }
}
