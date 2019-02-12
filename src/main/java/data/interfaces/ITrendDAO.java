package data.interfaces;

import java.util.Collection;

/**
 * Created by Devin
 */
public interface ITrendDAO {

    public Collection<String> getPopular(int limit);

}
