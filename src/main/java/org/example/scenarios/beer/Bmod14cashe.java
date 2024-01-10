package org.example.scenarios.beer;

import org.example.model.Account;
import org.example.model.InputRecord;
import org.example.model.Pair;
import org.example.scenarios.Scenario;
import org.example.util.Utils;

import java.util.Map;

import static org.example.config.Config.BEER_3L;

public class Bmod14cashe implements Scenario {

    private final static double BEER_PRICE = 1.4;

    @Override
    public Pair process(InputRecord inputRecord, Map<String, Integer> scores) {

        String key = null;

//        if (inputRecord.getExpense() % BEER_PRICE == 0 && Account.CASH = inputRecord.getAccount()) {
//            key = BEER_3L;
//            scores = Utils.addPoint(key, scores);
//        }

        return new Pair(inputRecord, scores);
    }
}
