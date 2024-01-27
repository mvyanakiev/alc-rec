package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.example.repository.MlRepository;
import org.example.repository.Repository;
import org.example.service.AlcService;
import org.example.util.Processor;
import org.example.util.ReadInputData;

import java.io.IOException;
import java.util.List;

import static org.example.util.ConvertUtils.*;
import static org.example.util.Utils.summarisedReport;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        String ipAddress = "192.168.1.108";
        int port = 51327;
        boolean jsonOutput = true;
        boolean csvOutput = false;

        String csvUrl = "http://" + ipAddress + ":" + port + "/Report.csv";

//        try {
//            downloadCsv(csvUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        List<String[]> inputContent = ReadInputData.readContent();
        List<InputRecord> inputRecordList = convertToInputRecordList(inputContent);

        Repository repository = new MlRepository();
        Processor processor= new Processor(); // TODO Да мине през интерфейс -> виж Repository
        AlcService alcService = new AlcService(processor, repository); // TODO подаваш интерфейси

        List<OutputRecord> outputRecordList = alcService.calculate(inputRecordList);

        System.out.println("Input records: " + inputRecordList.size());
        System.out.println("Found " + outputRecordList.size() + " records");

        System.out.println(summarisedReport(outputRecordList));

        for (OutputRecord outputRecord : outputRecordList) {
                log.debug(convertToCsv(outputRecord)); // TODO fix log https://mkyong.com/logging/apache-log4j-2-tutorials/
                log.debug(convertToJson(outputRecord));
        }
    }
}
