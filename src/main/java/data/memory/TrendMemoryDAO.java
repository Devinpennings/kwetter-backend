package data.memory;

import data.interfaces.ITrendDAO;
import model.Trend;
import util.MapUtil;

import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Devin
 */
@Singleton
public class TrendMemoryDAO extends ModelMemoryDAO<Trend> implements ITrendDAO {

    public Collection<String> getPopular(int limit) {

        Map<String, Long> map = MapUtil.sortByValue(items.stream()
                .collect(Collectors.groupingBy(Trend::getMessage, Collectors.counting())));

        ArrayList<String> keys = new ArrayList<>(map.keySet());
        Collections.reverse(keys);
        return keys.subList(0, limit);

    }

    @Override
    public Collection<Trend> search(String term) {
        return this.items.stream()
                         .filter(trend -> trend.getMessage().contains(term))
                         .collect(Collectors.toList());
    }
}
