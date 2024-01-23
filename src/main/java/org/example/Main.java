package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.example.repository.MlRepository;
import org.example.repository.Repository;
import org.example.service.AlcService;
import org.example.util.Processor;
import org.example.util.ReadInputData;
import org.example.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.example.util.ConvertUtils.toJson;

//import static org.example.util.Downloader.downloadCsv;

public class Main {
    public static void main(String[] args) throws IOException, JsonProcessingException {

        String ipAddress = "192.168.1.108";
        int port = 50470;

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

            inputRecord.setData(inputContent.get(i)[0]);
            inputRecord.setCategory(cleanQuotes(inputContent.get(i)[1]));
            inputRecord.setSubCategory(cleanQuotes(inputContent.get(i)[2]));
            inputRecord.setExpense(Double.parseDouble(cleanQuotes(inputContent.get(i)[3]).trim()));
            inputRecord.setAccount(cleanQuotes(inputContent.get(i)[4]));
            inputRecord.setPayee(cleanQuotes(inputContent.get(i)[5]));
            inputRecord.setNotes(cleanQuotes(inputContent.get(i)[6]));

            inputRecordList.add(inputRecord);
            i++;

//            if (!"".equals(inputRecord.getNotes())) {
//                System.out.println(inputRecord.getNotes());
//            }
        }

        Repository repository = new MlRepository();
        Processor processor= new Processor(); // TODO Да мине през интерфейс -> виж Repository
        AlcService alcService = new AlcService(processor, repository); // TODO подаваш интерфейси

        List<OutputRecord> outputRecordList = alcService.calculate(inputRecordList);

        for (OutputRecord outputRecord : outputRecordList) {
            System.out.println(outputRecord.convertToCsv());
        }

//        String keyById = repository.getKeyById(3);
//        Map<String, String> singleWordCombinations = repository.loadSingleWordCombinations();
//        Set<String> strings = repository.loadKeys();
//        Set<String> byKey = repository.loadByKey("бира 2.5");
    }

    private static String cleanQuotes(String in) {
        return in.substring(1, in.length()-1);
    }
}
