package org.example.repository;

import java.sql.*;
import java.util.*;

public class MlRepository implements Repository {

    public Map<String, String> loadSingleWordCombinations() {
        String sql = "SELECT i.input, k.key FROM inputs i JOIN keys k ON k.id = i.key_id;";

        Map<String, String> resultMap = new HashMap<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    resultMap.put(resultSet.getString("input"), resultSet.getString("key"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Set<String> loadByKey(String key) {
        String sql = "SELECT i.input FROM inputs i JOIN keys k ON k.id = i.key_id WHERE k.key=?";

        Set<String> resultList = new HashSet<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, key);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    resultList.add(resultSet.getString("input"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public Set<String> loadKeys() {
        String sql = "SELECT key FROM keys;";

        Set<String> resultList = new HashSet<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    resultList.add(resultSet.getString("key"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
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
