package net.therap.dao;

import net.therap.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/20/14
 * Time: 5:13 PM
 */
public interface UserDao {
    public User getUserByUserName(String userName);
    public User getInfoOfUser(User user);
}
