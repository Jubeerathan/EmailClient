// 200277G

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Email_Client {

    public static void main(String[] args) {

        Host host = Host.getInstance();
        //----------------------------------------
            //NEED TO CHANGE
        host.setName("Jubeerathan T.");
        host.setUsername("thorinjava@gmail.com");
        host.setPassword("fwmablqolmelttuh");
        //----------------------------------------
//        host.setName("Jubeerathan T."); // Enter your name with initials here.
//        host.setUsername("sb@gmail.com"); // Enter your username of the mail id here.
//        host.setPassword("password"); // Enter your password of the mail id here.
        //-------------------------------------
        ArrayList<IWishable> wishList = new ArrayList<>(); // Array which keeps the track of birthday wishable recipients
        ArrayList<Email> sentEmails = new ArrayList<>();// Array which keeps the track of sent emails
        //De-Serializing email objects into ArrayList<Email>.
        try{
            FileInputStream fileInputStream = new FileInputStream("Serialized.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object email = objectInputStream.readObject();
            while(true){
                sentEmails.add((Email) email);
                email = objectInputStream.readObject();
                System.out.println("*****");
                break;
            }
//            Object email = objectInputStream.readObject();
//            sentEmails.add((Email) email);
            System.out.println(sentEmails.size());
            objectInputStream.close();
            fileInputStream.close();
        }catch (IOException expt){
            //expt.printStackTrace();
        }catch (ClassNotFoundException classNotFoundException){
            //classNotFoundException.printStackTrace();
        }

        // Read and create recipient objects from file "clientList.txt"
        try{
            FileReader read = new FileReader("clientList.txt");
            BufferedReader bread = new BufferedReader(read);
            String data;

                while (true) {
                    data = bread.readLine();
                    if(data == null) break;
                    String[] partsOfRecipients = data.split(":");
                    String[] dataOfRecipients = partsOfRecipients[1].split(",");

                    switch (partsOfRecipients[0].toLowerCase()) {         // code to create objects for each recipient in clientList.txt
                        case "official": // Create official recipient object
                            OfficialRecipients officialRecipients = new OfficialRecipients(dataOfRecipients[0], dataOfRecipients[1], dataOfRecipients[2]);
                            break;
                        case "office_friend": // Create official friend object
                            OfficialFriends officialFriends = new OfficialFriends(dataOfRecipients[0], dataOfRecipients[1], dataOfRecipients[2], dataOfRecipients[3]);
                            wishList.add(officialFriends);
                            break;
                        case "personal": // Create personal recipient object
                            PersonalRecipients personalRecipients = new PersonalRecipients(dataOfRecipients[0], dataOfRecipients[1], dataOfRecipients[2], dataOfRecipients[3]);
                            wishList.add(personalRecipients);
                            break;
                    }
                }
                bread.close(); // Closing buffer reader
                read.close(); // Closing reader
        }catch (IOException ex){
            //ex.printStackTrace();
        }
        LocalDate today = LocalDate.now(); // Getting the date of today
        for (int i = 0; i < wishList.size(); i++){
            if(wishList.get(i).getDateOfBirth().getMonth() == today.getMonth() && wishList.get(i).getDateOfBirth().getDayOfMonth() == today.getDayOfMonth()){
                Email email = new Email(wishList.get(i).getEmail(),wishList.get(i).getGreeting(),"HBD");
                //Sending Email to recipient
                SendEmailTLS.sendEmail(email); // Sending email to recipients who have birthday today.
                sentEmails.add(email);// Adding the all sent email to recipients who have birthday today.
                System.out.println(sentEmails);
                //Serializing email objects into Serialized.ser
                try{
                    FileOutputStream fileOutputStream1 = new FileOutputStream("Serialized.ser",true);
                    ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
                    objectOutputStream1.writeObject(email);
                    objectOutputStream1.close();
                    fileOutputStream1.close();
                }catch (IOException excep1) {
                    //excep1.printStackTrace();
                }
            }
        }
        Scanner scanner = new Scanner(System.in); //Getting input from the user
        System.out.println("Enter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application");

        int option = scanner.nextInt();

        switch(option){ // What to choose 1, 2, 3, 4, 5
            case 1: //Adding a new recipient
                scanner.nextLine();
                System.out.println("input format - \nOfficial: nimal,nimal@gmail.com,ceo" +
                        "\nOffice_friend: kamal,kamal@gmail.com,clerk,2000/12/12" +
                        "\nPersonal: sunil,<nick-name>,sunil@gmail.com,2000/10/10");
                //input format - Official: nimal,nimal@gmail.com,ceo
                //Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12
                //Personal: sunil,<nick-name>,sunil@gmail.com,2000/10/10
                String recipi = scanner.nextLine().trim(); // Use a single input to get all the details of a recipient

                try{ // store details in clientList.txt file
                FileWriter writer = new FileWriter("clientList.txt",true);
                BufferedWriter bwriter = new BufferedWriter(writer);
                bwriter.write( "\n" +recipi );
                bwriter.close();
                writer.close();
                }catch (IOException e){
                    //e.printStackTrace();
                }

                String[] partsOfrecipi = recipi.split(":"); // Get which kind of recipient
                String[] dataOfRecipi = partsOfrecipi[1].split(","); // get data of each recipient

                switch(partsOfrecipi[0].toLowerCase()){   // code to add a new recipient
                    case "official": // If official
                        OfficialRecipients officialRecipients = new OfficialRecipients(dataOfRecipi[0],dataOfRecipi[1],dataOfRecipi[2]);
                        break;
                    case "office_friend": // If official friend
                        OfficialFriends officialFriends = new OfficialFriends(dataOfRecipi[0],dataOfRecipi[1],dataOfRecipi[2],dataOfRecipi[3]);
                        wishList.add(officialFriends);
                        break;
                    case "personal": // If personal
                        PersonalRecipients personalRecipients = new PersonalRecipients(dataOfRecipi[0],dataOfRecipi[1],dataOfRecipi[2],dataOfRecipi[3]);
                        wishList.add(personalRecipients);
                        break;
                }
                System.out.println("Added Successfully");
                // input format - Official: nimal,nimal@gmail.com,ceo
                break;
            case 2: //  Sending an email
                scanner.nextLine();
                System.out.println("input format - email, subject, content");// input format - email, subject, content
                String inputCase2 = scanner.nextLine().trim();
                String[] dataOfInputCase2 = inputCase2.split(",");
                Email email = new Email(dataOfInputCase2[0],dataOfInputCase2[2],dataOfInputCase2[1]);
                //Sending Email to recipient
                SendEmailTLS.sendEmail(email);  // code to send an email
                sentEmails.add(email);
                System.out.println("Email has been sent sucessfully");

                //Serializing email objects into Serialized.ser
                try{
                FileOutputStream fileOutputStream2 = new FileOutputStream("Serialized.ser",true);
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(fileOutputStream2);
                objectOutputStream2.writeObject(email);
                objectOutputStream2.close();
                fileOutputStream2.close();
                }catch (IOException excep2){
                    //excep2.printStackTrace();
                }
                break;
            case 3: // Printing out all the recipients who have birthdays
                scanner.nextLine();
                Boolean flag3 = false;
                System.out.println("input format - yyyy/MM/dd (ex: 2018/09/17)");// input format - yyyy/MM/dd (ex: 2018/09/17)
                String inputCase3 = scanner.nextLine().trim();
                DateTimeFormatter myFormatObjCase3 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate givenDate = LocalDate.parse(inputCase3,myFormatObjCase3);
                for (int i = 0; i < wishList.size(); i++){ // Printing all details of wished recipient
                    if(wishList.get(i).getDateOfBirth().getMonth() == givenDate.getMonth() && wishList.get(i).getDateOfBirth().getDayOfMonth() == givenDate.getDayOfMonth()){
                        System.out.println("Recipient Name is : " + wishList.get(i).getName());
                        System.out.println("Recipient Email Id is : " + wishList.get(i).getEmail());
                        System.out.println("Recipient date of birth : is " + wishList.get(i).getDateOfBirth());
                        System.out.println();

                        flag3 = true;
                    }
                }if(!flag3){ // Check if no birthday
                System.out.println("There is no recipients who have birthdays on " + inputCase3);
                }
                // code to print recipients who have birthdays on the given date
                break;
            case 4: // Printing out details of all the emails sent
                scanner.nextLine();
                Boolean flag4 = false ;
                System.out.println("input format - yyyy/MM/dd (ex: 2018/09/17)");// input format - yyyy/MM/dd (ex: 2018/09/17)
                String inputCase4 = scanner.nextLine().trim();
                DateTimeFormatter myFormatObjCase4 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate inputDate = LocalDate.parse(inputCase4,myFormatObjCase4);
                for (int i = 0; i < sentEmails.size(); i++){ // Printing all details of sent email
                    if(sentEmails.get(i).getSentDate().equals(inputDate)){
                        System.out.println("List of emails which sent on " + inputDate);
                        System.out.println("Email id : "+ sentEmails.get(i).getEmail());
                        System.out.println("Email content : "+sentEmails.get(i).getContent());
                        System.out.println("Email Subject : "+sentEmails.get(i).getSubject());
                        flag4 = true;
                    }
                }if (!flag4){ // Check if no emails
                System.out.println("No emails sent yet");
                }
                // code to print the details of all the emails sent on the input date
                break;
            case 5: // Printing out the number of recipient objects in the application
                System.out.println("Total number of recipient objects : " + Recipients.getCount());
                // code to print the number of recipient objects in the application
                break;
        }
    }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)