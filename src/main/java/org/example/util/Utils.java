package org.example.util;

import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.example.model.UndefinedResult;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static List<UndefinedResult> convertToUndefinedReultsList(List<InputRecord> inputRecordList) {

        List<UndefinedResult> result = new ArrayList<>();

        for (InputRecord inputRecord : inputRecordList) {
            UndefinedResult undefinedResult = new UndefinedResult();
            undefinedResult.setInputRecord(inputRecord);
            undefinedResult.setResultMap(new HashMap<>());
            result.add(undefinedResult);
        }

        return result;
    }

    // TODO move to UtilConvertor class

    public static OutputRecord convertToOutputRecord(UndefinedResult undefinedResult) {

        Map<String, Integer> scores = undefinedResult.getResultMap();
        InputRecord inputRecord = undefinedResult.getInputRecord();

        String key = null;
        int score = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() > score) {
                score = entry.getValue();
                key = entry.getKey();
            }
        }

        if (key != null) {
            String[] split = key.split("\\s+");

            OutputRecord result = new OutputRecord(
                    inputRecord.getData(),
                    inputRecord.getExpense(),
                    inputRecord.getAccount(),
                    inputRecord.getPayee(),
                    inputRecord.getNotes(),
                    Double.parseDouble(split[1]),
                    split[0],
                    score);

            return result;
        }

        OutputRecord result = new OutputRecord(
                inputRecord.getData(),
                inputRecord.getExpense(),
                inputRecord.getAccount(),
                inputRecord.getPayee(),
                inputRecord.getNotes(),
                0.0,
                " Unrecognized",
                0);

        return result;
    }

    public static Map<String, Integer> addPoints(String key, int point, Map<String, Integer> map) {
        if (key == null) {
            return map;
        }

        if (!map.containsKey(key)) {
            map.put(key, point);
        } else {
            int currentPoint = map.get(key);
            map.replace(key, currentPoint + point);
        }

        return map;
    }

    public static double roundToNDecimalPlaces(double value, int decimalPlaces) {
        String pattern = "#.";
        for (int i = 0; i < decimalPlaces; i++) {
            pattern += "#";
        }

        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String formattedValue = decimalFormat.format(value);

        try {
            return Double.parseDouble(formattedValue);
        } catch (Exception e) {
            throw new RuntimeException("Грешка при закръгляне на числото.", e);
        }
    }
}
