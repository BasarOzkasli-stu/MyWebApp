/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.domain;

/**
 *
 * @author basar
 */
public class Author {
    private int authorId;
    private String name;
    private String surname;
    private String website;

    public Author() {}

    public Author(int authorId, String name, String surname, String website) {
        this.authorId = authorId;
        this.name = name;
        this.surname = surname;
        this.website = website;
    }

    // Getter ve setter metodları
    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    
    public String getFullName() {
        return name + " " + surname;
    }
    public void generateWebsite() {
    this.website = "website-" + authorId;
    }
}
