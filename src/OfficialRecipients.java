public class OfficialRecipients extends Recipients{ //Class for official
    private String designation;

    public OfficialRecipients(String name, String email, String designation) {
        super(name, email);
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

}
