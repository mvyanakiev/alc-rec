package org.example.service;

import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.example.scenarios.Scenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Processor {

    public List<OutputRecord> make(List<InputRecord> inputRecordList) {

        List<Scenario> models = new ArrayList<>();
        OutputRecord outputRecord = null;
        List<OutputRecord> result = new ArrayList<>();

        for (InputRecord inputRecord : inputRecordList) {

            Map<String, Integer> scores = new HashMap<>();

            for (Scenario model : models) {
                model.process(inputRecord, scores);
            }

            outputRecord = convert(inputRecord, scores);
            result.add(outputRecord);
        }

        return result;
    }

    private OutputRecord convert(InputRecord inputRecord, Map<String, Integer> scores) {

        // TODO find big score

        InputRecord bigestScore = null;

        OutputRecord result = new OutputRecord();

        // TODO fill result;


        return result;

    }


}
