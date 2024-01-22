package org.example.repository;

import java.sql.*;
import java.util.*;

public class MlRepository implements Repository {

    public Map<String, String> loadSingleWordCombinations() {

        Map<String, String> result = new HashMap<>();
        result.put("б3", "бира 3");
        result.put("b3", "бира 3");
        result.put("бира3", "бира 3");
        result.put("бира 3", "бира 3");

        result.put("уиски 0.7", "уиски 0.7");
        result.put("w0.7", "уиски 0.7");

        return result;
    }

    @Override
    public Set<String> loadByKey(String key) {

        Set<String> result = new HashSet<>();
        result.add("б3");
        result.add("b3");
        result.add("бира3");
        result.add("бира 3");

        return result;
    }

    public Set<String> loadKeys() {
        Set<String> result = new HashSet<>();
        result.add("бира 3");
        result.add("уиски 0.7");

        return result;
    }

    @Override
    public String getKeyById(int id) {
        String sql = "SELECT key FROM keys WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("key");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
