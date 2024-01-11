package org.example.service;

import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.example.model.Pair;
import org.example.model.UndefinedResult;
import org.example.repository.MlRepository;
import org.example.util.Processor;
import org.example.util.Utils;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.util.Utils.createUndefinedList;

public class AlcService {

    private final Processor processor;
    private final MlRepository repository;

    public AlcService(Processor processor, MlRepository repository) {
        this.processor = processor;
        this.repository = repository;
    }

    public List<OutputRecord> calculate(List<InputRecord> inputRecordList) {

        List<UndefinedResult> undefinedResultList = createUndefinedList(inputRecordList);

        Map<String, String> singleWordCombinations = repository.loadSingleWordCombinations();
        undefinedResultList = processor.processWithSingleWords(undefinedResultList, singleWordCombinations);

        Set<String> keys = repository.loadKeys();

        for (String key : keys) {
            Set<String> combinations = repository.loadByKey(key);

            undefinedResultList = processor.processWithManyWords(key, combinations, undefinedResultList);
        }

        undefinedResultList = processor.mathProcess(undefinedResultList);

        List<OutputRecord> finalResult = new ArrayList<>();

        for (UndefinedResult undefinedResult : undefinedResultList) {
            finalResult.add(Utils.convert(undefinedResult));
        }

        return finalResult.stream().filter(e -> e.getPoints() > 0).collect(Collectors.toList());
    }

//    public List<UndefinedResult> createUndefinedList(List<InputRecord> inputRecordList) {
//
//        List<UndefinedResult> result = new ArrayList<>();
//
//        for (InputRecord inputRecord : inputRecordList) {
//            UndefinedResult undefinedResult = new UndefinedResult();
//            undefinedResult.setInputRecord(inputRecord);
//            undefinedResult.setResultMap(new HashMap<>());
//            result.add(undefinedResult);
//        }
//
//        return result;
//    }
}
