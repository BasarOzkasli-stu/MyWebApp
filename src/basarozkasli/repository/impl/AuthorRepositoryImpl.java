/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.repository.impl;

/**
 *
 * @author basar
 */
import basarozkasli.domain.Author;
import basarozkasli.repository.AuthorRepository;
import basarozkasli.infrastructure.MySQLConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    @Override
    public Author findById(int authorId) {
        Author author = null;
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM authors WHERE authorId = ?")) {
            ps.setInt(1, authorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                author = new Author(
                        rs.getInt("authorId"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("website")
                );
            }
        } catch (SQLException e) {
        }
        return author;
    }

    @Override
    public List<Author> findByName(String name) {
        List<Author> authors = new ArrayList<>();
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM authors WHERE name = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                authors.add(new Author(
                        rs.getInt("authorId"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("website")
                ));
            }
        } catch (SQLException e) {
        }
        return authors;
    }

    @Override
    public Author findByNameAndSurname(String name, String surname) {
        Author author = null;
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM authors WHERE name = ? AND surname = ?")) {
            ps.setString(1, name);
            ps.setString(2, surname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                author = new Author(
                        rs.getInt("authorId"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("website")
                );
            }
        } catch (SQLException e) {
        }
        return author;
    }

    @Override
    public List<Author> findFavoriteAuthors(int userId) {
        List<Author> authors = new ArrayList<>();
        String sql =
                "SELECT a.authorId, a.name, a.surname, a.website " +
                "FROM authors a " +
                "JOIN books b ON a.authorId = b.authorId " +
                "WHERE b.userId = ? " +
                "GROUP BY a.authorId, a.name, a.surname, a.website " +
                "HAVING COUNT(b.bookId) >= 3";
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                authors.add(new Author(
                        rs.getInt("authorId"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("website")
                ));
            }
        } catch (SQLException e) {
        }
        return authors;
    }

    @Override
    public boolean save(Author author) {
        String sql = "INSERT INTO authors (name, surname, website) VALUES (?, ?, ?)";
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getWebsite());
            int result = ps.executeUpdate();
            if (result > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    author.setAuthorId(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean delete(int authorId) {
        String sql = "DELETE FROM authors WHERE authorId = ?";
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, authorId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
           
        }
        return false;
    }

    @Override
    public boolean hasBooks(int authorId, int userId) {
        String sql = "SELECT COUNT(*) AS count FROM books WHERE authorId = ? AND userId = ?";
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, authorId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException e) {
          
        }
        return false;
    }
}