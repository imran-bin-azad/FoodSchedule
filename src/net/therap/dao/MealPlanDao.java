package net.therap.dao;

import net.therap.domain.DailyMealPlan;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/21/14
 * Time: 3:10 PM
 */
public interface MealPlanDao {
    public List<DailyMealPlan> getPlanForWeek();
    public DailyMealPlan getMealPlanByDay(String day);
    public void updateMenu(String nameOfDay, String mealType, String menu);
}
