package org.example.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.example.config.Config.OUTPUT_FILE;

public class Downloader {

    public static void downloadCsv(String csvUrl) throws IOException {

        URL url = new URL(csvUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        String destinationFilePath = OUTPUT_FILE;

        try (InputStream inputStream = httpURLConnection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(destinationFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // TODO Implement time out

            System.out.println("File downloaded successfully to: " + destinationFilePath);
        } finally {
            httpURLConnection.disconnect();
        }
    }
}
