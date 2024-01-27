package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.example.model.UndefinedResult;

import java.util.*;

import static org.example.config.Config.CSV_SEPARATOR;
import static org.example.util.Utils.cleanQuotes;

public class ConvertUtils {

    public static String convertToJson(Object object) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

    public static String convertToCsv(OutputRecord outputRecord) {
        StringBuilder sb = new StringBuilder();
        return sb.append(outputRecord.getId()).append(CSV_SEPARATOR)
                .append(outputRecord.getData()).append(CSV_SEPARATOR)
                .append(outputRecord.getExpense()).append(CSV_SEPARATOR)
                .append(outputRecord.getAccount()).append(CSV_SEPARATOR)
                .append(outputRecord.getPayee()).append(CSV_SEPARATOR)
                .append(outputRecord.getOriginalNote()).append(CSV_SEPARATOR)
                .append(outputRecord.getVolume()).append(CSV_SEPARATOR)
                .append(outputRecord.getKind()).append(CSV_SEPARATOR)
                .append(outputRecord.getPoints()).append(CSV_SEPARATOR)
                .append(convertPointProducers(outputRecord.getPointProducers()))
                .toString();
    }

    private static String convertPointProducers(Map<String, Integer> pointProducers) {
        StringBuilder sb = new StringBuilder();
        pointProducers.forEach((k, v) -> sb.append(k).append("->").append(v).append(" | "));
        return sb.toString();
    }

    public static List<InputRecord> convertToInputRecordList(List<String[]> inputContent) {
        List<InputRecord> inputRecordList = new ArrayList<>();

        int i = 1;
        while (!"".equals(inputContent.get(i)[0])) {
            InputRecord inputRecord = new InputRecord();

            inputRecord.setId(i);
            inputRecord.setData(inputContent.get(i)[0]);
            inputRecord.setCategory(cleanQuotes(inputContent.get(i)[1]));
            inputRecord.setSubCategory(cleanQuotes(inputContent.get(i)[2]));
            inputRecord.setExpense(Double.parseDouble(cleanQuotes(inputContent.get(i)[3]).trim()));
            inputRecord.setAccount(cleanQuotes(inputContent.get(i)[4]));
            inputRecord.setPayee(cleanQuotes(inputContent.get(i)[5]));
            inputRecord.setNotes(cleanQuotes(inputContent.get(i)[6]));

            inputRecordList.add(inputRecord);
            i++;
        }

        return inputRecordList;
    }

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
}
