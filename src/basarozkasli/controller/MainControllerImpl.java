/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.controller;

/**
 *
 * @author basar
 */
import basarozkasli.domain.User;
import basarozkasli.domain.Book;
import basarozkasli.domain.Author;
import basarozkasli.dto.BookDTO;
import basarozkasli.dto.Result;
import basarozkasli.service.ILibraryService;
import basarozkasli.view.IMainView;
import java.util.List;
public class MainControllerImpl implements IMainController {

    private final ILibraryService libraryService;
    private IMainView view;
    private User currentUser;

    public MainControllerImpl(ILibraryService libraryService, IMainView view) {
        this.libraryService = libraryService;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void initialize(User user) {
        this.currentUser = user;
        view.showMainInterface(user);
    }

    @Override
    public void handleAddBook(BookDTO bookData) {
        Result<Book> result = libraryService.addBook(bookData, currentUser.getUserId());
        if (result.isSuccess()) {
            view.showSuccess("Book added successfully!");
        } else {
            view.showError(result.getError());
        }
    }

    @Override
    public void handleDeleteBook(int bookId) {
        Result<Boolean> result = libraryService.deleteBook(bookId, currentUser.getUserId());
        if (result.isSuccess() && Boolean.TRUE.equals(result.getData())) {
            view.showSuccess("Book deleted successfully!");
        } else {
            view.showError(result.getError() != null ? result.getError() : "Book could not be deleted.");
        }
    }

    @Override
    public void handleUpdateBook(BookDTO bookData) {
        Result<Book> result = libraryService.updateBook(bookData, currentUser.getUserId());
        if (result.isSuccess()) {
            view.showSuccess("Book updated successfully!");
        } else {
            view.showError(result.getError());
        }
    }

    @Override
    public void handleSearchAuthor(String name) {
        Result<List<Author>> result = libraryService.searchAuthor(name);
        if (result.isSuccess()) {
            view.showAuthors(result.getData());
        } else {
            view.showError(result.getError());
        }
    }

    @Override
    public void handleShowFavorites() {
        Result<List<Book>> result = libraryService.getFavoriteBooks(currentUser.getUserId());
        if (result.isSuccess()) {
            view.showBooks(result.getData());
        } else {
            view.showError(result.getError());
        }
    }

    @Override
    public void handleShowUnread() {
        Result<List<Book>> result = libraryService.getUnreadBooks(currentUser.getUserId());
        if (result.isSuccess()) {
            view.showBooks(result.getData());
        } else {
            view.showError(result.getError());
        }
    }

    @Override
    public void handleShowWishlist() {
        Result<List<Book>> result = libraryService.getWishlistNotifications(currentUser.getUserId());
        if (result.isSuccess()) {
            view.showBooks(result.getData());
        } else {
            view.showError(result.getError());
        }
    }
}
