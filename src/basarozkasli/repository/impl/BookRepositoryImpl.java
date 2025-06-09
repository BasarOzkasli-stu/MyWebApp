/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.repository.impl;

/**
 *
 * @author basar
 */
import basarozkasli.domain.Book;
import basarozkasli.domain.ReadStatus;
import basarozkasli.repository.BookRepository;
import basarozkasli.infrastructure.MySQLConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    @Override
    public Book findById(int bookId, int userId) {
        Book book = null;
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE bookId = ? AND userId = ?")) {
            ps.setInt(1, bookId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = mapToBook(rs);
            }
        } catch (SQLException e) {
        }
        return book;
    }

    @Override
    public List<Book> findByUserId(int userId) {
        List<Book> books = new ArrayList<>();
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE userId = ?")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(mapToBook(rs));
            }
        } catch (SQLException e) {
        }
        return books;
    }

    @Override
    public List<Book> findFavoriteBooks(int userId) {
        List<Book> books = new ArrayList<>();
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE userId = ? AND `read` = 1 AND rating >= 4")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(mapToBook(rs));
            }
        } catch (SQLException e) {
        }
        return books;
    }

    @Override
    public List<Book> findUnreadBooks(int userId) {
        List<Book> books = new ArrayList<>();
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE userId = ? AND `read` = 2")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(mapToBook(rs));
            }
        } catch (SQLException e) {
        }
        return books;
    }

    @Override
    public List<Book> findWishlistBooks(int userId) {
        List<Book> books = new ArrayList<>();
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE userId = ? AND `read` = 3")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(mapToBook(rs));
            }
        } catch (SQLException e) {
        }
        return books;
    }

    @Override
    public List<Book> findBooksReleasingSoon(int userId) {
        List<Book> books = new ArrayList<>();
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT * FROM books WHERE userId = ? AND `read` = 3 AND releaseDate IS NOT NULL AND releaseDate <= DATE_ADD(CURDATE(), INTERVAL 7 DAY)")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(mapToBook(rs));
            }
        } catch (SQLException e) {
        }
        return books;
    }

    @Override
    public boolean save(Book book) {
        String sql = "INSERT INTO books (authorId, userId, title, year, numberOfPages, cover, about, `read`, rating, comments, releaseDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, book.getAuthorId());
            ps.setInt(2, book.getUserId());
            ps.setString(3, book.getTitle());
            ps.setInt(4, book.getYear());
            ps.setInt(5, book.getNumberOfPages());
            ps.setString(6, book.getCoverPath());
            ps.setString(7, book.getAbout());
            ps.setInt(8, book.getReadStatus().getValue());
            ps.setInt(9, book.getRating());
            ps.setString(10, book.getComments());
            if (book.getReleaseDate() != null) {
                ps.setDate(11, new java.sql.Date(book.getReleaseDate().getTime()));
            } else {
                ps.setNull(11, Types.DATE);
            }
            int result = ps.executeUpdate();
            if (result > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    book.setBookId(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean update(Book book) {
        String sql = "UPDATE books SET authorId=?, userId=?, title=?, year=?, numberOfPages=?, cover=?, about=?, `read`=?, rating=?, comments=?, releaseDate=? WHERE bookId=?";
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, book.getAuthorId());
            ps.setInt(2, book.getUserId());
            ps.setString(3, book.getTitle());
            ps.setInt(4, book.getYear());
            ps.setInt(5, book.getNumberOfPages());
            ps.setString(6, book.getCoverPath());
            ps.setString(7, book.getAbout());
            ps.setInt(8, book.getReadStatus().getValue());
            ps.setInt(9, book.getRating());
            ps.setString(10, book.getComments());
            if (book.getReleaseDate() != null) {
                ps.setDate(11, new java.sql.Date(book.getReleaseDate().getTime()));
            } else {
                ps.setNull(11, Types.DATE);
            }
            ps.setInt(12, book.getBookId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean delete(int bookId, int userId) {
        String sql = "DELETE FROM books WHERE bookId = ? AND userId = ?";
        try (Connection conn = MySQLConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ps.setInt(2, userId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
        }
        return false;
    }

 
    private Book mapToBook(ResultSet rs) throws SQLException {
        int bookId = rs.getInt("bookId");
        int authorId = rs.getInt("authorId");
        int userId = rs.getInt("userId");
        String title = rs.getString("title");
        int year = rs.getInt("year");
        int numberOfPages = rs.getInt("numberOfPages");
        String coverPath = rs.getString("cover");
        String about = rs.getString("about");
        int read = rs.getInt("read");
        int rating = rs.getInt("rating");
        String comments = rs.getString("comments");
        Date releaseDate = rs.getDate("releaseDate");
        return new Book(bookId, authorId, userId, title, year, numberOfPages, coverPath, about, ReadStatus.fromValue(read), rating, comments, releaseDate);
    }
}


