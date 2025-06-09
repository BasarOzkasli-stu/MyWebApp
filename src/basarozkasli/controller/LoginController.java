/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.controller;

/**
 *
 * @author basar
 */
import basarozkasli.domain.User;
import basarozkasli.service.IAuthenticationService;
import basarozkasli.view.ILoginView;
import basarozkasli.view.IViewFactory;

public class LoginController {

    private final IAuthenticationService authService;
    private final ILoginView loginView;
    private final IViewFactory viewFactory;

    public LoginController(IAuthenticationService authService, ILoginView loginView, IViewFactory viewFactory) {
        this.authService = authService;
        this.loginView = loginView;
        this.viewFactory = viewFactory;
        this.loginView.setController(this);
    }

    public void handleLogin(String username, String password) {
        System.out.println("DEBUG: handleLogin - username: " + username);
        
        User user = authService.authenticate(username, password);
        if (user != null) {
            System.out.println("DEBUG: Giriş başarılı");
            showMainView(user);
            loginView.closeView();
        } else {
             System.out.println("DEBUG: Giriş başarısız");
            loginView.showError("Invalid username or password.");
        }
    }

    public void showMainView(User user) {
        // Kullanıcı tipine uygun ana view'i yarat ve göster
        var mainView = viewFactory.createMainView(user.getUserType());
        if (mainView != null) {
            mainView.showMainInterface(user);
        } else {
            loginView.showError("Main view could not be loaded!");
        }
    }
}


