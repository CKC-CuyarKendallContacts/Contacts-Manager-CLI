import java.util.Scanner;

public class Input {
    private final Scanner scanner;

    public Input(){
        this.scanner = new Scanner(System.in);
    }

    public String getString(){
        return this.scanner.nextLine();
    }

    public boolean yesNo() {
        System.out.print("(Y)es or (N)o? ");
        String input = getString().trim().toLowerCase();
        char checkValue = input.charAt(0);
        return checkValue == 'y';
    }

    public int getInt(int min, int max) {
        int input;
        boolean exceptionFound;
        boolean outOfBounds = false;
        do {
            exceptionFound = false;
            System.out.println("Enter a number between " + min + " and " + max + ": ");
            input = Integer.valueOf(getString());
            try {
                if (input < min || input > max) {
                    System.out.println("Number outside the given parameters.");
                    outOfBounds = true;
                } else {
                    // user entered a correct integer
                    outOfBounds = false;
                }
            } catch( Exception e ) {
                System.out.println("That is not an integer");
                System.out.println();
                exceptionFound = true;
            }

        } while (exceptionFound || outOfBounds);

        return input;
    }

    public int getInt() {
        System.out.print("Input a number: ");
        int input = 0;
        try {
            input = Integer.valueOf(getString());
        }catch (Exception e){
            System.out.println("That is not a valid number.\n");
            getInt();
        }
        return input;
    }

    public int getInt(String prompt){
        System.out.print(prompt);
        int input = 0;
        try {
            input = Integer.valueOf(getString());
        }catch (Exception e){
            System.out.println("That is not a valid number.\n");
            getInt();
        }
        return input;
    }

    public double getDouble(double min, double max) {
        double input = 0.0;
        System.out.println("Enter a number between " + min + " and " + max + ".");
            try {
                input = Double.valueOf(getString());
            } catch (Exception e) {
                System.out.println("That is not a valid number.");
                getDouble(min, max);
            }
            if (input < min || input > max) {
                System.out.println("You've entered a value outside the given parameters.");
                getDouble(min, max);
            }
        return input;
    }

    public double getDouble() {
        System.out.println("Input a number: ");
        double input = 0.0;
        try {
            input = Double.valueOf(getString());
        } catch (Exception e) {
            System.out.println("That is not a valid number.");
            getDouble();
        }
        return input;
    }
}
