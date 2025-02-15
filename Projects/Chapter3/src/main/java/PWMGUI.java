import java.util.Scanner;

public class PWMGUI {
    
    private Scanner sc;
    private PasswordManager pm;

    public PWMGUI(Scanner scanner) {
        this.sc = scanner;
        this.pm = new PasswordManager();
    }

    public void showPasswordMenu(){
        while (true) {
            System.out.println("\nPassword Manager: ");
            System.out.println("1. Store a password");
            System.out.println("2. Retrieve a password");
            System.out.println("3. Return to Main Menu");

            int choice = getUserChoice();

            switch(choice) {
                case 1:
                    storePassword();
                    break;
                case 2:
                    retrievePassword();
                    break;
                case 3:
                    return; //Exit to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private int getUserChoice() {
        while (true) {
            System.out.println("Enter your choice: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                return choice;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }

    private void storePassword() {
        System.out.println("Enter a username: ");
        String username = sc.nextLine();

        System.out.println("Enter a password: ");
        String password = sc.nextLine();

        PasswordManager.storePassword(username, password);
        System.out.println("Password stored successfully");
    }

    private void retrievePassword() {
        System.out.println("Enter a username: ");
        String username = sc.nextLine();

        String password = PasswordManager.retrievePassword(username);
        if (password != null) {
            System.out.println("Retrieved password: " + password);
        } else {
            System.out.println("Username not found.");
        }
    }
}
