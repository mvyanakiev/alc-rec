package org.example.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static java.nio.charset.StandardCharsets.UTF_8;
import static org.example.config.Config.CSV_SEPARATOR;
import static org.example.config.Config.INPUT_FILE;

public class ReadInputData {

    public static List<String[]> readContent() throws IOException {
        List<String[]> result = new ArrayList<>();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("input/report.csv");
        InputStreamReader streamReader = new InputStreamReader(is, UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String line = "";


//        File jsonFile = new ClassPathResource("jira/periodic-review-completed-valid-payload.json").getFile();

//        String path =  this.getClass().getClassLoader()
//                .getResource("employees.csv")
//                .getPath();

        // TODO tread sleep synced with time out for download;

        try {
            while ((line = bufferedReader.readLine()) != null) {

                String[] tokens = Arrays
                    .stream(line.split(CSV_SEPARATOR))
                    .map(String::trim)
                    .toArray(String[]::new);

                result.add(tokens);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            is.close();
            streamReader.close();
            bufferedReader.close();
        }

        return result;
    }
}
