package data.jpa;

import data.interfaces.IDAO;
import model.Model;
import util.PaginationDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by Devin
 */
public abstract class ModelJPADAO<T extends Model> implements IDAO<T> {

    private Class<T> entityClass;

    @PersistenceContext(name = "PU")
    protected EntityManager entityManager;

    public ModelJPADAO(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public Collection<T> get() {
        return this.entityManager.createQuery("SELECT t from " + this.entityClass.getName() + " t", this.entityClass)
                                 .getResultList();
    }

    @Override
    public Collection<T> get(PaginationDetails paginationDetails) {
        TypedQuery<T> query =
                this.entityManager.createQuery("SELECT t from " + this.entityClass.getName() + " t LIMIT :limit, :offset", this.entityClass);
        query.setParameter("limit", paginationDetails.getIndexTo());
        query.setParameter("offset", paginationDetails.getIndexFrom());
        return query.getResultList();
    }

    @Override
    public Optional<T> get(String id) {
        return Optional.of(this.entityManager.find(this.entityClass, id));
    }

    @Override
    public T add(T item) {
        this.entityManager.persist(item);
        return item;
    }

    @Override
    public Collection<T> add(Collection<T> items) {
        items.forEach(this::add);
        return items;
    }

    @Override
    public Optional<T> update(T item) {

        T existing = item.getId() == null ? null : this.entityManager.find(this.entityClass, item.getId());

        if (existing != null) {
            this.entityManager.merge(item);
        } else {
            this.entityManager.persist(item);
        }

        if (existing == null) {
           return Optional.of(item);
        }

        return Optional.empty();

    }

    @Override
    public void delete(T item) {
        this.entityManager.remove(item);
    }
}
