package org.example.util;

import java.util.Map;

public class Utils {

    public static Map<String, Integer> addPoint (String key, Map<String, Integer> map) {
        if (key == null) {
            return map;
        }

        if (!map.containsKey(key)) {
            map.put(key, 1);
        } else {
            int currentPoint = map.get(key);
            map.replace(key, currentPoint + 1);
        }

        return map;
    }
}
