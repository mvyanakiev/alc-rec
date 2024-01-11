package org.example.repository;

import java.util.*;

public class MlRepository {

    // просто симулирам репоситори понеже нямам база сега

    public Map<String, String> loadSingleWordCombinations() {

        Map<String, String> result = new HashMap<>();
        result.put("б3", "бира 3");
        result.put("b3", "бира 3");
        result.put("бира3", "бира 3");
        result.put("бира 3", "бира 3");

        result.put("уиски 0.7", "уиски 0.7");
        result.put("w0.7", "уиски 0.7");

        return result;
    }

    public Set<String> loadByKey(String key) {

        Set<String> result = new HashSet<>();
        result.add("б3");
        result.add("b3");
        result.add("бира3");
        result.add("бира 3");

        return result;
    }

    public Set<String> loadKeys() {
        Set<String> result = new HashSet<>();
        result.add("бира 3");
        result.add("уиски 0.7");

        return result;
    }
}
