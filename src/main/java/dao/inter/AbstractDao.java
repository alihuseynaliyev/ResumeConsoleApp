package dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDao {
    public  Connection connection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "alinazim";
        String password = "alinazim";
        Connection connection
                = DriverManager.getConnection(
                        url, username, password);
        return connection;
    }
}
