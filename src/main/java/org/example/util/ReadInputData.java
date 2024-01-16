package org.example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static java.nio.charset.StandardCharsets.UTF_8;
import static org.example.config.Config.DELIMITER;
import static org.example.config.Config.INPUT_FILE;

public class ReadInputData {

    public static List<String[]> readContent() throws IOException {
        List<String[]> result = new ArrayList<>();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(INPUT_FILE);
        InputStreamReader streamReader = new InputStreamReader(is, UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String line = "";

        try {
            while ((line = bufferedReader.readLine()) != null) {

                // s -> s.replace("\"", "")

                String[] split = line.split(DELIMITER);


                // TODO remove double double quotes from begin and end of each field and fix price to double

//                String[] fixed = new String[split.length];
//                int i = 0;
//                for (String s : split) {
//
//                    s.trim();
//
//                    s = s.substring(2, s.length() - 2);
//
////                    s.replace("\"", "");
//                    fixed[i] = s;
//                    i++;
//
//                }
//                result.add(fixed);



// original
                String[] tokens = Arrays
                    .stream(line.split(DELIMITER))
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

