package org.example.util;

import org.example.model.OutputRecord;
import org.example.model.SummarisedElement;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Utils {

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
        Map<String, SummarisedElement> summaryMap = new HashMap<>();

        for (OutputRecord outputRecord : outputRecordList) {
            if (summaryMap.containsKey(outputRecord.getKind())) {
                SummarisedElement oldSummarisedElement = summaryMap.get(outputRecord.getKind());

                SummarisedElement newSummarisedElement =
                        new SummarisedElement(oldSummarisedElement.getVolume() + outputRecord.getVolume(),
                                oldSummarisedElement.getExpense() + outputRecord.getExpense());
                summaryMap.replace(outputRecord.getKind(), newSummarisedElement);
            } else {
                summaryMap.put(outputRecord.getKind(),
                        new SummarisedElement(outputRecord.getVolume(), outputRecord.getExpense()));
            }
        }

        AtomicReference<Double> totalExpense = new AtomicReference<>((double) 0);
        StringBuilder result = new StringBuilder();

        summaryMap.forEach((k, v) -> {
            result.append(k).append(":\t").append(v.getVolume()).append(" l, сума: ")
                    .append(String.format("%.2f",v.getExpense()))
                    .append(" лв.").append(System.lineSeparator());

            totalExpense.set(totalExpense.get() + v.getExpense());
        });

        result.append(System.lineSeparator()).append("Общо: ").append( String.format("%.2f",totalExpense.get())).append(" лв.");

        // TODO начало на периода, край на периода, консумация на ден / месец /година
        // описваш наличното и смята само изпитото
        // да чете и от забавление-други

        return result.toString();
    }

    public static String cleanQuotes(String in) {
        return in.substring(1, in.length()-1);
    }
}
