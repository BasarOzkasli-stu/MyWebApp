/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.service;

/**
 *
 * @author basar
 */
import basarozkasli.domain.User;

public interface IAuthenticationService {
    User authenticate(String username, String password);
}