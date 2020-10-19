import java.io.IOException;
import java.util.ArrayList;

public class ContactsManager {
    public static void main(String[] args){
        displayUi();
        /*ArrayList<Contact> firstGo = new ArrayList<>();

        firstGo.add(new Contact("Buster Weaver", "(843) 320-9854"));
        firstGo.add(new Contact("Tomas Carlson",    "(918) 657-2557"));
        firstGo.add(new Contact("Douglas Walter",   "(740) 967-9185"));
        firstGo.add(new Contact("Alyce Hutchinson", "(724) 744-7956"));
        firstGo.add(new Contact("Brooke Armstrong", "(804) 598-8251"));
        firstGo.add(new Contact("Anwar Maxwell",    "(740) 927-6882"));
        firstGo.add(new Contact("Isla-Mae Baxter",  "(603) 253-7520"));
        firstGo.add(new Contact("Lacy Woolley",     "(606) 598-8735"));
        firstGo.add(new Contact("Tara Morris",      "(870) 257-3039"));
        firstGo.add(new Contact("Hanifa Arias",     "(716) 992-4634"));

        FileIO.writeContactsList(firstGo);
*/
    }

    public static void displayUi(){
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        space();
        usersChoice();
    }

    public static void usersChoice(){
        Input selection = new Input();
        int choice = selection.getInt("Enter an option (1, 2, 3, 4 or 5): ");
        switch (choice){
            case 1:
                FileIO.viewContacts();
                break;
            case 2:
                FileIO.addContact();
                break;
            case 3:
                FileIO.searchContacts();
                break;
            case 4:
                FileIO.deleteContact();
                break;
            case 5:
                System.out.println("Sayonara");
                break;

            default:
                System.out.println("Looks like you've made an invalid selection.");
                usersChoice();
        }
    }

    public static void continueChoosing(){
        space();
        System.out.print("Would you like to make another selection? ");
        Input choice = new Input();
        if(choice.yesNo()){
            space();
            displayUi();
        }else {
            System.out.println("Take care!");
            System.exit(0);
        }
    }


    public static void space(){
        System.out.println();
    }


}
