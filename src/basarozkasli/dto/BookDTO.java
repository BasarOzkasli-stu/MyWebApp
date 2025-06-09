/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.dto;

/**
 *
 * @author basar
 */
import basarozkasli.domain.ReadStatus;
import java.util.Date;

public class BookDTO {
    private Integer bookId;
    private String title;
    private String authorName;
    private String authorSurname;
    private int year;
    private int numberOfPages;
    private String about;
    private ReadStatus readStatus;
    private int rating;
    private String comments;
    private Date releaseDate;

    public BookDTO() {}

    public BookDTO(String title, String authorName, String authorSurname, int year, int numberOfPages, String about,
                   ReadStatus readStatus, int rating, String comments, Date releaseDate) {
        this.title = title;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.year = year;
        this.numberOfPages = numberOfPages;
        this.about = about;
        this.readStatus = readStatus;
        this.rating = rating;
        this.comments = comments;
        this.releaseDate = releaseDate;
    }

    // Getter & Setter metotları
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getAuthorSurname() { return authorSurname; }
    public void setAuthorSurname(String authorSurname) { this.authorSurname = authorSurname; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getNumberOfPages() { return numberOfPages; }
    public void setNumberOfPages(int numberOfPages) { this.numberOfPages = numberOfPages; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    public ReadStatus getReadStatus() { return readStatus; }
    public void setReadStatus(ReadStatus readStatus) { this.readStatus = readStatus; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }

}
