package net.therap.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/20/14
 * Time: 5:25 PM
 */
public class DataSourceConnector {
    public static Connection getConnection() {
        Context context;
        Connection connection;
        try {
            context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/FoodSchedule");
            connection = dataSource.getConnection();
            return connection;
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
