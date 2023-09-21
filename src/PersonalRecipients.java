import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonalRecipients extends Recipients implements IWishable{ //Class for personal
    Host host = Host.getInstance();
    private String greeting = "Hi,"+"\n"+"Hug and love on your birthday." ;
    private String nickName;
    private LocalDate dateOfBirth;

    public PersonalRecipients(String name, String nickName, String email, String DOB) {
        super(name, email);
        this.nickName = nickName;
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        dateOfBirth = LocalDate.parse(DOB,myFormatObj);
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getGreeting() {
        return greeting;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
