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
    Author findByNameAndSurname(String name, String surname);
    List<Author> findAll();
    boolean addAuthor(Author author);
    boolean updateAuthor(Author author);
    boolean deleteAuthor(int authorId);
}