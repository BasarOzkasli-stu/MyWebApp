/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.infrastructure;

/**
 *
 * @author basar
 */
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionManager implements IConnectionManager {
    private static MySQLConnectionManager instance;
    private final IConfiguration config;
    private boolean connectionMessageShown = false;

    private MySQLConnectionManager() {
        config = AppConfiguration.getInstance();
    }

    public static MySQLConnectionManager getInstance() {
        if (instance == null) {
            instance = new MySQLConnectionManager();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
    String url = config.getDatabaseUrl();
    String user = config.getDatabaseUsername();
    String password = config.getDatabasePassword();
    Connection conn = DriverManager.getConnection(url, user, password);

    // --- EKLEDİĞİMİZ KISIM ---
    if (!connectionMessageShown) {
        JOptionPane.showMessageDialog(null, "Veritabanına başarıyla bağlanıldı!");
        connectionMessageShown = true;
    }
    // --------------------------

    return conn;
    }

    @Override
    public void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
