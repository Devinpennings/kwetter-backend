package data.memory;

import data.interfaces.IKweetDAO;
import model.Kweet;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Devin
 */
@Singleton
public class KweetMemoryDAO extends ModelMemoryDAO<Kweet> implements IKweetDAO {

    @Override
    public Collection<Kweet> search(String term) {
        return this.items.stream()
                         .filter(kweet -> kweet.getMessage().contains(term))
                         .collect(Collectors.toList());
    }

}
