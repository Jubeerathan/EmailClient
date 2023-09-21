import java.io.*;
import java.time.LocalDate;

public class Email implements Serializable{ // Class to handle data of an eEmail
    // Attributes of an email
    private String email;
    private LocalDate sentDate;
    private String content;
    private String subject;

    public Email(String email, String content, String subject) {
        this.email = email;
        this.content = content;
        this.subject = subject;
        this.sentDate = LocalDate.now();

    }
    // Getter for attributes
    public String getEmail() {
        return email;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }
}
