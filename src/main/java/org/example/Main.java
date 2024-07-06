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
import java.time.LocalDate;
import java.util.*;

import static org.example.util.ConvertUtils.*;
import static org.example.util.Downloader.downloadCsv;
import static org.example.util.Utils.summarisedReport;
import static org.example.util.Utils.validateOutput;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        String ipAddress = "192.168.1.102";
        int port = 49252;
        String csvUrl = "http://" + ipAddress + ":" + port + "/Report.csv";

//        try {
//            downloadCsv(csvUrl);
//            Thread.sleep(2000);
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
        System.out.println("Found: " + outputRecordList.size() + " records.");

        LocalDate firstDate = LocalDate.parse(inputRecordList.get(0).getData());
        LocalDate lastDate = LocalDate.parse(inputRecordList.get(inputRecordList.size()-1).getData());
        System.out.println(summarisedReport(outputRecordList, firstDate, lastDate));

        validateOutput(inputRecordList, outputRecordList);
    }
}
