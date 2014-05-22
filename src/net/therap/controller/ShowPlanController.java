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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/21/14
 * Time: 1:27 PM
 */
@WebServlet({"/showPlan", "/updateSuccess"})
public class ShowPlanController extends HttpServlet {
    private MealPlanService mealPlanService;

    public ShowPlanController() {
        mealPlanService = new MealPlanService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DailyMealPlan> totalMealPlan = mealPlanService.getTotalMealPlan();
        setMealPlanInRequest(totalMealPlan, req);
        String dispatcherPath = getServletContext().getInitParameter("MealPlan");
        DispatchHelper.dispatchRequest(dispatcherPath, req, resp);
    }

    private void setMealPlanInRequest(List<DailyMealPlan> totalMealPlan, HttpServletRequest req) {
        req.setAttribute("totalMealPlan", totalMealPlan);
    }
}
