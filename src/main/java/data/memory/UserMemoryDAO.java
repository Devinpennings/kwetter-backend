package data.memory;

import data.interfaces.IUserDAO;
import model.User;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Devin
 */
@Default
@Singleton
public class UserMemoryDAO extends ModelMemoryDAO<User> implements IUserDAO {

    @Override
    public Collection<User> search(String term) {
        return this.items.stream()
                         .filter(user ->
                                 user.getUsername().contains(term) ||
                                 user.getEmail().contains(term) ||
                                 user.getBiography().contains(term) ||
                                 user.getLocation().contains(term))
                         .collect(Collectors.toList());
    }

}
