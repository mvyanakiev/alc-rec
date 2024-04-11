package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.example.model.SummarisedElement;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.example.config.Config.SUBCATEGORY_ALC;
import static org.example.util.ConvertUtils.convertToJson;

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

    public static String summarisedReport(List<OutputRecord> outputRecordList, LocalDate firstDate, LocalDate lastDate) {

        // TODO extract mount from LocalDate
        long daysBetween = lastDate.toEpochDay() - firstDate.toEpochDay();

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
                    .append(String.format("%.2f", v.getExpense()))
                    .append(" лв.").append(" (").append(String.format("%.2f", v.getVolume() / daysBetween * 1000))
                    .append(" ml/ден)").append(System.lineSeparator());

            totalExpense.set(totalExpense.get() + v.getExpense());
        });

        result.append(System.lineSeparator()).append("Общо: ").append(String.format("%.2f", totalExpense.get())).append(" лв.");

        // описваш наличното и смята само изпитото
        // да чете и от забавление-други

        return result.toString();
    }

    public static String cleanQuotes(String in) {
        return in.substring(1, in.length() - 1);
    }

    public static void validateOutput(List<InputRecord> inputRecordList, List<OutputRecord> outputRecordList)
            throws JsonProcessingException {

        Set<String> missingRecords = new HashSet<>();
        Set<Integer> outputRecordIdSet = new HashSet<>();
        outputRecordList.forEach(outputRecord -> outputRecordIdSet.add(outputRecord.getId()));
        double price = 0;

        for (InputRecord inputRecord : inputRecordList) {
            if (inputRecord.getSubCategory().equalsIgnoreCase(SUBCATEGORY_ALC)
                    && !outputRecordIdSet.contains(inputRecord.getId())) {

                System.out.println("Входящ липсващ запис: ");
                System.out.println(convertToJson(inputRecord));

                price += inputRecord.getExpense();
                missingRecords.add(inputRecord.getNotes());
            }
        }

        if (price > 0) {
            System.out.println("Разлика: " + price);
            System.out.println("Неразпознати записи: ");
            missingRecords.forEach(System.out::println);
        }
    }
}
