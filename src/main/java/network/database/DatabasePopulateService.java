package network.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabasePopulateService {
    private static final Database database = Database.getInstance();
    PreparedStatement insertWorker;
    PreparedStatement insertClient;
    PreparedStatement insertProject;
    PreparedStatement insertProjectWorker;

    public DatabasePopulateService() {
        try {
            insertWorker = Database.getInstance().getConnection().prepareStatement(
                    "INSERT INTO worker(NAME, BIRTHDATE, LEVEL, SALARY) VALUES (?, ?, ?, ?)"
            );
            insertClient = Database.getInstance().getConnection().prepareStatement(
                    "INSERT INTO client(NAME) VALUES (?)"
            );
            insertProject = Database.getInstance().getConnection().prepareStatement(
                    "INSERT INTO project(PROJECT_ID, START_DATE, FINISH_DATE) VALUES (?, ?, ?)"
            );
            insertProjectWorker = Database.getInstance().getConnection().prepareStatement(
                    "INSERT INTO project_worker(PROJECT_ID, WORKER_ID) VALUES (?, ?)"
            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public void setInsertWorker(String name, LocalDate birthday, String level, int salary) {
        try {
            insertWorker.setString(1, name);
            insertWorker.setString(2, birthday.toString());
            insertWorker.setString(3, level);
            insertWorker.setInt(4, salary);

            insertWorker.addBatch();
            insertWorker.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setInsertWorker(String[] names, LocalDate[] birthdays, String[] levels, int[] salarys) {
        try {
            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                LocalDate birthday = birthdays[i];
                String level = levels[i];
                int salary = salarys[i];
                
                insertWorker.setString(1, name);
                insertWorker.setString(2, birthday.toString());
                insertWorker.setString(3, level);
                insertWorker.setInt(4, salary);

                insertWorker.addBatch();
            }
            insertWorker.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setInsertClient(String name) {
        try {
            insertClient.setString(1, name);

            insertClient.addBatch();
            insertClient.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setInsertClient(String[] names) {
        try {
            for (String name : names) {
                insertClient.setString(1, name);

                insertClient.addBatch();
            }
            insertClient.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setInsertProject(int clientId, LocalDate startDate, LocalDate finishDate) {
        try {
            insertProject.setInt(1, clientId);
            insertProject.setString(2, startDate.toString());
            insertProject.setString(3, finishDate.toString());

            insertProject.addBatch();

            insertProject.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setInsertProject(int[] clientsId, LocalDate[] startDates, LocalDate[] finishDates) {
        try {
            for (int i = 0; i < clientsId.length; i++) {
                int clientId = clientsId[i];
                LocalDate startDate = startDates[i];
                LocalDate finishDate = finishDates[i];

                insertProject.setInt(1, clientId);
                insertProject.setString(2, startDate.toString());
                insertProject.setString(3, finishDate.toString());

                insertProject.addBatch();
            }
            insertProject.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setInsertProjectWorker(int projectId, int workerId) {
        try {
            insertProjectWorker.setInt(1, projectId);
            insertProjectWorker.setInt(1, workerId);

            insertProjectWorker.addBatch();

            insertProjectWorker.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setInsertProjectWorker(int[] projectsId, int[] workersId) {
        try {
            for (int i = 0; i < projectsId.length; i++) {
                int projectId = projectsId[i];
                int workerId = workersId[i];
                insertProjectWorker.setInt(1, projectId);
                insertProjectWorker.setInt(1, workerId);

                insertProjectWorker.addBatch();
            }
            insertProjectWorker.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
