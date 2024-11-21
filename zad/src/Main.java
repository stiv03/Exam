public class Main {
    public static void main(String[] args) {
        BankDepositorManager manager = new BankDepositorManager();

        Date depositDate = new Date(14, 11, 2024);

        BankDepositor depositor1 = new BankDepositor("1234567890", "Ivan Ivanov", 1000.00, depositDate);
        BankDepositor depositor2 = new BankDepositor("0987654321", "Petar Petrov", 2000.00, depositDate);

        manager.addDepositor(depositor1);
        manager.addDepositor(depositor2);

        manager.sortDepositorsByAmount();
        manager.displayDepositors();

        manager.saveToFile("depositors.txt");
        manager.loadFromFile("depositors.txt");
    }
}