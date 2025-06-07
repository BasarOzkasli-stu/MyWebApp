/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.controller;

/**
 *
 * @author basar
 */
import basarozkasli.domain.Author;
import basarozkasli.repository.AuthorRepository;
import basarozkasli.repository.impl.AuthorRepositoryImpl;

import java.util.List;

public class AuthorController {
    private AuthorRepository authorRepository;

    public AuthorController() {
        this.authorRepository = new AuthorRepositoryImpl();
    }

    public Author findAuthorById(int authorId) {
        return authorRepository.findById(authorId);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

   
}