package network.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {
    private static final Database database = Database.getInstance();

    public static void main(String[] args) {
        initDb();
    }

    public static void initDb() {
        // Шлях, який зазвичай працює в Gradle, якщо файл у src/main/resources/sql/
        String filePath = "src/main/resources/sql/init_db.sql";

        try {
            String sql = Files.readString(Path.of(filePath));
            String[] commands = sql.split(";");

            for (String command : commands) {
                String trimmed = command.trim();
                if (!trimmed.isEmpty()) {
                    database.executeUpdate(trimmed);
                    System.out.println("Executed: " + (trimmed.length() > 30 ? trimmed.substring(0, 30) + "..." : trimmed));
                }
            }
            System.out.println("✅ Database initialized successfully!");
        } catch (IOException e) {
            System.err.println("❌ Could not read file: " + e.getMessage());
            // Додамо підказку, де саме Java шукає файл:
            System.err.println("Absolute path tried: " + Path.of(filePath).toAbsolutePath());
        }
    }
}
