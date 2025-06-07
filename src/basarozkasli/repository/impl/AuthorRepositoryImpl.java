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
import basarozkasli.infrastructure.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    @Override
    public Author findById(int authorId) {
        Author author = null;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM authors WHERE authorId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, authorId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("authorId");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String website = rs.getString("website");

                author = new Author(id, name, surname, website);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public Author findByNameAndSurname(String name, String surname) {
        Author author = null;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM authors WHERE name = ? AND surname = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, surname);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("authorId");
                String authorName = rs.getString("name");
                String authorSurname = rs.getString("surname");
                String website = rs.getString("website");

                author = new Author(id, authorName, authorSurname, website);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM authors";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("authorId");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String website = rs.getString("website");

                Author author = new Author(id, name, surname, website);
                authors.add(author);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public boolean addAuthor(Author author) {
        boolean added = false;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "INSERT INTO authors (name, surname, website) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getWebsite());

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
    public boolean updateAuthor(Author author) {
        boolean updated = false;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "UPDATE authors SET name = ?, surname = ?, website = ? WHERE authorId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getWebsite());
            ps.setInt(4, author.getAuthorId());

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
    public boolean deleteAuthor(int authorId) {
        boolean deleted = false;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "DELETE FROM authors WHERE authorId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, authorId);

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
