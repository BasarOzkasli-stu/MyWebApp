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
import basarozkasli.infrastructure.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    @Override
    public Book findById(int bookId) {
        Book book = null;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM books WHERE bookId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = mapToBook(rs);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> findByUserId(int userId) {
        List<Book> books = new ArrayList<>();
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM books WHERE userId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(mapToBook(rs));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM books";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                books.add(mapToBook(rs));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean addBook(Book book) {
        boolean added = false;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "INSERT INTO books (authorId, userId, title, year, numberOfPages, cover, about, read, rating, comments, releaseDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, book.getAuthorId());
            ps.setInt(2, book.getUserId());
            ps.setString(3, book.getTitle());
            ps.setInt(4, book.getYear());
            ps.setInt(5, book.getNumberOfPages());
            ps.setString(6, book.getCover());
            ps.setString(7, book.getAbout());
            ps.setInt(8, readStatusToInt(book.getReadStatus()));
            ps.setInt(9, book.getRating());
            ps.setString(10, book.getComments());
            if (book.getReleaseDate() != null) {
                ps.setDate(11, new java.sql.Date(book.getReleaseDate().getTime()));
            } else {
                ps.setNull(11, Types.DATE);
            }

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
    public boolean updateBook(Book book) {
        boolean updated = false;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "UPDATE books SET authorId=?, userId=?, title=?, year=?, numberOfPages=?, cover=?, about=?, read=?, rating=?, comments=?, releaseDate=? WHERE bookId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, book.getAuthorId());
            ps.setInt(2, book.getUserId());
            ps.setString(3, book.getTitle());
            ps.setInt(4, book.getYear());
            ps.setInt(5, book.getNumberOfPages());
            ps.setString(6, book.getCover());
            ps.setString(7, book.getAbout());
            ps.setInt(8, readStatusToInt(book.getReadStatus()));
            ps.setInt(9, book.getRating());
            ps.setString(10, book.getComments());
            if (book.getReleaseDate() != null) {
                ps.setDate(11, new java.sql.Date(book.getReleaseDate().getTime()));
            } else {
                ps.setNull(11, Types.DATE);
            }
            ps.setInt(12, book.getBookId());

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
    public boolean deleteBook(int bookId) {
        boolean deleted = false;
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "DELETE FROM books WHERE bookId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookId);

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

    @Override
    public List<Book> findFavoriteBooks(int userId) {
        List<Book> books = new ArrayList<>();
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM books WHERE userId = ? AND read = 1 AND rating >= 4";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(mapToBook(rs));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findUnreadBooks(int userId) {
        List<Book> books = new ArrayList<>();
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM books WHERE userId = ? AND read = 2";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(mapToBook(rs));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findWishlistBooksReleasedWithinAWeek(int userId) {
        List<Book> books = new ArrayList<>();
        try {
            Connection conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM books WHERE userId = ? AND read = 3 AND releaseDate IS NOT NULL AND releaseDate <= DATE_ADD(CURDATE(), INTERVAL 7 DAY)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(mapToBook(rs));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Helper methods
    private Book mapToBook(ResultSet rs) throws SQLException {
        int bookId = rs.getInt("bookId");
        int authorId = rs.getInt("authorId");
        int userId = rs.getInt("userId");
        String title = rs.getString("title");
        int year = rs.getInt("year");
        int numberOfPages = rs.getInt("numberOfPages");
        String cover = rs.getString("cover");
        String about = rs.getString("about");
        int read = rs.getInt("read");
        int rating = rs.getInt("rating");
        String comments = rs.getString("comments");
        Date releaseDate = rs.getDate("releaseDate");

        ReadStatus readStatus;
        if (read == 1) {
            readStatus = ReadStatus.READ;
        } else if (read == 2) {
            readStatus = ReadStatus.UNREAD;
        } else {
            readStatus = ReadStatus.WISHLIST;
        }

        return new Book(bookId, authorId, userId, title, year, numberOfPages, cover, about, readStatus, rating, comments, releaseDate);
    }

    private int readStatusToInt(ReadStatus status) {
        if (status == ReadStatus.READ) return 1;
        if (status == ReadStatus.UNREAD) return 2;
        return 3;
    }
}

