package net.therap.service;

import net.therap.InvalidUserException;
import net.therap.dao.JdbcUserDao;
import net.therap.dao.UserDao;
import net.therap.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: imran.azad
 * Date: 5/19/14
 * Time: 11:47 AM
 */
public class UserService {
    private UserDao userDao;

    public UserService(){
        userDao = new JdbcUserDao();
    }

    public User verifyUserAndGetLoginDetails(User user) throws InvalidUserException {
        String userName = user.getUserName();
        User verifiedUser = userDao.getUserByUserName(userName);
        boolean userMatchesVerifiedUser = userMatchesVerifiedUser(user, verifiedUser);
        if (userMatchesVerifiedUser) {
            return verifiedUser;
        } else {
            throw new InvalidUserException("invalid handle, password combination");
        }
    }

    private boolean userMatchesVerifiedUser(User user, User verifiedUser) {
        boolean matches = false;
        if (verifiedUser != null) {
            String password = user.getPassword();
            if (password.equals(verifiedUser.getPassword())) {
                matches = true;
            }
        }
        return matches;
    }

    public User getUserInfoOf(User user) {
        User userWithDetailInfo = userDao.getInfoOfUser(user);
        user.setName(userWithDetailInfo.getName());
        user.setAdmin(userWithDetailInfo.isAdmin());
        return user;
    }
}
