/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.view;

/**
 *
 * @author basar
 */
import basarozkasli.domain.User;
import basarozkasli.domain.Book;
import basarozkasli.domain.Author;
import basarozkasli.controller.IMainController;
import java.util.List;
public interface IMainView {
    void showMainInterface(User user);
    void showBooks(List<Book> books);
    void showAuthors(List<Author> authors);
    void showError(String message);
    void showSuccess(String message);
    void showBookDialog(Book book);
    void setController(IMainController controller);
}
