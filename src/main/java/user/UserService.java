package user;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import user.model.User;

import java.util.List;

/**
 * Service class for User data handling at login.
 */
@Slf4j
@Data
public class UserService {

    private User user;
    private UserDAO userManager;

    /**
     * Function for user authentication.
     * @param username username input
     * @param password password input
     * @return true if user was found false if not
     */
    public boolean auth(String username, String password) {
        this.userManager = UserDAO.getInstance();

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

        if (found) {
            log.info("Successful authentication");
        }else {
            log.info("Unsuccessful authentication");
        }

        return found;
    }
}
