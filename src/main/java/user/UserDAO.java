package user;

import jpa.GenericJpaDao;
import lombok.extern.slf4j.Slf4j;
import user.model.User;

import javax.persistence.Persistence;

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
        log.info("Connected to db.");
        return instance;
    }

    // TODO: user authentikacio bekotes
    public User auth(User user) {
        String auth_query = "SELECT u from User u where u.username=" + user.getUsername() + " and u.password=" + user.getPassword();
        return entityManager.createQuery(auth_query, User.class)
                .getSingleResult();
    }
}
