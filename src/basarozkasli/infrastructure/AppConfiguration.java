/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.infrastructure;

/**
 *
 * @author basar
 */
public class AppConfiguration implements Configuration {
    @Override
    public String getDatabaseUrl() {
        return "jdbc:mysql://localhost:3306/mylibrarydb"; // kendi veritabanı adını yaz
    }

    @Override
    public String getDatabaseUser() {
        return "root"; // kendi kullanıcı adını yaz
    }

    @Override
    public String getDatabasePassword() {
        return "sifren"; // kendi şifreni yaz
    }
}