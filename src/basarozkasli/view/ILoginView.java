/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.view;

/**
 *
 * @author basar
 */


import basarozkasli.controller.LoginController;

public interface ILoginView {
    void showLoginForm();
    void showError(String message);
    void closeView();
    void setController(LoginController controller);
    
}

