/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.controller;

/**
 *
 * @author basar
 */
import basarozkasli.domain.User;
import basarozkasli.dto.BookDTO;

public interface IMainController {
    void initialize(User user);
    void handleAddBook(BookDTO bookData);
    void handleDeleteBook(int bookId);
    void handleUpdateBook(BookDTO bookData);
    void handleSearchAuthor(String name);
    void handleShowFavorites();
    void handleShowUnread();
    void handleShowWishlist();
}
