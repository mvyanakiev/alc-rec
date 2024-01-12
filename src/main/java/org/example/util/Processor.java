package org.example.util;

import org.example.model.Account;
import org.example.model.UndefinedResult;
import org.example.scenarios.OtherProcesses;
import org.example.scenarios.beer.BeerWithoutNote;
import org.example.scenarios.beer.WineWithoutNote;

import java.util.*;

import static org.example.config.Config.*;
import static org.example.util.Utils.addPoints;

public class Processor {

    public List<UndefinedResult> processWithSingleWords(List<UndefinedResult> undefinedResultList,
                                                        Map<String, String> singleWordCombinations) {

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


    public List<UndefinedResult> processWithManyWords(String key, Set<String> combinations,
                                                      List<UndefinedResult> undefinedResultList) {

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
        models.add(new WineWithoutNote());

        for (OtherProcesses model : models) {
            undefinedResultList = model.process(undefinedResultList);
        }

        return undefinedResultList;
    }

    public List<UndefinedResult> processByPrice(List<Double> prices, String key, double volumePerUnit,
                                                List<UndefinedResult> undefinedResultList) {
        List<UndefinedResult> result = new ArrayList<>();

        for (UndefinedResult undefinedResult : undefinedResultList) {
            for (Double pricePerUnit : prices) {
                double expense = undefinedResult.getInputRecord().getExpense();
                double count = expense / pricePerUnit;

                count = Utils.roundToNDecimalPlaces(count, 2);

                if ((count % 1) == 0
                        && undefinedResult.getInputRecord().getNotes() == null
                        && undefinedResult.getInputRecord().getAccount() == Account.CASH
                ) {
                    double totalVolume = volumePerUnit * count;
                    String keyToWrite = key + " " + totalVolume;
                    addPoints(keyToWrite, LOW_POSSIBILITY, undefinedResult.getResultMap());
                }
            }
            result.add(undefinedResult);
        }
        return result;
    }
}
