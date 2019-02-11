package data;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Devin
 */
public interface IDAO<T> {

    Collection<T> get();

    Collection<T> get(PaginationDetails paginationDetails);

    Optional<T> get(long id);

    void add(T item);

    void add(Collection<T> items);

    void update(T item);

    void delete(T item);

}
