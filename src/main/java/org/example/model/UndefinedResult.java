package org.example.model;

import java.util.Map;

public class UndefinedResult {

    private InputRecord inputRecord;
    private Map<String, Integer> resultMap;
    private Map<String, Integer> pointProducers;

    public InputRecord getInputRecord() {
        return inputRecord;
    }

    public void setInputRecord(InputRecord inputRecord) {
        this.inputRecord = inputRecord;
    }

    public Map<String, Integer> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Integer> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<String, Integer> getPointProducers() {
        return pointProducers;
    }

    public void setPointProducers(Map<String, Integer> pointProducers) {
        this.pointProducers = pointProducers;
    }
}
