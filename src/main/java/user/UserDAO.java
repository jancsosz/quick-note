package user;

import jpa.GenericJpaDao;
import user.model.User;

import javax.persistence.Persistence;

public class UserDAO extends GenericJpaDao<User> {

    private static UserDAO instance;

    private UserDAO(Class<User> entityClass) {
        super(entityClass);
    }

    public UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO(User.class);
            instance.setEntityManager(Persistence.createEntityManagerFactory("oracle-unit").createEntityManager());
        }
        return instance;
    }

    // TODO: user authentikacio bekotes
//    public User auth() {
//        return entityManager.createQuery("select u from User u where username=u.username and password=u.password", User.class)
//                .getSingleResult();
//    }
}
