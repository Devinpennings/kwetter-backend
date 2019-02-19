package data.memory;

import model.Mock;
import model.Trend;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Devin
 */
public class TrendMemoryDAOTest {

    @Test
    void testGetPopular(){

        Map<String, Integer> occurencesMap = new TreeMap<>();
        occurencesMap.put("Plek 1", 100);
        occurencesMap.put("Plek 2", 75);
        occurencesMap.put("Plek 3", 50);

        ArrayList<Trend> trends = new ArrayList<>();

        for (Map.Entry<String, Integer> kv : occurencesMap.entrySet()) {
            for (int i = 0; i < kv.getValue(); i++){
                trends.add(new Trend(0, kv.getKey()));
            }
        }

        trends.addAll(Mock.trends(100));

        TrendMemoryDAO dao = new TrendMemoryDAO();
        dao.add(trends);

        ArrayList<String> result = new ArrayList<>(dao.getPopular(3));
        assertEquals("Plek 1", result.get(0));
        assertEquals("Plek 2", result.get(1));
        assertEquals("Plek 3", result.get(2));

    }

}
