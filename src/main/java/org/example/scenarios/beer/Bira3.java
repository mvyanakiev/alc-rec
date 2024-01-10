package org.example.scenarios.beer;

import org.example.model.InputRecord;
import org.example.model.Pair;
import org.example.scenarios.Scenario;
import org.example.util.Utils;

import java.util.Map;

import static org.example.config.Config.BEER_3L;

public class Bira3 implements Scenario {

    private final static String CRITERIA = "бира3";

    @Override
    public Pair process(InputRecord inputRecord, Map<String, Integer> scores) {

        String key = null;

        if (CRITERIA.equals(inputRecord.getNotes())) {
            key = BEER_3L;
            scores = Utils.addPoint(key, scores);
        }

        return new Pair(inputRecord, scores);
    }
}
