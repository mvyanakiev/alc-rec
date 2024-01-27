package org.example.service;

import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.example.model.UndefinedResult;
import org.example.repository.Repository;
import org.example.util.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.example.util.ConvertUtils.convertToOutputRecord;
import static org.example.util.ConvertUtils.convertToUndefinedReultsList;

public class AlcService {

    private final Processor processor;
    private final Repository repository;

    public AlcService(Processor processor, Repository repository) {
        this.processor = processor;
        this.repository = repository;
    }

    public List<OutputRecord> calculate(List<InputRecord> inputRecordList) {

        List<UndefinedResult> undefinedResultList = convertToUndefinedReultsList(inputRecordList);

        Map<String, String> singleWordCombinations = repository.loadSingleWordCombinations();
        undefinedResultList = processor.processWithSingleWords(undefinedResultList, singleWordCombinations);

        Set<String> keys = repository.loadKeys();

        for (String key : keys) {
            Set<String> combinations = repository.loadByKey(key);

            undefinedResultList = processor.processWithManyWords(key, combinations, undefinedResultList);
        }

//        List<Double> prices = Arrays.asList(1.6, 1.4, 1.2);
//        undefinedResultList = processor.processByPrice(prices, "бира", 0.5, undefinedResultList);

        undefinedResultList = processor.mathProcess(undefinedResultList);

        List<OutputRecord> finalResult = new ArrayList<>();

        for (UndefinedResult undefinedResult : undefinedResultList) {
            OutputRecord outputRecord = convertToOutputRecord(undefinedResult);

            if (outputRecord.getPoints() > 0) {
                finalResult.add(outputRecord);
            }
        }

        return finalResult;
    }
}
