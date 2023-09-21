abstract class Recipients { // Class for abstract recipients
    private String name;
    private String email;
    private static int count = 0; // Counting the total recipient

    public Recipients(String name, String email) {
        this.name = name;
        this.email = email;
        Recipients.count++;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static int getCount() {
        return count;
    }
}
