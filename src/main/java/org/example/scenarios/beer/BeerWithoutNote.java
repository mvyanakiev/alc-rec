package org.example.scenarios.beer;

import org.example.model.Account;
import org.example.model.UndefinedResult;
import org.example.scenarios.OtherProcesses;
import org.example.util.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.example.config.Config.LOW_POSSIBILITY;
import static org.example.config.Config.SUBCATEGORY_ALC;
import static org.example.util.Utils.addPoints;

public class BeerWithoutNote implements OtherProcesses {

    private final static double BEER_PRICE = 1.4;
    private final static double VOLUME_PER_UNIT = 0.5;
    private final static String KEY = "бира ";

    @Override
    public List<UndefinedResult> process(List<UndefinedResult> undefinedResultList) {
        List<UndefinedResult> result = new ArrayList<>();

        for (UndefinedResult undefinedResult : undefinedResultList) {
            if (!SUBCATEGORY_ALC.equals(undefinedResult.getInputRecord().getSubCategory())) {
                continue;
            }

            double count = undefinedResult.getInputRecord().getExpense() / BEER_PRICE;
            count = Utils.roundToNDecimalPlaces(count, 2);

            if ((count % 1) == 0
//                    && undefinedResult.getInputRecord().getNotes() == null
                    && undefinedResult.getInputRecord().getAccount() == Account.CASH
            ) {
                String key = KEY + count * VOLUME_PER_UNIT;
                addPoints(key, LOW_POSSIBILITY, undefinedResult.getResultMap());
            }

            result.add(undefinedResult);
        }
        return result;
    }
}
