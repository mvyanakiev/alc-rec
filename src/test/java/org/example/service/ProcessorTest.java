package org.example.service;

import com.github.javafaker.Faker;
import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.example.utils.TestUtils.randomAccount;
import static org.example.utils.TestUtils.randomPayee;

class ProcessorTest {

    private Faker faker = new Faker();

    private final String CATEGORY = "";
    private final String SUBCATEGORY = "";


    @Test
    void abc() {



        InputRecord ir1 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY,
                1.2,
                randomAccount(),
                randomPayee(),
                "б3"
        );

        InputRecord ir2 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY,
                7,
                randomAccount(),
                randomPayee(),
                "бира3"
        );

        InputRecord ir3 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY,
                7,
                randomAccount(),
                randomPayee(),
                "бира 3"
        );

        InputRecord ir4 = new InputRecord(
                faker.date().birthday().toString(),
                CATEGORY,
                SUBCATEGORY,
                7,
                randomAccount(),
                randomPayee(),
                "asd 3"
        );

        List<InputRecord> list = new ArrayList<>();
        list.add(ir1);
        list.add(ir2);
        list.add(ir3);
        list.add(ir4);

        Processor processor = new Processor();
        List<OutputRecord> process = processor.process(list);

        process.forEach(or -> System.out.print(or.toCsv()));
    }
}