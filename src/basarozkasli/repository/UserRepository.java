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
import java.util.List;

public interface UserRepository {
    User findById(int userId);
    User findByUsername(String username);
    List<User> findAll();
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int userId);
}
