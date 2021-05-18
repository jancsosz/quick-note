package user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void auth() {
        assertTrue(userService.auth("admin", "admin"));
        assertFalse(userService.auth("bla", "bla"));
    }
}
