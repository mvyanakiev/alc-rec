package org.example;

import org.example.repository.MlRepository;

public class Main {
    public static void main(String[] args) {


        MlRepository repository = new MlRepository();

        String keyById = repository.getKeyById(12);


        System.out.println(keyById);

    }
}
