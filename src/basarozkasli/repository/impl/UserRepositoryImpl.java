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
import basarozkasli.infrastructure.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findById(int userId) {
        User user = null;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM userinfo WHERE userId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("userId");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int type = rs.getInt("userType");

                UserType userType;
                if (type == 1) {
                    userType = UserType.ADMIN;
                } else {
                    userType = UserType.REGULAR;
                }

                user = new User(id, username, password, userType);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM userinfo WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("userId");
                String uname = rs.getString("username");
                String password = rs.getString("password");
                int type = rs.getInt("userType");

                UserType userType;
                if (type == 1) {
                    userType = UserType.ADMIN;
                } else {
                    userType = UserType.REGULAR;
                }

                user = new User(id, uname, password, userType);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM userinfo";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("userId");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int type = rs.getInt("userType");

                UserType userType;
                if (type == 1) {
                    userType = UserType.ADMIN;
                } else {
                    userType = UserType.REGULAR;
                }

                User user = new User(id, username, password, userType);
                users.add(user);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean addUser(User user) {
        boolean added = false;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "INSERT INTO userinfo (username, password, userType) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUserType() == UserType.ADMIN ? 1 : 2);

            int result = ps.executeUpdate();
            if (result > 0) {
                added = true;
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public boolean updateUser(User user) {
        boolean updated = false;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "UPDATE userinfo SET username = ?, password = ?, userType = ? WHERE userId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUserType() == UserType.ADMIN ? 1 : 2);
            ps.setInt(4, user.getUserId());

            int result = ps.executeUpdate();
            if (result > 0) {
                updated = true;
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean deleteUser(int userId) {
        boolean deleted = false;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "DELETE FROM userinfo WHERE userId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            int result = ps.executeUpdate();
            if (result > 0) {
                deleted = true;
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
}

