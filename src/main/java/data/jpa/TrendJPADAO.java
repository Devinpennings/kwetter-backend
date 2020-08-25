package data.jpa;

import data.interfaces.ITrendDAO;
import model.Kweet;
import model.Trend;
import org.hibernate.internal.build.AllowPrintStacktrace;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * Created by Devin
 */
@Stateless
@Alternative
public class TrendJPADAO extends ModelJPADAO<Trend> implements ITrendDAO {

    @Override
    public Collection<String> getPopular(int limit) {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Trend> search(String term) {
        TypedQuery<Trend> query = entityManager.createQuery("SELECT t FROM Trend t WHERE t.message LIKE :term",
                Trend.class);
        query.setParameter("term", "%" + term + "%");
        return query.getResultList();
    }
}
