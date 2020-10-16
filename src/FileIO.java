import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileIO {
    public static Path dataFile = Paths.get("data", "contacts.txt");

    public static void viewContacts() throws IOException {
        List<String> contacts = Files.readAllLines(dataFile);
        ContactsManager.space();
        System.out.printf("%-20s | %-20s\n", "Name", "Phone Number");
        System.out.println("-------------------------------------------");
        for(String contact: contacts){
            System.out.println(contact);
        }
        ContactsManager.continueChoosing();
    }

    //insert formatPhoneNumber method

    public static void addContact() throws IOException{
        Input entry = new Input();
        System.out.print("Enter your new contacts name: ");
        String name = entry.getString();
        ContactsManager.space();
        System.out.print("Enter new contact's phone number: ");
        String phoneNum = entry.getString();

        Files.write(
                dataFile,
                Arrays.asList(String.format("%-20s | %-20s",name, phoneNum)),
                StandardOpenOption.APPEND
        );
        ContactsManager.continueChoosing();
    }

    public static void searchContacts() throws IOException{
        Input entry = new Input();
        Boolean gotMatch = false;
        System.out.print("Enter the name you're looking for: ");
        String search = entry.getString();
        List<String> currentList = Files.readAllLines(dataFile);
        ContactsManager.space();
        System.out.printf("%-20s | %-20s\n", "Name", "Phone Number");
        System.out.println("-------------------------------------------");
        for(String contact: currentList){
            if(contact.contains(search)){
                gotMatch = true;
                System.out.println(contact);
            }
        }
        if(!gotMatch) {
            System.out.println("Sorry, no matches found.");
        }
        ContactsManager.continueChoosing();
    }

    public static void deleteContact() throws IOException{
        Input entry = new Input();
        Boolean gotMatch = false;
        System.out.print("Enter the contact you're deleting: ");
        String search = entry.getString();
        List<String> hereForNow = Files.readAllLines(dataFile);
        List<String> keepers = new ArrayList<>();
        for(String potentialVictim: hereForNow){
            if(potentialVictim.contains(search)){
                System.out.println(potentialVictim);
                System.out.print("Is this the contact you want to delete? ");
                    if(entry.yesNo()){
                        System.out.println("That contact is now dead to you.");
                    }else{
                        keepers.add(potentialVictim);
                    }
            }else{
                keepers.add(potentialVictim);
            }
        }
        writeContactsList(keepers);
        ContactsManager.continueChoosing();
    }

    public static void writeContactsList(ArrayList<Contact> anArray) throws IOException {
        FileWriter fw = new FileWriter(dataFile.toString());
        for(Contact person: anArray){
            fw.write(String.format("%-20s | %-20s\n", person.getName(),person.getPhoneNumber()));
        }
        fw.close();
    }

    public static void writeContactsList(List<String> anArray) throws IOException {
        FileWriter fw = new FileWriter(dataFile.toString());
        for(String person: anArray){
            fw.write(String.format("%s\n", person));
        }
        fw.close();
    }

}
