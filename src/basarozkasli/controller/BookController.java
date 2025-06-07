/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.controller;

/**
 *
 * @author basar
 */
import basarozkasli.dto.BookDTO;
import basarozkasli.service.LibraryService;
import basarozkasli.service.LibraryServiceImpl;

import java.util.List;

public class BookController {
    private LibraryService libraryService;

    public BookController() {
        this.libraryService = new LibraryServiceImpl();
    }

    public boolean addBook(BookDTO bookDTO) {
        return libraryService.addBook(bookDTO);
    }

    public boolean updateBook(BookDTO bookDTO) {
        return libraryService.updateBook(bookDTO);
    }

    public boolean deleteBook(int bookId) {
        return libraryService.deleteBook(bookId);
    }

    public List<BookDTO> getAllBooks() {
        return libraryService.getBooks();
    }

    public List<BookDTO> getBooksByUser(int userId) {
        return libraryService.getBooksByUser(userId);
    }
}