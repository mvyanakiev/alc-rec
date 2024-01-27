package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import static org.example.util.Downloader.downloadCsv;
import static org.example.util.Utils.summarisedReport;

public class Main {
    public static void main(String[] args) throws IOException, JsonProcessingException {

        String ipAddress = "192.168.1.108";
        int port = 51327;

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

        // For debug
//        System.out.println("");
//        for (OutputRecord outputRecord : outputRecordList) {
//            System.out.println(convertToCsv(outputRecord));
//            System.out.println(convertToJson(outputRecord));
//        }
    }
}
