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

public class BookDTO {
    private String title;
    private String authorName;      // Yazarın tam adı (adı + soyadı)
    private int year;
    private ReadStatus readStatus;
    private int rating;

    public BookDTO() {
    }

    public BookDTO(String title, String authorName, int year, ReadStatus readStatus, int rating) {
        this.title = title;
        this.authorName = authorName;
        this.year = year;
        this.readStatus = readStatus;
        this.rating = rating;
    }

    // Getter ve setter'lar
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ReadStatus getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(ReadStatus readStatus) {
        this.readStatus = readStatus;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}