package network.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {
    private static final Database database = Database.getInstance();

    public static void main(String[] args) {
        initDb();
        populateDb();
    }

    public static void initDb() {
        String filePath = "src\\main\\resources\\sql\\init_db.sql";
        try {
            String sql = Files.readString(Path.of(filePath));
            String[] commands = sql.split(";");
            for (String command : commands) {
                String trim =  command.trim();
                if (!trim.isEmpty()) {
                    try(PreparedStatement statement = database.getConnection().prepareStatement(trim)) {
                        statement.executeUpdate();
                    }
                }
            }
            System.out.println("✅ Database initialized successfully!");
        } catch (IOException | SQLException e) {
            System.err.println("❌ Could not read file: " + e.getMessage());
            // Додамо підказку, де саме Java шукає файл:
            System.err.println("Absolute path tried: " + Path.of(filePath).toAbsolutePath());
        }
    }

    public static void populateDb() {
        String filePath = "src\\main\\resources\\sql\\populate_db.sql";
        try {
            String sql = Files.readString(Path.of(filePath));
            String[] commands = sql.split(";");
            for (String command : commands) {
                String trim =  command.trim();
                if (!trim.isEmpty()) {
                    try(PreparedStatement statement = database.getConnection().prepareStatement(trim)) {
                        statement.executeUpdate();
                    }
                }
            }
            System.out.println("✅ Database initialized successfully!");
        } catch (IOException | SQLException e) {
            System.err.println("❌ Could not read file: " + e.getMessage());
            // Додамо підказку, де саме Java шукає файл:
            System.err.println("Absolute path tried: " + Path.of(filePath).toAbsolutePath());
        }
    }
}
