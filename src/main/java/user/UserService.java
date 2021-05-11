package user;

import user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class UserService {

    // private static Logger logger = new LogManager().getLogger();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("oracle-unit");
    private EntityManager em = emf.createEntityManager();
    private UserService(EntityManager em) {
        this.em = em;
    }

    public User createUser(Long id, String username, String password) {
        User user = new User (username, password);
        this.em.persist(user);
        return user;
    }

    public Optional<User> auth(String username, String password) {
        this.em.find(User.class, username);
        this.em.find(User.class, password);

        return Optional.empty();
    }

//    public Optional<User> findUser(String username, String password) {
//        return Optional.ofNullable(em.find(User.class, username, password));
//    }

    public List<User> findAllUsers() {
        return em.createQuery("SELECT u FROM User u ORDER BY u.username", User.class).getResultList();
    }
}
