/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.service;

/**
 *
 * @author basar
 */
import basarozkasli.dto.BookDTO;
import basarozkasli.domain.Author;
import basarozkasli.domain.Book;
import basarozkasli.dto.Result;

import java.util.List;

public interface ILibraryService {
    Result<Book> addBook(BookDTO bookData, int userId);
    Result<Boolean> deleteBook(int bookId, int userId);
    Result<Book> updateBook(BookDTO bookData, int userId);
    Result<Book> getBookById(int bookId, int userId);
    Result<List<Author>> searchAuthor(String name);
    Result<List<Book>> getFavoriteBooks(int userId);
    Result<List<Author>> getFavoriteAuthors(int userId);
    Result<List<Book>> getUnreadBooks(int userId);
    Result<List<Book>> getWishlistNotifications(int userId);
    Result<String> getBookCover(int bookId, int userId);
}
