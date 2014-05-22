package net.therap.dao;

import net.therap.domain.User;
import net.therap.util.DatabaseQueryExecutor;
import net.therap.util.RowToObjectConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/20/14
 * Time: 5:15 PM
 */
public class JdbcUserDao implements UserDao {
    @Override
    public User getUserByUserName(String userName) {
        String query = "SELECT * FROM login WHERE user_name='" + userName + "'";

        RowToObjectConverter<User> rowToObjectConverter = new RowToObjectConverter<User>() {
            @Override
            public User getObjectFromNextRowOf(ResultSet resultSet) throws SQLException {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                return new User(id, userName, password);
            }
        };

        List<User> users = DatabaseQueryExecutor.executeQueryForObject(query, rowToObjectConverter);

        User user = null;
        if (users.size() != 0){
            user = users.get(0);
        }
        return user;
    }

    @Override
    public User getInfoOfUser(User user) {
        String query = "SELECT name, is_admin FROM user_info WHERE id='" + user.getId() + "'";

        RowToObjectConverter<User> rowToObjectConverter = new RowToObjectConverter<User>() {
            @Override
            public User getObjectFromNextRowOf(ResultSet resultSet) throws SQLException {
                String name = resultSet.getString("name");
                boolean isAdmin = resultSet.getBoolean("is_admin");

                User user = new User();
                user.setName(name);
                user.setAdmin(isAdmin);
                return user;
            }
        };

        List<User> users = DatabaseQueryExecutor.executeQueryForObject(query, rowToObjectConverter);
        return users.get(0);
    }
}
