/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package basarozkasli.domain;

/**
 *
 * @author basar
 */
public enum UserType {
    ADMIN(1),    // Type-1: tüm yetkiler
    REGULAR(2);  // Type-2: sınırlı yetki
    private final int value;
    UserType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static UserType fromValue(int value) {
        for (UserType ut : UserType.values()) {
            if (ut.value == value) return ut;
        }
        throw new IllegalArgumentException("Geçersiz UserType: " + value);
    }
}
