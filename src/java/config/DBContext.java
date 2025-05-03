package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	
    private static DBContext _instance = null;
    private Connection connection = null;

    public static DBContext getInstance() {
        if (_instance == null) {
            _instance = new DBContext();
        }

        return _instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public DBContext(String URL, String userName, String password) {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(URL, userName, password);
            }
        } catch (Exception e) {
            connection = null;
        }
    }

    public DBContext() {
        this("jdbc:sqlserver://localhost:1433;databaseName=FPTUsersDB;TrustServerCertificate=true;",
                "sa", "123");
    }
	
	public void setDatabaseTest() {
		 try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=FPTUsersDBTest;TrustServerCertificate=true;", "sa", "123");
            }
        } catch (Exception e) {
            connection = null;
        }
	} 
}
