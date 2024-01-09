package org.example.service;

import jdk.internal.util.xml.impl.Pair;
import org.example.model.InputRecord;
import org.example.model.OutputRecord;
import org.example.scenarios.Scenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Processor {

    public void make (List<InputRecord> inputRecordList) {

        List<Scenario> models = new ArrayList<>();

        for (InputRecord inputRecord : inputRecordList) {

            Map<String, Integer> scores = new HashMap<>();

            for (Scenario model : models) {
                model.process(inputRecord, scores);
            }



        }






    }




}
