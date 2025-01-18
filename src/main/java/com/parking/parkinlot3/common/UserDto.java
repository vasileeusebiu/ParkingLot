package com.parking.parkinlot3.common;

public class UserDto {

    private Long id;  // Adaugă câmpul id
    private String username;
    private String email;

    // Constructor
    public UserDto(Long id, String username, String email) {
        this.id = id;  // Inițializează id
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}