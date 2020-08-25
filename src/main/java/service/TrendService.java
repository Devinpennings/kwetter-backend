package service;

import data.interfaces.ITrendDAO;
import data.memory.TrendMemoryDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Devin
 */
@Stateless
public class TrendService {

    @Inject
    private ITrendDAO trendDAO;

    public Collection<String> getPopular(int limit){

        return trendDAO.getPopular(limit);

    }
}
