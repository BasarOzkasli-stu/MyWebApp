/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.service;

/**
 *
 * @author basar
 */
import dto.BookDTO;
import java.util.List;

public interface LibraryService {
    boolean addBook(BookDTO book);
    boolean deleteBook(int bookId);
    boolean updateBook(BookDTO book);
    List<BookDTO> getBooks();
    List<BookDTO> getBooksByUser(int userId);
   
}