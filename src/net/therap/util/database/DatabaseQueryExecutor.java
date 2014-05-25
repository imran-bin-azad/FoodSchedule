package net.therap.util.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/20/14
 * Time: 5:44 PM
 */
public class DatabaseQueryExecutor {
    public static <E> List<E> executeQueryForObject(String query, RowToObjectConverter<E> rowToObjectConverter) {
        List <E> listOfE = new ArrayList<>();
        try (
                Connection connection = DataSourceConnector.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfE.add(rowToObjectConverter.getObjectFromNextRowOf(resultSet));
            }
            return listOfE;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static int executeAlterQuery(String query, Object... parameters) {
        try (
                Connection connection = DataSourceConnector.getConnection();
                PreparedStatement tempStatement = connection.prepareStatement(query);
                PreparedStatement statement = setParametersInPreparedStatement(tempStatement, parameters)
        ) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private static PreparedStatement setParametersInPreparedStatement(
        PreparedStatement preparedStatement, Object... parameters) throws SQLException {
        int i = 1;
        for (Object parameter : parameters) {
            if (parameter instanceof String) {
                preparedStatement.setString(i, (String) parameter);
            } else if (parameter instanceof Integer) {
                preparedStatement.setInt(i, (Integer) parameter);
            } else if (parameter instanceof Long) {
                preparedStatement.setLong(i, (Long) parameter);
            } else if (parameter instanceof Float) {
                preparedStatement.setFloat(i, (Float) parameter);
            } else if (parameter instanceof Double) {
                preparedStatement.setDouble(i, (Double) parameter);
            } else if (parameter instanceof Date) {
                preparedStatement.setDate(i, (Date) parameter);
            } else if (parameter instanceof Blob) {
                preparedStatement.setBlob(i, (Blob) parameter);
            }
            i++;
        }
        return preparedStatement;
    }
}
