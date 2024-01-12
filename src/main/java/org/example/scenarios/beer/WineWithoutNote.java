package org.example.scenarios.beer;

import org.example.model.Account;
import org.example.model.UndefinedResult;
import org.example.scenarios.OtherProcesses;

import java.util.ArrayList;
import java.util.List;

import static org.example.config.Config.LOW_POSSIBILITY;
import static org.example.util.Utils.addPoints;

public class WineWithoutNote implements OtherProcesses {

    private final static double MIN_PRICE = 5.0;
    private final static double MAX_PRICE = 6.2;
    private final static double VOLUME_PER_UNIT = 1;
    private final static String KEY = "вино ";
    private final String SUBCATEGORY_A = "Алкохол";

    @Override
    public List<UndefinedResult> process(List<UndefinedResult> undefinedResultList) {
        List<UndefinedResult> result = new ArrayList<>();

        for (UndefinedResult undefinedResult : undefinedResultList) {
            double price = undefinedResult.getInputRecord().getExpense();

            if (price >= MIN_PRICE && price <= MAX_PRICE
                    && undefinedResult.getInputRecord().getNotes() == null
                    && undefinedResult.getInputRecord().getAccount() == Account.CASH
                    && undefinedResult.getInputRecord().getSubCategory().equalsIgnoreCase(SUBCATEGORY_A)
            ) {
                String key = KEY + VOLUME_PER_UNIT;
                addPoints(key, LOW_POSSIBILITY, undefinedResult.getResultMap());
            }

            result.add(undefinedResult);
        }
        return result;
    }
}
