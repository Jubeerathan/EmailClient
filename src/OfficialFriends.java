import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OfficialFriends extends OfficialRecipients implements IWishable{//Class for official friends
    Host host = Host.getInstance();
    private LocalDate dateOfBirth;
    private String greeting = "Hi,"+"\n"+ "Wish you a Happy Birthday.";


    public OfficialFriends(String name, String email, String designation, String DOB) {
        super(name, email, designation);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        dateOfBirth = LocalDate.parse(DOB,myFormatObj);
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String getGreeting() {
        return greeting;
    }
}
