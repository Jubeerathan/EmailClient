import java.time.LocalDate;

public interface IWishable { // Interface to identify which objects to send birthday wishes.
    public String getName();
    public String getEmail();
    public String getGreeting();
    public LocalDate getDateOfBirth();

}
