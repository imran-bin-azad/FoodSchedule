package net.therap.util.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 4/30/14
 * Time: 9:42 AM
 */
public interface RowToObjectConverter<E> {
    public E getObjectFromNextRowOf(ResultSet resultSet) throws SQLException;
}
