package org.example.util;

import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.nio.file.Path;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader {

    public static void downloadCsv(String csvUrl) throws IOException, InterruptedException {

        URL url = new URL(csvUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        String destinationFilePath = "report.csv";

        try (InputStream inputStream = httpURLConnection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(destinationFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File downloaded successfully to: " + destinationFilePath);
        } finally {
            httpURLConnection.disconnect();
        }


//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(csvUrl))
//                .build();
//
//        HttpResponse<Path> response = httpClient.send(request, HttpResponse.BodyHandlers.ofFile(Path.of("output")));
//
//        if (response.statusCode() == 200) {
//            System.out.println("CSV file downloaded successfully to: " + response.body());
//        } else {
//            System.err.println("Failed to download CSV file. HTTP Status Code: " + response.statusCode());
//        }
    }
}
