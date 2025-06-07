/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.domain;

/**
 *
 * @author basar
 */

import java.util.Date;

public class Book {
    private int bookId;
    private int authorId;
    private int userId;
    private String title;
    private int year;
    private int numberOfPages;
    private String cover;
    private String about;
    private ReadStatus readStatus;
    private int rating;         // 1-5 
    private String comments;
    private Date releaseDate;   //  WISHLIST 

    public Book() {}

    public Book(int bookId, int authorId, int userId, String title, int year, int numberOfPages,
                String cover, String about, ReadStatus readStatus, int rating, String comments, Date releaseDate) {
        this.bookId = bookId;
        this.authorId = authorId;
        this.userId = userId;
        this.title = title;
        this.year = year;
        this.numberOfPages = numberOfPages;
        this.cover = cover;
        this.about = about;
        this.readStatus = readStatus;
        this.rating = rating;
        this.comments = comments;
        this.releaseDate = releaseDate;
    }
    //add getters and setters
}
