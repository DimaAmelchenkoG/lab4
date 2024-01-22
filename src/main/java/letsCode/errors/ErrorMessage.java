package letsCode.errors;

//import java.util.Date;

public class ErrorMessage {
    private int status;
    private String message;
   // private Date timestamp;

    public ErrorMessage(int status, String message){
        this.status = status;
        this.message = message;
        //this.timestamp = new Date();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
