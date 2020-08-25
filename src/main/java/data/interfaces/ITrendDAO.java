package data.interfaces;

import model.Trend;

import java.util.Collection;

/**
 * Created by Devin
 */
public interface ITrendDAO extends IDAO<Trend> {

    Collection<String> getPopular(int limit);

}
