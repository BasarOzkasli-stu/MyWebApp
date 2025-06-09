/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.repository;

/**
 *
 * @author basar
 */
import basarozkasli.domain.Author;
import java.util.List;

public interface AuthorRepository {
    Author findById(int authorId);
    List<Author> findByName(String name);
    List<Author> findFavoriteAuthors(int userId);
    Author findByNameAndSurname(String name, String surname);
    boolean save(Author author);
    boolean delete(int authorId);
    boolean hasBooks(int authorId, int userId);
}
