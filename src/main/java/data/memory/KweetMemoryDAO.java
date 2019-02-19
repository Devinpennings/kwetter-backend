package data.memory;

import data.interfaces.IKweetDAO;
import model.Kweet;

import javax.inject.Singleton;

/**
 * Created by Devin
 */
@Singleton
public class KweetMemoryDAO extends ModelMemoryDAO<Kweet> implements IKweetDAO {
}
