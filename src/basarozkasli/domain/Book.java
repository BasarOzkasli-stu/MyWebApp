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
    private String coverPath; // şablon ve config ile uyumlu olması için isim güncellendi
    private String about;
    private ReadStatus readStatus;
    private int rating;       // 1-5 (0: not rated)
    private String comments;
    private Date releaseDate; // sadece WISHLIST (readStatus=3) ise dolu olur

    // Boş Constructor
    public Book() {}

    // Dolu Constructor
    public Book(int bookId, int authorId, int userId, String title, int year, int numberOfPages,
                String coverPath, String about, ReadStatus readStatus,
                int rating, String comments, Date releaseDate) {
        this.bookId = bookId;
        this.authorId = authorId;
        this.userId = userId;
        this.title = title;
        this.year = year;
        this.numberOfPages = numberOfPages;
        this.coverPath = coverPath;
        this.about = about;
        this.readStatus = readStatus;
        this.rating = rating;
        this.comments = comments;
        this.releaseDate = releaseDate;
    }

    // Getter ve setter'lar
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getNumberOfPages() { return numberOfPages; }
    public void setNumberOfPages(int numberOfPages) { this.numberOfPages = numberOfPages; }

    public String getCoverPath() { return coverPath; }
    public void setCoverPath(String coverPath) { this.coverPath = coverPath; }

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

    // EKSTRA: Şablonda geçen temel yardımcı fonksiyonlar (isteğe bağlı)
    public boolean isRead() {
        return readStatus == ReadStatus.READ;
    }

    public boolean isWishlisted() {
        return readStatus == ReadStatus.WISHLIST;
    }

    public boolean isFavorite() {
        return (readStatus == ReadStatus.READ) && (rating == 4 || rating == 5);
    }

    public boolean isReleasingSoon(Date now) {
        if (readStatus == ReadStatus.WISHLIST && releaseDate != null) {
            long diff = releaseDate.getTime() - now.getTime();
            return diff >= 0 && diff <= 7L * 24 * 60 * 60 * 1000; // 1 hafta
        }
        return false;
    }
}

