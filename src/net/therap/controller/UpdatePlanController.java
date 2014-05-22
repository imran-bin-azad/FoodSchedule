package net.therap.controller;

import net.therap.domain.DailyMealPlan;
import net.therap.service.MealPlanService;
import net.therap.util.DispatchHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/21/14
 * Time: 7:18 PM
 */
@WebServlet({"/updatePlan"})
public class UpdatePlanController extends HttpServlet {
    private String nameOfDay = null;
    private String mealType = null;
    private MealPlanService mealPlanService;

    public UpdatePlanController() {
        mealPlanService = new MealPlanService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveGetMethodDataFromRequest(req);
        String dispatcherPath = getServletContext().getInitParameter("UpdatePage");
        DispatchHelper.dispatchRequest(dispatcherPath, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newMenu = req.getParameter("newMenu");
        mealPlanService.changeMealPlan(nameOfDay, mealType, newMenu);
        resp.sendRedirect("/updateSuccess");
    }

    private void saveGetMethodDataFromRequest(HttpServletRequest req) {
        nameOfDay = req.getParameter("nameOfDay");
        mealType = req.getParameter("mealType");
    }
}
