package org.example.service;

import com.github.javafaker.Faker;
import org.example.model.Account;
import org.example.model.InputRecord;
import org.example.model.UndefinedResult;
import org.example.repository.MlRepository;
import org.example.util.Processor;
import org.example.util.Utils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.example.utils.TestUtils.randomAccount;
import static org.example.utils.TestUtils.randomPayee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessorTest {

    private final Faker faker = new Faker();

    private final String CATEGORY = "Забавление";
    private final String SUBCATEGORY_A = "Алкохол";

    // TODO Mock it
    MlRepository repository = new MlRepository();

    Processor processor = new Processor();


    @Test
    void oneWord() {
        List<UndefinedResult> undefinedList = Utils.createUndefinedList(getInputList());
        Map<String, String> singleWordCombinations = repository.loadSingleWordCombinations();

        List<UndefinedResult> results = processor.processWithSingleWords(undefinedList, singleWordCombinations);
        assertEquals(undefinedList.size(), results.size());
        assertEquals(1, results.get(0).getResultMap().size());
        assertEquals(0, results.get(3).getResultMap().size());

        results.stream().forEach(r -> System.out.println(Utils.convert(r).toCsv()));
    }

    @Test
    void moreWords() {
        String key = "бира 3";
        List<UndefinedResult> undefinedList = Utils.createUndefinedList(getInputList());
        Set<String> combinations = repository.loadByKey(key);

        List<UndefinedResult> results = processor.processWithManyWords(key, combinations, undefinedList);
        assertEquals(undefinedList.size(), results.size());
        assertEquals(1, results.get(0).getResultMap().size());
        assertEquals(0, results.get(3).getResultMap().size());
        assertEquals(1, results.get(6).getResultMap().size());

        results.stream().forEach(r -> System.out.println(Utils.convert(r).toCsv()));
    }

    @Test
    void mathTest() {
        List<UndefinedResult> undefinedList = Utils.createUndefinedList(getInputMathList());
        List<UndefinedResult> results = processor.mathProcess(undefinedList);

        assertEquals(undefinedList.size(), results.size());

        results.stream().forEach(r -> System.out.println(Utils.convert(r).toCsv()));
        assertEquals(1, results.get(0).getResultMap().size());
        assertEquals(1, results.get(1).getResultMap().size());
        assertEquals(0, results.get(2).getResultMap().size());
        assertTrue(results.get(1).getResultMap().containsKey("бира 2.5"));
    }

    @Test
    void processByPriceTest() {
        List<UndefinedResult> undefinedList = Utils.createUndefinedList(getInputMathList());

        List<Double> prices = Arrays.asList(
                1.6
                , 1.4
                , 1.2
        );
        List<UndefinedResult> results = processor.processByPrice(prices, "бира", 0.5, undefinedList);

        assertEquals(undefinedList.size(), results.size());

        results.stream().forEach(r -> System.out.println(Utils.convert(r).toCsv()));
//        assertEquals(0, results.get(0).getResultMap().size());
//        assertEquals(1, results.get(1).getResultMap().size());
//        assertEquals(0, results.get(2).getResultMap().size());
//        assertTrue(results.get(1).getResultMap().containsKey("бира 2.5"));
    }

    private List<InputRecord> getInputList() {
        InputRecord ir1 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                1.2,
                randomAccount(),
                randomPayee(),
                "б3"
        );

        InputRecord ir2 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                7,
                randomAccount(),
                randomPayee(),
                "бира3"
        );

        InputRecord ir3 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                7,
                randomAccount(),
                randomPayee(),
                "бира 3"
        );

        InputRecord ir4 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                7,
                randomAccount(),
                randomPayee(),
                "asd 3"
        );

        InputRecord ir5 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                7,
                randomAccount(),
                randomPayee(),
                null
        );

        InputRecord ir6 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                7,
                randomAccount(),
                randomPayee(),
                ""
        );

        InputRecord ir7 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                8.2,
                randomAccount(),
                randomPayee(),
                "б3 zagorka"
        );

        InputRecord ir8 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                7,
                randomAccount(),
                randomPayee(),
                "asd 3 11 w"
        );

        InputRecord math1 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                8.4,
                Account.CASH.toString(),
                randomPayee(),
               "null"
        );

        InputRecord math2 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                7,
                Account.CASH.toString(),
                null,
                "null"
        );



        List<InputRecord> list = new ArrayList<>();
        list.add(ir1);
        list.add(ir2);
        list.add(ir3);
        list.add(ir4);
        list.add(ir5);
        list.add(ir6);
        list.add(ir7);
        list.add(ir8);
        list.add(math1);
        list.add(math2);

        return list;
    }

    private List<InputRecord> getInputMathList() {

        InputRecord math1 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                8.4,
                Account.CASH.toString(),
                randomPayee(),
                null
        );

        InputRecord math2 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                7,
                Account.CASH.toString(),
                null,
                null
        );

        InputRecord math3 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                3.14,
                Account.CASH.toString(),
                null,
                null
        );

        InputRecord math4 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                1.6,
                Account.CASH.toString(),
                null,
                null
        );

        InputRecord math5 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                2.4,
                Account.CASH.toString(),
                null,
                null
        );

        InputRecord math6 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY_A,
                5.6,
                Account.CASH.toString(),
                null,
                null
        );


        List<InputRecord> list = new ArrayList<>();
        list.add(math1);
        list.add(math2);
        list.add(math3);
        list.add(math4);
        list.add(math5);
        list.add(math6);

        return list;
    }
}