/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.dto;

/**
 *
 * @author basar
 */
public class Result<T> {
    private boolean success;
    private T data;
    private String errorMessage;

    private Result(boolean success, T data, String errorMessage) {
        this.success = success;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    // Başarılı sonuç üretici (data dolu, hata yok)
    public static <T> Result<T> success(T data) {
        return new Result<>(true, data, null);
    }

    // Başarısız sonuç üretici (hata mesajı dolu, data yok)
    public static <T> Result<T> failure(String errorMessage) {
        return new Result<>(false, null, errorMessage);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return errorMessage;
    }
}
