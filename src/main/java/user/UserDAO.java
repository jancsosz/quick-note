package user;

import jpa.GenericJpaDao;
import lombok.extern.slf4j.Slf4j;
import user.model.User;

import javax.persistence.Persistence;

/**
 * Data Access Object for User Entities.
 */
@Slf4j
public class UserDAO extends GenericJpaDao<User> {

    private static UserDAO instance;

    private UserDAO(Class<User> entityClass) {
        super(entityClass);
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO(User.class);
            instance.setEntityManager(Persistence.createEntityManagerFactory("mysql-unit").createEntityManager());
        }
        log.info("Connected to User table.");

        return instance;
    }
}
