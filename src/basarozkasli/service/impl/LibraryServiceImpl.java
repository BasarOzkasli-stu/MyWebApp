/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.service.impl;

/**
 *
 * @author basar
 */
import basarozkasli.domain.Author;
import basarozkasli.domain.Book;
import basarozkasli.dto.BookDTO;
import basarozkasli.dto.Result;
import basarozkasli.repository.AuthorRepository;
import basarozkasli.repository.BookRepository;
import basarozkasli.service.ILibraryService;

import java.util.List;

public class LibraryServiceImpl implements ILibraryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public LibraryServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Result<Book> addBook(BookDTO bookData, int userId) {
        // 1. Yazar mevcut mu?
        Author author = authorRepository.findByNameAndSurname(bookData.getAuthorName(), bookData.getAuthorSurname());
        if (author == null) {
            author = new Author();
            author.setName(bookData.getAuthorName());
            author.setSurname(bookData.getAuthorSurname());
            author.setWebsite(""); // Geçici olarak boş bırak
            boolean created = authorRepository.save(author);
            if (!created) {
                return Result.failure("Author save failed");
            }
            // Kayıt sonrası ID atandığı için website güncellenir
            author.setWebsite("website-" + author.getAuthorId());
            authorRepository.save(author); // opsiyonel: güncellenmiş website ile tekrar kaydedebilirsin
        }

        // 2. Kitap objesini oluştur
        Book book = new Book();
        book.setUserId(userId);
        book.setAuthorId(author.getAuthorId());
        book.setTitle(bookData.getTitle());
        book.setYear(bookData.getYear());
        book.setNumberOfPages(bookData.getNumberOfPages());
        book.setAbout(bookData.getAbout());
        book.setReadStatus(bookData.getReadStatus());
        book.setRating(bookData.getRating());
        book.setComments(bookData.getComments());
        book.setReleaseDate(bookData.getReleaseDate());

        // 3. Kaydet
        boolean success = bookRepository.save(book);
        return success ? Result.success(book) : Result.failure("Book save failed");
    }

    @Override
    public Result<Boolean> deleteBook(int bookId, int userId) {
        // 1. Kitabı sil
        boolean result = bookRepository.delete(bookId, userId);

        // 2. Eğer kitap silindiyse ve yazara artık başka kitap kalmadıysa, yazarı da sil
        if (result) {
            Book deletedBook = bookRepository.findById(bookId, userId);
            if (deletedBook != null) {
                int authorId = deletedBook.getAuthorId();
                boolean hasOtherBooks = authorRepository.hasBooks(authorId, userId);
                if (!hasOtherBooks) {
                    authorRepository.delete(authorId);
                }
            }
        }

        return Result.success(result);
    }

    @Override
    public Result<Book> updateBook(BookDTO bookData, int userId) {
        // 1. Kitabı DB’den bul
        Book existingBook = bookRepository.findById(bookData.getBookId(), userId);
        if (existingBook == null) {
            return Result.failure("Book not found");
        }

        // 2. Alanları güncelle
        existingBook.setTitle(bookData.getTitle());
        existingBook.setYear(bookData.getYear());
        existingBook.setNumberOfPages(bookData.getNumberOfPages());
        existingBook.setAbout(bookData.getAbout());
        existingBook.setReadStatus(bookData.getReadStatus());
        existingBook.setRating(bookData.getRating());
        existingBook.setComments(bookData.getComments());
        existingBook.setReleaseDate(bookData.getReleaseDate());

        boolean success = bookRepository.update(existingBook);
        return success ? Result.success(existingBook) : Result.failure("Update failed");
    }

    @Override
    public Result<Book> getBookById(int bookId, int userId) {
        Book book = bookRepository.findById(bookId, userId);
        return book != null ? Result.success(book) : Result.failure("Book not found");
    }

    @Override
    public Result<List<Author>> searchAuthor(String name) {
        List<Author> authors = authorRepository.findByName(name);
        return Result.success(authors);
    }

    @Override
    public Result<List<Book>> getFavoriteBooks(int userId) {
        return Result.success(bookRepository.findFavoriteBooks(userId));
    }

    @Override
    public Result<List<Author>> getFavoriteAuthors(int userId) {
        return Result.success(authorRepository.findFavoriteAuthors(userId));
    }

    @Override
    public Result<List<Book>> getUnreadBooks(int userId) {
        return Result.success(bookRepository.findUnreadBooks(userId));
    }

    @Override
    public Result<List<Book>> getWishlistNotifications(int userId) {
        return Result.success(bookRepository.findBooksReleasingSoon(userId));
    }

    @Override
    public Result<String> getBookCover(int bookId, int userId) {
        Book book = bookRepository.findById(bookId, userId);
        if (book == null) {
            return Result.failure("Book not found");
        }
        return Result.success(book.getCoverPath());
    }
}
