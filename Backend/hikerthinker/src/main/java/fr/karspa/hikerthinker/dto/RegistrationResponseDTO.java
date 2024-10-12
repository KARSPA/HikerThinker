package fr.karspa.hikerthinker.dto;

public class RegistrationResponseDTO {

    private String username;

    private String message;

    private int status;

    private boolean ok;

    public RegistrationResponseDTO(String username, String message, int status, boolean ok) {
        this.username = username;
        this.message = message;
        this.status = status;
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
