package net.therap.domain;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/21/14
 * Time: 1:30 PM
 */
public class DailyMealPlan {
    private int id;
    private String nameOfDay;
    private String breakfastMenu;
    private String lunchMenu;

    public void setId(int id) {
        this.id = id;
    }

    public void setPlanForDay(String nameOfDay, String breakfastMenu, String lunchMenu) {
        this.nameOfDay = nameOfDay;
        this.breakfastMenu = breakfastMenu;
        this.lunchMenu = lunchMenu;
    }

    public int getId() {
        return id;
    }

    public String getNameOfDay() {
        return nameOfDay;
    }

    public String getBreakfastMenu() {
        return breakfastMenu;
    }

    public String getLunchMenu() {
        return lunchMenu;
    }
}
