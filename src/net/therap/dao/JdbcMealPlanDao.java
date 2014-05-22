package net.therap.dao;

import net.therap.domain.DailyMealPlan;
import net.therap.util.DatabaseQueryExecutor;
import net.therap.util.RowToObjectConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/21/14
 * Time: 3:12 PM
 */
public class JdbcMealPlanDao implements MealPlanDao {
    private RowToObjectConverter rowToObjectConverter;
    private List<DailyMealPlan> mealPlans;

    public JdbcMealPlanDao() {
        setRowToObjectConverter();
    }

    private void setRowToObjectConverter() {
        rowToObjectConverter = new RowToObjectConverter<DailyMealPlan>() {
            @Override
            public DailyMealPlan getObjectFromNextRowOf(ResultSet resultSet) throws SQLException {
                int id = resultSet.getInt("id");
                String nameOfDay = resultSet.getString("day");
                String breakfast = resultSet.getString("breakfast");
                String lunch = resultSet.getString("lunch");

                DailyMealPlan mealPlan = new DailyMealPlan();
                mealPlan.setId(id);
                mealPlan.setPlanForDay(nameOfDay, breakfast, lunch);
                return mealPlan;
            }
        };
    }

    @Override
    public List<DailyMealPlan> getPlanForWeek() {
        String query = "SELECT * FROM food_menu";
        mealPlans = DatabaseQueryExecutor.executeQueryForObject(query, rowToObjectConverter);
        return mealPlans;
    }

    @Override
    public DailyMealPlan getMealPlanByDay(String day) {
        day = day.toLowerCase();
        String query = "SELECT * FROM food_menu WHERE day='" + day + "'";
        mealPlans = DatabaseQueryExecutor.executeQueryForObject(query, rowToObjectConverter);
        return mealPlans.get(0);
    }

    @Override
    public void updateMenu(String nameOfDay, String mealType, String menu) {
        String query = "UPDATE food_menu SET " + mealType + "=? WHERE day=?";
        int noOfAffectedRows = DatabaseQueryExecutor.executeAlterQuery(query, menu, nameOfDay);
    }
}
