package user;

import jpa.GenericJpaDao;

import java.util.List;

public class UserDAO extends GenericJpaDao<User> {
    /**
     * Constructs a {@code GenericJpaDao} object.
     *
     * @param entityClass the {@link Class} object that represents the entity
     *                    class
     */
    public UserDAO(Class<User> entityClass) {
        super(entityClass);
    }

    public List<User> getAllUsers() {
        return findAll();
    }
}
