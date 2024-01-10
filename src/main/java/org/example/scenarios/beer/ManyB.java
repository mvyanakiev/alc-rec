package org.example.scenarios.beer;

import org.example.model.InputRecord;
import org.example.model.Pair;
import org.example.scenarios.Scenario;
import org.example.util.Utils;

import java.util.*;

import static org.example.config.Config.BEER_3L;

public class ManyB implements Scenario {


    @Override
    public Pair process(InputRecord inputRecord, Map<String, Integer> scores) {

        String key = null;
        Set<String> validKeys = new HashSet<>();

        // TODO
        validKeys.add("б3");
        validKeys.add("бира3");


        List<String> notes = Arrays.asList(inputRecord.getNotes().split("\\s+"));

        for (String note : notes) {

            if (validKeys.contains(note)) {
                key = BEER_3L;
                scores = Utils.addPoint(key, scores);
            }
        }

        return new Pair(inputRecord, scores);
    }
}
