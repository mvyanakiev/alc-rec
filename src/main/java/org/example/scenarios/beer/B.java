package org.example.scenarios.beer;

import jdk.internal.util.xml.impl.Pair;
import org.example.model.InputRecord;
import org.example.scenarios.Scenario;
import org.example.util.Utils;

import java.util.Map;

import static org.example.config.BEER_3L;

public class B implements Scenario {

    @Override
    public Pair process(InputRecord inputRecord, Map<String, Integer> scores) {

        String key = null;

        if (inputRecord.getNotes().equals("б3")) {
            key = BEER_3L;
            scores = Utils.addPoint(key, scores);
        }

        return new Pair(inputRecord, scores);
    }
}
