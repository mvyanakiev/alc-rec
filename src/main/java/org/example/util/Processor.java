package org.example.util;

import org.example.model.UndefinedResult;
import org.example.scenarios.OtherProcesses;
import org.example.scenarios.beer.BeerWithoutNote;

import java.util.*;

import static org.example.config.Config.HIGH_POSSIBILITY;
import static org.example.config.Config.MEDIUM_POSSIBILITY;
import static org.example.util.Utils.addPoints;

public class Processor {

    public List<UndefinedResult> processWithSingleWords(List<UndefinedResult> undefinedResultList, Map<String, String> singleWordCombinations) {

        List<UndefinedResult> result = new ArrayList<>();

        for (UndefinedResult undefinedResult : undefinedResultList) {

            if (undefinedResult.getInputRecord().getNotes() != null) {

                String note = undefinedResult.getInputRecord().getNotes().toLowerCase().trim();

                if (singleWordCombinations.containsKey(note)) {
                    addPoints(singleWordCombinations.get(note), HIGH_POSSIBILITY, undefinedResult.getResultMap());
                }
            }

            result.add(undefinedResult);
        }

        return result;
    }


    public List<UndefinedResult> processWithManyWords(String key, Set<String> combinations, List<UndefinedResult> undefinedResultList) {

        List<UndefinedResult> result = new ArrayList<>();

        for (UndefinedResult undefinedResult : undefinedResultList) {

            if (undefinedResult.getInputRecord().getNotes() != null) {

                String[] notes = undefinedResult.getInputRecord().getNotes().toLowerCase().trim().split("\\s+");

                for (String note : notes) {
                    if (combinations.contains(note)) {
                        addPoints(key, MEDIUM_POSSIBILITY, undefinedResult.getResultMap());
                    }
                }
            }
            result.add(undefinedResult);
        }

        return result;
    }

    public List<UndefinedResult> mathProcess(List<UndefinedResult> undefinedResultList) {
        List<OtherProcesses> models = new ArrayList<>();

        models.add(new BeerWithoutNote());

        for (OtherProcesses model : models) {
            undefinedResultList = model.process(undefinedResultList);
        }

        return undefinedResultList;
    }
}
