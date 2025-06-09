/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package basarozkasli.domain;

/**
 *
 * @author basar
 */
public enum ReadStatus {
    READ(1),       // Okundu
    UNREAD(2),     // Okunmadı
    WISHLIST(3);   // Okunacak (istek listesi)

    private final int value;

    ReadStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ReadStatus fromValue(int value) {
        for (ReadStatus rs : ReadStatus.values()) {
            if (rs.value == value) return rs;
        }
        throw new IllegalArgumentException("Geçersiz ReadStatus: " + value);
    }
}

