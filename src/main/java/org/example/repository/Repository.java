package org.example.repository;

import java.util.Map;
import java.util.Set;

public interface Repository {

    String getKeyById(int id);

    Set<String> loadByKey(String key);

    Map<String, String> loadSingleWordCombinations();

    Set<String> loadKeys();

}
