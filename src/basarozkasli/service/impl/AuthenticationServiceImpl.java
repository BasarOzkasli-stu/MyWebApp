/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.service.impl;

/**
 *
 * @author basar
 */

import basarozkasli.domain.User;
import basarozkasli.repository.UserRepository;
import basarozkasli.service.IAuthenticationService;

public class AuthenticationServiceImpl implements IAuthenticationService {
    private final UserRepository userRepository;
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(String username, String password) {
        return userRepository.authenticate(username, password);
    }
}