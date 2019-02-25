package data.jpa;

import data.interfaces.IUserDAO;
import model.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * Created by Devin
 */
@Stateless
@Alternative
public class UserJPADAO extends ModelJPADAO<User> implements IUserDAO {
    @Override
    public Collection<User> search(String term) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE " +
                                                                                "u.username LIKE :term OR " +
                                                                                "u.email LIKE :term OR " +
                                                                                "u.biography LIKE :term OR " +
                                                                                "u.location LIKE :term",
                User.class);
        query.setParameter("term", "%" + term + "%");
        return query.getResultList();
    }
}
