package data.memory;

import data.interfaces.ITrendDAO;
import model.Trend;
import util.MapUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Devin
 */
public class TrendMemoryDAO extends ModelMemoryDAO<Trend> implements ITrendDAO {

    @Override
    public Collection<String> getPopular(int limit) {

        Map<String, Long> map = MapUtil.sortByValue(items.stream()
                .collect(Collectors.groupingBy(Trend::getMessage, Collectors.counting())));

        ArrayList<String> keys = new ArrayList<>(map.keySet());
        Collections.reverse(keys);
        return keys.subList(0, limit);

    }
}
