/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.view.swing;

/**
 *
 * @author basar
 */


import basarozkasli.domain.UserType;
import basarozkasli.view.*;

public class SwingViewFactory implements IViewFactory {

    @Override
    public ILoginView createLoginView() {
        return new LoginFrame();
    }

    @Override
    public IMainView createMainView(UserType userType) {
        if (userType == UserType.ADMIN) {
            return new Type1MainFrame();  // Yönetici için ana ekran
        } else {
            return new Type2MainFrame();  // Normal kullanıcı için ana ekran
        }
    }

    @Override
    public IBookDialog createBookDialog() {
        return new BookDialog(null, true); // Modal dialog, parent JFrame null verilebilir
    }
}

