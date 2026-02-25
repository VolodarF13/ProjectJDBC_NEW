package network.database;

import network.sql.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private static final Database INSTANCE = Database.getInstance();
    private static final String NAME = "NAME";

    public List<MaxProjectCountClient> findMaxProjectClient() {
        String filePath = "src\\main\\resources\\sql\\find_max_projects_client.sql";
        List<MaxProjectCountClient> maxProjectCountClientArrayList = new ArrayList<>();

        try (Connection connection = INSTANCE.getConnection()) {
            String sql = Files.readString(Paths.get(filePath));
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString(NAME);
                    int projectCount = resultSet.getInt("PROJECT_COUNT");
                    maxProjectCountClientArrayList.add(new MaxProjectCountClient(name, projectCount));
                }
            }
        } catch (SQLException | IOException e) {
            System.err.println(e.getMessage());
        }
        return maxProjectCountClientArrayList;
    }

    public List<LongestProject> findLongestProject() {
        String filePath = "src\\main\\resources\\sql\\find_longest_project.sql";
        List<LongestProject> longestProjectList = new ArrayList<>();

        try (Connection connection = INSTANCE.getConnection()) {
            String sql = Files.readString(Paths.get(filePath));
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("CLIENT_ID");
                    int times = resultSet.getInt("TIMES");
                    longestProjectList.add(new LongestProject(name, times));
                }
            }
        } catch (SQLException | IOException e) {
            System.err.println(e.getMessage());
        }
        return longestProjectList;
    }

    public List<MaxWorkerCountSalary> findMaxWorkerSalary() {
        String filePath = "src\\main\\resources\\sql\\find_max_salary_worker.sql";
        List<MaxWorkerCountSalary> salaries = new ArrayList<>();

        try (Connection connection = INSTANCE.getConnection()) {
            String sql = Files.readString(Paths.get(filePath));
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString(NAME);
                    int salary = resultSet.getInt("SALARY");
                    salaries.add(new MaxWorkerCountSalary(name, salary));
                }
            }
        } catch (SQLException | IOException e) {
            System.err.println(e.getMessage());
        }
        return salaries;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorker() {
        String filePath = "src\\main\\resources\\sql\\find_youngest_eldest_workers.sql";
        List<YoungestEldestWorker> youngestEldestWorkers = new ArrayList<>();

        try (Connection connection = INSTANCE.getConnection()) {
            String sql = Files.readString(Paths.get(filePath));
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String age = resultSet.getString("TYPE");
                    String name = resultSet.getString(NAME);
                    String birthday = resultSet.getString("BIRTHDAY");
                    youngestEldestWorkers.add(new YoungestEldestWorker(age, name, birthday));
                }
            }
        } catch (SQLException | IOException e) {
            System.err.println(e.getMessage());
        }
        return youngestEldestWorkers;
    }

    public List<PriceOfEachProject> findProjectPrices() {
        String filePath = "src\\main\\resources\\sql\\print_project_prices.sql";
        List<PriceOfEachProject> priceOfEachProjects = new ArrayList<>();

        try (Connection connection = INSTANCE.getConnection()) {
            String sql = Files.readString(Paths.get(filePath));
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("PROJECT_ID");
                    int prices = resultSet.getInt("PRICE");
                    priceOfEachProjects.add(new PriceOfEachProject(name, prices));
                }
            }
        } catch (SQLException | IOException e) {
            System.err.println(e.getMessage());
        }
        return priceOfEachProjects;
    }
}
