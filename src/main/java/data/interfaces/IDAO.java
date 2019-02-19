package data.interfaces;

import data.PaginationDetails;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Devin
 */
public interface IDAO<T> {

    Collection<T> get();

    Collection<T> get(PaginationDetails paginationDetails);

    Optional<T> get(long id);

    T add(T item);

    Collection<T> add(Collection<T> items);

    Optional<T> update(T item);

    void delete(T item);

}
