/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author basar
 */
package basarozkasli.repository.impl;
import basarozkasli.domain.User;
import basarozkasli.domain.UserType;
import basarozkasli.repository.UserRepository;
import basarozkasli.infrastructure.MySQLConnectionManager;

import java.sql.*;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findById(int userId) {
        User user = null;
        String sql = "SELECT * FROM userinfo WHERE userId = ?";
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = mapToUser(rs);
                }
            }
        } catch (SQLException e) {
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM userinfo WHERE username = ?";
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = mapToUser(rs);
                }
            }
        } catch (SQLException e) {
        }
        return user;
    }

    @Override
    public User authenticate(String username, String password) {
        System.out.println("DEBUG: authenticate çağrıldı, username=" + username + ", password=" + password);
        User user = null;
        String sql = "SELECT * FROM userinfo WHERE username = ? AND password = ?";
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("DEBUG:Kullanıcı bulundu");
                    user = mapToUser(rs);}
                else{
                    System.out.println("DEBUG : Kullanıcı bulunamadı");
                    }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean save(User user) {
        String sql = "INSERT INTO userinfo (username, password, userType) VALUES (?, ?, ?)";
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUserType().getValue());
            int result = ps.executeUpdate();
            if (result > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) user.setUserId(keys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }
    private User mapToUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("userId");
        String username = rs.getString("username");
        String password = rs.getString("password");
        int type = rs.getInt("userType");
        UserType userType = UserType.fromValue(type);
        return new User(id, username, password, userType);
    }
}
