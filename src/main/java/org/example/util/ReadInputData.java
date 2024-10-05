package org.example.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.config.Config.CSV_SEPARATOR;

public class ReadInputData {

    public static List<String[]> readContent(String filePath) throws IOException {

        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            throw new IOException("Файлът не е намерен на пътя: " + filePath);
        }

        List<String[]> result = new ArrayList<>();
        List<String> lines = Files.readAllLines(path);

        for (String line : lines) {

            if ("".equals(line)) {
                break;
            }

            String[] tokens = Arrays
                    .stream(line.split(CSV_SEPARATOR))
                    .map(String::trim)
                    .toArray(String[]::new);

            if (tokens.length != 8) {
                tokens[6] = tokens[6] + "." + tokens[7];
                tokens[7] = tokens[8];
            }

            result.add(tokens);
        }

        try {
            Files.delete(path);
        } catch (IOException e) {
            System.out.println("Неуспешно изтриване на файла: " + e.getMessage());
        }

        return result;
    }
}
