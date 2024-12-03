import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final String fileName = "depositors.txt";

    private Menu() {
    }

    public static void displayMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Създаване на нов депозитор");
        System.out.println("2. Редактиране на депозитор по ЕГН");
        System.out.println("3. Изтриване на депозитор");
        System.out.println("4. Показване на всички депозити");
        System.out.println("0. Изход");
        System.out.print("Изберете опция: ");
    }


    public static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Невалиден избор! Моля, въведете номер на менюто.");
            scanner.nextLine();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }


    public static void addNewDepositor(BankDepositorManager manager, Scanner scanner) {
        System.out.print("Въведете ЕГН: ");
        String id = scanner.nextLine();
        System.out.print("Въведете име: ");
        String name = scanner.nextLine();
        System.out.print("Въведете сума: ");
        double amount = scanner.nextDouble();
        System.out.print("Въведете дата на депозита (ден месец година): ");
        int day = scanner.nextInt();
        int month = scanner.nextInt();
        int year = scanner.nextInt();
        scanner.nextLine();
        Date depositDate = new Date(day, month, year);
        BankDepositor newDepositor = new BankDepositor(id, name, amount, depositDate);
        manager.addDepositor(newDepositor);
        manager.saveToFile(fileName);
        System.out.println("Депозиторът е добавен успешно.");
    }


    public static void editDepositor(BankDepositorManager manager, Scanner scanner) {
        System.out.print("Въведете ЕГН на депозитора за редактиране: ");
        String egn = scanner.nextLine();
        BankDepositor depositorToEdit = manager.getDepositorByEGN(egn);

        if (depositorToEdit == null) {
            System.out.println("Не е намерен депозитор с такова ЕГН.");
            return;
        }

        boolean editing = true;

        while (editing) {
            System.out.println("\n--- Опции за редактиране ---");
            System.out.println("1. Промяна на име");
            System.out.println("2. Промяна на сума");
            System.out.println("3. Промяна на дата на депозит");
            System.out.println("4. Край на редактирането");
            System.out.print("Изберете опция: ");

            int editChoice = scanner.nextInt();
            scanner.nextLine();

            switch (editChoice) {
                case 1:
                    System.out.print("Въведете ново име: ");
                    String newName = scanner.nextLine();
                    depositorToEdit.setDepositorName(newName);
                    manager.saveToFile(fileName);
                    System.out.println("Името е успешно променено.");
                    break;

                case 2:
                    System.out.print("Въведете нова сума: ");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine();
                    depositorToEdit.setDepositAmount(newAmount);
                    manager.saveToFile(fileName);
                    System.out.println("Сумата е успешно променена.");
                    break;

                case 3:
                    System.out.print("Въведете нова дата (ден месец година): ");
                    int newDay = scanner.nextInt();
                    int newMonth = scanner.nextInt();
                    int newYear = scanner.nextInt();
                    scanner.nextLine();
                    depositorToEdit.setDepositDate(new Date(newDay, newMonth, newYear));
                    manager.saveToFile(fileName);
                    System.out.println("Датата на депозит е успешно променена.");
                    break;

                case 4:
                    System.out.println("Край на редактирането.");
                    editing = false;
                    break;

                default:
                    System.out.println("Невалидна опция. Опитайте отново.");
                    break;
            }
        }
    }

    public static void deleteDepositor(BankDepositorManager manager, Scanner scanner) {
        System.out.print("Въведете ЕГН на депозитора за изтриване: ");
        String deleteId = scanner.nextLine();

        if (manager.removeDepositor(deleteId)) {
            manager.saveToFile(fileName);
            System.out.println("Депозиторът е успешно изтрит.");
        } else {
            System.out.println("Не е намерен депозитор с такова ЕГН.");
        }
    }

    public static void displayAllDepositors(BankDepositorManager manager) {
        List<BankDepositor> allDepositors = manager.loadFromFile(fileName);

        manager.sortDepositorsByAmount();

        if (allDepositors.isEmpty()) {
            System.out.println("Няма добавени депозитори.");
        } else {
            allDepositors.forEach(System.out::println);
        }
    }

    public static void exitProgram(Scanner scanner) {
        System.out.println("Изход...");
        scanner.close();
    }
}
