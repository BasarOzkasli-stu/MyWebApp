/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.service;

/**
 *
 * @author basar
 */
import basarozkasli.dto.BookDTO;
import basarozkasli.repository.BookRepository;
import basarozkasli.repository.impl.BookRepositoryImpl;
import java.util.List;
import java.util.stream.Collectors;
import domain.Book;

public class LibraryServiceImpl implements LibraryService {
    private BookRepository bookRepository;

    public LibraryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Eğer bağımlılık enjeksiyonu yoksa:
    public LibraryServiceImpl() {
        this.bookRepository = new BookRepositoryImpl();
    }

    @Override
    public boolean addBook(BookDTO bookDTO) {
        // DTO'yu entity'ye çevirip repository'ye ekler
        Book book = mapToBook(bookDTO);
        return bookRepository.addBook(book);
    }

    @Override
    public boolean deleteBook(int bookId) {
        return bookRepository.deleteBook(bookId);
    }

    @Override
    public boolean updateBook(BookDTO bookDTO) {
        Book book = mapToBook(bookDTO);
        return bookRepository.updateBook(book);
    }

    @Override
    public List<BookDTO> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::mapToBookDTO).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBooksByUser(int userId) {
        List<Book> books = bookRepository.findByUserId(userId);
        return books.stream().map(this::mapToBookDTO).collect(Collectors.toList());
    }

    // --- DTO ve entity dönüşüm yardımcıları ---

    private Book mapToBook(BookDTO dto) {
        
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setYear(dto.getYear());
        book.setRating(dto.getRating());
        // diğer alanlar...
        return book;
    }

    private BookDTO mapToBookDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setTitle(book.getTitle());
        dto.setYear(book.getYear());
        dto.setRating(book.getRating());
       
        return dto;
    }
}