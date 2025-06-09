/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.view;

/**
 *
 * @author basar
 */
import basarozkasli.domain.UserType;
public interface IViewFactory {
    ILoginView createLoginView();
    IMainView createMainView(UserType userType);
    IBookDialog createBookDialog();
}