import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankDepositorManager manager = new BankDepositorManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            Menu.displayMenu();
            int choice = Menu.getUserChoice(scanner);

            switch (choice) {
                case 1:
                    Menu.addNewDepositor(manager, scanner);
                    break;

                case 2:
                    Menu.editDepositor(manager, scanner);
                    break;

                case 3:
                    Menu.deleteDepositor(manager, scanner);
                    break;

                case 4:
                    Menu.displayAllDepositors(manager);
                    break;


                case 0:
                    Menu.exitProgram(scanner);
                    return;

                default:
                    System.out.println("Невалидна опция. Опитайте отново.");
                    break;
            }
        }
    }
}
