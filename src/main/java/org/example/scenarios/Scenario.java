package org.example.scenarios;


import org.example.model.InputRecord;
import org.example.model.Pair;

import java.util.Map;

public interface Scenario {

    public Pair process (InputRecord inputRecord, Map<String, Integer> scores);

}
