package org.example.util;

import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.example.model.UndefinedResult;

import java.text.DecimalFormat;
import java.util.*;

public class Utils {

    // TODO move to UtilConvertor class

    public static List<UndefinedResult> convertToUndefinedReultsList(List<InputRecord> inputRecordList) {

        List<UndefinedResult> result = new ArrayList<>();

        for (InputRecord inputRecord : inputRecordList) {
            UndefinedResult undefinedResult = new UndefinedResult();
            undefinedResult.setInputRecord(inputRecord);
            undefinedResult.setResultMap(new HashMap<>());
            undefinedResult.setPointProducers(new TreeMap<>());
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
                    inputRecord.getId(),
                    inputRecord.getData(),
                    inputRecord.getExpense(),
                    inputRecord.getAccount(),
                    inputRecord.getPayee(),
                    inputRecord.getNotes(),
                    Double.parseDouble(split[1]),
                    split[0],
                    score,
                    undefinedResult.getPointProducers());

            return result;
        }

        OutputRecord result = new OutputRecord(
                inputRecord.getId(),
                inputRecord.getData(),
                inputRecord.getExpense(),
                inputRecord.getAccount(),
                inputRecord.getPayee(),
                inputRecord.getNotes(),
                0.0,
                " Unrecognized",
                0,
                new TreeMap<>());

        return result;
    }

    public static Map<String, Integer> addPoints(String key, int point, Map<String, Integer> resultMap) {
        if (key == null) {
            return resultMap;
        }

        if (!resultMap.containsKey(key)) {
            resultMap.put(key, point);
        } else {
            int currentPoint = resultMap.get(key);
            resultMap.replace(key, currentPoint + point);
        }

        return resultMap;
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

    public static String summarisedReport(List<OutputRecord> outputRecordList) {

        // TODO


//                количество по вид i kolko si dal, начало на периода, край на периода, общо разход за периода
        // консумация на ден / месец /година

        return null;
    }
}
