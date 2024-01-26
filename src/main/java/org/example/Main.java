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
import java.util.ArrayList;
import java.util.List;

import static org.example.util.Utils.summarisedReport;

import static org.example.util.Downloader.downloadCsv;

public class Main {
    public static void main(String[] args) throws IOException, JsonProcessingException {

        String ipAddress = "192.168.1.108";
        int port = 51236;

        String csvUrl = "http://" + ipAddress + ":" + port + "/Report.csv";

//        try {
//            downloadCsv(csvUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        List<String[]> inputContent = ReadInputData.readContent();
        List<InputRecord> inputRecordList = new ArrayList<>();

        int i = 1;
        while (!"".equals(inputContent.get(i)[0])) {
            InputRecord inputRecord = new InputRecord();

            inputRecord.setId(i);
            inputRecord.setData(inputContent.get(i)[0]);
            inputRecord.setCategory(cleanQuotes(inputContent.get(i)[1]));
            inputRecord.setSubCategory(cleanQuotes(inputContent.get(i)[2]));
            inputRecord.setExpense(Double.parseDouble(cleanQuotes(inputContent.get(i)[3]).trim()));
            inputRecord.setAccount(cleanQuotes(inputContent.get(i)[4]));
            inputRecord.setPayee(cleanQuotes(inputContent.get(i)[5]));
            inputRecord.setNotes(cleanQuotes(inputContent.get(i)[6]));

            inputRecordList.add(inputRecord);
            i++;
        }

        Repository repository = new MlRepository();
        Processor processor= new Processor(); // TODO Да мине през интерфейс -> виж Repository
        AlcService alcService = new AlcService(processor, repository); // TODO подаваш интерфейси

        List<OutputRecord> outputRecordList = alcService.calculate(inputRecordList);

        System.out.println("Input records: " + inputRecordList.size());
        System.out.println("Found " + outputRecordList.size() + " records");

        System.out.println(summarisedReport(outputRecordList));

//        System.out.println("");
//        for (OutputRecord outputRecord : outputRecordList) {
//            System.out.println(outputRecord.convertToCsv());
//        }
    }

    private static String cleanQuotes(String in) {
        return in.substring(1, in.length()-1);
    }
}
