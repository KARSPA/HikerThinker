package fr.karspa.hikerthinker.dto;

import fr.karspa.hikerthinker.Entity.ApplicationUser;

public class LoginResponseDTO {
    private ApplicationUser user;
    private String jwtToken;

    public LoginResponseDTO() {
        super();
    }

    public LoginResponseDTO(ApplicationUser user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
