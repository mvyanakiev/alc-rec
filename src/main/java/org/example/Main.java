package org.example;

import org.example.repository.MlRepository;
import org.example.util.ReadInputData;

import java.io.IOException;
import java.util.List;

import static org.example.util.Downloader.downloadCsv;

public class Main {
    public static void main(String[] args) throws IOException {


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
        // convert to List<InputRecord> inputRecordList =
        // call service calculate(inputRecordList)

        MlRepository repository = new MlRepository();
        String keyById = repository.getKeyById(3);
        System.out.println(keyById);

    }
}
