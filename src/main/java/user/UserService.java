package user;

import lombok.Data;
import user.model.User;

import java.util.List;

/**
 * Service class for User data handling at login.
 */
@Data
public class UserService {

    private User user;

    /**
     * Function for user authentication.
     * @param username username input
     * @param password password input
     * @return true if user was found false if not
     */
    public boolean auth(String username, String password) {
        UserDAO userManager = UserDAO.getInstance();

        this.user = User.builder()
                .username(username)
                .password(password)
                .build();

        List<User> registeredUsers;
        registeredUsers = userManager.findAll();

        boolean found = false;
        for (User u: registeredUsers) {
            if (u.getUsername().equals(this.user.getUsername()) && u.getPassword().equals(this.user.getPassword())) {
                found = true;
                break;
            }
        }

        return found;
    }
}
