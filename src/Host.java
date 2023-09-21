public class Host { //Singleton to restrict usage.
    private String username;
    private String password;
    private String name;
    static Host instance;

    public static synchronized Host getInstance() { // synchronized key word for Thread safe Singleton otherwise, lazy initialization
        if (instance==null){
            instance=new Host();
        }
        return instance;
    }
    private Host(){}; // Making constructor private
    //Setter to change the Attributes
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //Getter to get the values of attributes
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}

