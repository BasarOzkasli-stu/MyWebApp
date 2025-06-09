/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package basarozkasli.view;

/**
 *
 * @author basar
 */


import basarozkasli.dto.BookDTO;

public interface IBookDialog {
    void showDialog();
    void setBookData(BookDTO book); 
    BookDTO getBookData(); 
    void closeDialog();
    void setController(Object controller); 
}
