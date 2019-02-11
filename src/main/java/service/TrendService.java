package service;

import data.interfaces.IDAO;
import data.interfaces.ITrendDAO;
import data.memory.ModelMemoryDAO;
import model.Trend;

import java.util.Collection;

/**
 * Created by Devin
 */
public class TrendService {

    private ITrendDAO trendDAO;

    public Collection<String> getPopular(int limit){

        return trendDAO.getPopular(limit);

    }
}
