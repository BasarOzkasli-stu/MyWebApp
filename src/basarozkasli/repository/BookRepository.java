/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.repository;

/**
 *
 * @author basar
 */
import basarozkasli.domain.Book;
import java.util.List;

public interface BookRepository {
    Book findById(int bookId, int userId);
    List<Book> findByUserId(int userId);
    List<Book> findFavoriteBooks(int userId);
    List<Book> findUnreadBooks(int userId);
    List<Book> findWishlistBooks(int userId);
    List<Book> findBooksReleasingSoon(int userId);
    boolean save(Book book);
    boolean update(Book book);
    boolean delete(int bookId, int userId);
}
