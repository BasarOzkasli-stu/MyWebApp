/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.repository;

/**
 *
 * @author basar
 */
import basarozkasli.domain.User;
public interface UserRepository {
    User findById(int id);
    User findByUsername(String username);
    User authenticate(String username, String password);
    boolean save(User user);
}
