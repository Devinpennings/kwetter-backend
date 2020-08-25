package data.jpa;

import data.interfaces.IKweetDAO;
import model.Kweet;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * Created by Devin
 */
@Stateless
@Alternative
public class KweetJPADAO extends ModelJPADAO<Kweet> implements IKweetDAO {

    @Override
    public Collection<Kweet> search(String term) {
        TypedQuery<Kweet> query = entityManager.createQuery("SELECT k FROM Kweet k WHERE k.message LIKE :term",
                Kweet.class);
        query.setParameter("term", "%" + term + "%");
        return query.getResultList();
    }

}
