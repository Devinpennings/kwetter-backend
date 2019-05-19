package data.interfaces;

import model.User;

import java.util.Optional;

/**
 * Created by Devin
 */
public interface IUserDAO extends IDAO<User> {
    Optional<User> getByUsername(String passwordAsString);
}
