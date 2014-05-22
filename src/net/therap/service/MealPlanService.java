package net.therap.service;

import net.therap.dao.JdbcMealPlanDao;
import net.therap.dao.MealPlanDao;
import net.therap.domain.DailyMealPlan;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/21/14
 * Time: 3:08 PM
 */
public class MealPlanService {
    private MealPlanDao mealPlanDao;

    public MealPlanService() {
        mealPlanDao = new JdbcMealPlanDao();
    }

    public List<DailyMealPlan> getTotalMealPlan() {
        return mealPlanDao.getPlanForWeek();
    }

    public DailyMealPlan getMealPlanOf(String day) {
        return mealPlanDao.getMealPlanByDay(day);
    }

    public void changeMealPlan(String nameOfDay, String mealType, String menu) {
        mealPlanDao.updateMenu(nameOfDay, mealType, menu);
    }
}
