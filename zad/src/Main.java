import java.util.List;

public class Main {
    public static void main(String[] args) {
        BankDepositorManager manager = new BankDepositorManager();

        Date depositDate = new Date(14, 11, 2024);

        BankDepositor depositor1 = new BankDepositor("1234567890", "Ivan Ivanov", 1000.00, depositDate);
        BankDepositor depositor2 = new BankDepositor("0987654321", "Petar Petrov", 2000.00, depositDate);
        BankDepositor depositor3 = new BankDepositor("5324524355", "Georgi Marinov", 500.00, depositDate);

        manager.addDepositor(depositor1);
        manager.addDepositor(depositor2);
        manager.addDepositor(depositor3);

        manager.sortDepositorsByAmount();

        depositor2.setDepositAmount(1000);


        manager.saveToFile("depositors.txt");


        List<BankDepositor> list = manager.loadFromFile("depositors.txt");
        if (list!= null) {
            list.forEach(System.out::println);
        }
    }
}
