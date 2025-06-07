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
    Book findById(int bookId);
    List<Book> findByUserId(int userId);
    List<Book> findAll();
    boolean addBook(Book book);
    boolean updateBook(Book book);
    boolean deleteBook(int bookId);

    // Ekstra fonksiyonlar: 
    List<Book> findFavoriteBooks(int userId);    
    List<Book> findUnreadBooks(int userId);      
    List<Book> findWishlistBooksReleasedWithinAWeek(int userId);
}
