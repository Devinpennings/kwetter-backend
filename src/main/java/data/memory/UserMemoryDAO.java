package data.memory;

import data.interfaces.IUserDAO;
import model.User;

import javax.inject.Singleton;

/**
 * Created by Devin
 */
@Singleton
public class UserMemoryDAO extends ModelMemoryDAO<User> implements IUserDAO {
}
