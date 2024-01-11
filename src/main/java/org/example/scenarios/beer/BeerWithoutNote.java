package org.example.scenarios.beer;

import org.example.model.Account;
import org.example.model.UndefinedResult;
import org.example.scenarios.OtherProcesses;
import org.example.util.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.example.config.Config.LOW_POSSIBILITY;
import static org.example.util.Utils.addPoints;

public class BeerWithoutNote implements OtherProcesses {

    private final static double BEER_PRICE = 1.4;

    @Override
    public List<UndefinedResult> process(List<UndefinedResult> undefinedResultList) {
        List<UndefinedResult> result = new ArrayList<>();

        for (UndefinedResult undefinedResult : undefinedResultList) {
            double volume = undefinedResult.getInputRecord().getExpense() / BEER_PRICE;

            volume = Utils.roundToNDecimalPlaces(volume, 2);

            if ((volume % 1) == 0
                    && undefinedResult.getInputRecord().getNotes() == null
                    && undefinedResult.getInputRecord().getAccount() == Account.CASH
            ) {
                String key = "бира " + volume;
                addPoints(key, LOW_POSSIBILITY, undefinedResult.getResultMap());
            }

            result.add(undefinedResult);
        }

        return result;
    }
}
