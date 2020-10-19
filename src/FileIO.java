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
    private static boolean gotMatch;

    public static void viewContacts() {
        try {
            List<String> contacts = Files.readAllLines(dataFile);
            showContactsHeader();
            for (String contact : contacts) {
                System.out.println(contact);
            }
        } catch (IOException e) {
            System.err.println("Contacts file not found.");
        } finally {
            ContactsManager.continueChoosing();
        }
    }

    public static String formatPhoneNum(String aNum) {
        String areaCode = null;
        String exchangeCode = null;
        String subscriberNum = null;
        if (aNum.length() == 10) {
            areaCode = "(" + aNum.substring(0, 3) + ") ";
            exchangeCode = aNum.substring(3, 6) + "-";
            subscriberNum = aNum.substring(6);
        } else if (aNum.length() == 7) {
            areaCode = "(xxx) ";
            exchangeCode = aNum.substring(0, 3) + "-";
            subscriberNum = aNum.substring(3);
        }
        return new StringBuilder().append(areaCode).append(exchangeCode).append(subscriberNum).toString();
    }

    public static void addContact() {
        Input entry = new Input();
        gotMatch = false;
        System.out.print("Enter your new contacts name: ");
        String name = entry.getString();
        ContactsManager.space();
        try {
            List<String> currentList = Files.readAllLines(dataFile);
            for (String contact : currentList) {
                if (contact.toLowerCase().contains(name.toLowerCase())) {
                    gotMatch = true;
                    System.out.println(contact);
                    ContactsManager.space();
                    break;
                }
            }
            if (gotMatch) {
                System.out.println("A name match was found in your contact's list.\n" +
                        "Would you like to continue adding a new contact?");
                if (!entry.yesNo()) {
                    ContactsManager.continueChoosing();
                }
            }

        } catch (IOException e) {
            System.err.println("Contact file not found.");
            ContactsManager.continueChoosing();
        }
        System.out.print("Enter new contact's phone number:(numbers only, please) ");
        String phoneNum = formatPhoneNum(entry.getString());
        try {
            Files.write(
                    dataFile,
                    Arrays.asList(String.format("%-20s | %-20s", name, phoneNum)),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("Unable to create new contact. Target file not found.");
        } finally {
            ContactsManager.continueChoosing();
        }
    }

    public static void searchContacts() {
        Input entry = new Input();
        gotMatch = false;
        System.out.print("Enter the name you're looking for: ");
        String search = entry.getString().toLowerCase();
        try {
            List<String> currentList = Files.readAllLines(dataFile);
            for (String contact : currentList) {
                if (contact.toLowerCase().contains(search)) {
                    gotMatch = true;
                    break;
                }
            }
            ContactsManager.space();
            if (!gotMatch) {
                System.out.println("No search results found.");

            } else {
                showContactsHeader();
                for (String actualContact : currentList) {
                    if (actualContact.toLowerCase().contains(search)) {
                        System.out.println(actualContact);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Contacts file not found.");
        } finally {
            ContactsManager.continueChoosing();
        }
    }

    public static void deleteContact() {
        Input entry = new Input();
        gotMatch = false;
        System.out.print("Enter the contact you're deleting: ");
        String search = entry.getString().toLowerCase();
        if (search.isEmpty()) {
            System.out.println("Don't be squeamish. Tell me the person's name please. ");
            deleteContact();
        }
        try {
            List<String> hereForNow = Files.readAllLines(dataFile);
            List<String> keepers = new ArrayList<>();
            for (String potentialVictim : hereForNow) {
                if (potentialVictim.toLowerCase().contains(search)) {
                    System.out.println(potentialVictim);
                    System.out.print("Is this the contact you want to delete? ");
                    gotMatch = true;
                    if (entry.yesNo()) {
                        ContactsManager.space();
                        System.out.println("That contact is now dead to you.");
                    } else {
                        keepers.add(potentialVictim);
                    }
                } else {
                    keepers.add(potentialVictim);
                }
            }
            if (!gotMatch) {
                System.out.println("No search results were found.");
            }
            writeContactsList(keepers);
        } catch (IOException e) {
            System.err.println("Contacts file not found.");
        } finally {
            ContactsManager.continueChoosing();
        }

    }

    public static void writeContactsList(ArrayList<Contact> anArray) {
        try {
            FileWriter fw = new FileWriter(dataFile.toString());
            for (Contact person : anArray) {
                fw.write(String.format("%-20s | %-20s\n", person.getName(), person.getPhoneNumber()));
            }
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void writeContactsList(List<String> anArray) {
        try {
            FileWriter fw = new FileWriter(dataFile.toString());
            for (String person : anArray) {
                fw.write(String.format("%s\n", person));
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Error. Contacts file could not be created or overwritten");
        }
    }

    public static void showContactsHeader() {
        ContactsManager.space();
        System.out.printf("%-20s | %-20s\n", "Name", "Phone Number");
        System.out.println("-------------------------------------------");
    }

}
