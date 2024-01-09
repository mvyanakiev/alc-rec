package org.example.scenarios;


import org.example.model.InputRecord;
import javafx.util.*;

import java.util.Map;

public interface Scenario {

    Pair<Integer, String> pair = new Pair(1,"a");

    public Pair process (InputRecord inputRecord, Map<String, Integer> scores);

}
