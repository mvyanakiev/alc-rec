package org.example.service;

import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.TestUtils.randomAccount;
import static org.example.utils.TestUtils.randomPayee;

class ProcessorTest {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    LocalDate localDate = LocalDate.now();

    private final String CATEGORY = "";
    private final String SUBCATEGORY = "";


    @Test
    void abc() {

       InputRecord ir1 = new InputRecord(
                localDate.format(dtf),
                CATEGORY,
                SUBCATEGORY,
                1.2,
                randomAccount(),
                randomPayee(),
                "б3"
        );

        InputRecord ir2 = new InputRecord(
                localDate.format(dtf),
                CATEGORY,
                SUBCATEGORY,
                7,
                randomAccount(),
                randomPayee(),
                "бs3"
        );

        List<InputRecord> list = new ArrayList<>();
        list.add(ir1);
        list.add(ir2);

        Processor processor = new Processor();
        List<OutputRecord> process = processor.process(list);

        process.get(0);
    }
}