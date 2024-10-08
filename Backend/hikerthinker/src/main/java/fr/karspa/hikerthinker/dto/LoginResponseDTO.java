package fr.karspa.hikerthinker.dto;

import fr.karspa.hikerthinker.Entity.ApplicationUser;

public class LoginResponseDTO {

    private Long userId;
    private String username;
    private String email;

    private String jwtToken;

    public LoginResponseDTO() {
        super();
    }

    public LoginResponseDTO(Long userId, String username, String email, String jwtToken) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.jwtToken = jwtToken;
    }

    public LoginResponseDTO(Object o, String s) {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
