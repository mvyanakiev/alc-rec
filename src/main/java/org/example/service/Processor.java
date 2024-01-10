package org.example.service;

import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.example.scenarios.Scenario;
import org.example.scenarios.beer.B;

import java.util.*;

public class Processor {

    public List<OutputRecord> process(List<InputRecord> inputRecordList) {

        List<Scenario> models = new ArrayList<>(Arrays.asList(new B()));
        OutputRecord outputRecord;
        List<OutputRecord> result = new ArrayList<>();

        for (InputRecord inputRecord : inputRecordList) {

            Map<String, Integer> scores = new HashMap<>();

            for (Scenario model : models) {
                model.process(inputRecord, scores);
            }

            if (!scores.isEmpty()) {
                outputRecord = convert(inputRecord, scores);
                result.add(outputRecord);
            }
        }

        return result;
    }

    private OutputRecord convert(InputRecord inputRecord, Map<String, Integer> scores) {

        String key = null;
        int score = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {

            if (entry.getValue() > score) {
                score = entry.getValue();
                key = entry.getKey();
            }
        }

        assert key != null;
        String[] split = key.split("\\s+");

        OutputRecord result = new OutputRecord(
                inputRecord.getData(),
                inputRecord.getExpense(),
                inputRecord.getAccount(),
                inputRecord.getPayee(),
                Double.parseDouble(split[1]),
                split[0]);

        return result;
    }
}
