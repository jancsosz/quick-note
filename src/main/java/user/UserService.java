package user;

import org.slf4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;

public class UserService {

    // private static Logger logger = new LogManager().getLogger();
    private EntityManager em;
    private UserService(EntityManager em) {
        this.em = em;
    }

    public User create(Long id, String username, String password) {
        User user = new User (id, username, password);
        em.persist(user);
        return user;
    }

//    public Optional<User> findUser(String username, String password) {
//        return Optional.ofNullable(em.find(User.class, username, password));
//    }

    public List<User> findAllUsers() {
        return em.createQuery("SELECT u FROM User u ORDER BY u.username", User.class).getResultList();
    }
}
