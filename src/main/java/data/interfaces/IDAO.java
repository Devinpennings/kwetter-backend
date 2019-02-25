package data.interfaces;

import util.PaginationDetails;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Devin
 */
public interface IDAO<T> {

    Collection<T> get();

    Collection<T> get(PaginationDetails paginationDetails);

    Optional<T> get(String id);

    T add(T item);

    Collection<T> add(Collection<T> items);

    Optional<T> update(T item);

    void delete(T item);

    Collection<T> search(String term);

}
