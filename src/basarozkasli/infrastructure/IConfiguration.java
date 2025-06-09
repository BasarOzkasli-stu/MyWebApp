/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.infrastructure;

/**
 *
 * @author basar
 */
public interface IConfiguration {
    String getDatabaseUrl();
    String getDatabaseUsername();
    String getDatabasePassword();
    String getImageDirectory();
    int getMaxRating();
    int getMinRating();
}
