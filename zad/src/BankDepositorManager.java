import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BankDepositorManager {

    private List<BankDepositor> depositors = new ArrayList<>();

    public void addDepositor(BankDepositor depositor) {
        depositors.add(depositor);
    }

    public void removeDepositor(String egn) {
        depositors.removeIf(depositor -> depositor.getEgn().equals(egn));
    }

    public void sortDepositorsByAmount() {
        depositors.sort(Comparator.comparingDouble(BankDepositor::getDepositAmount));
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(depositors);
            System.out.println("Data successfully saved to file: " + filename);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    public List<BankDepositor> loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            depositors = (ArrayList<BankDepositor>) ois.readObject();
            System.out.println("Data successfully loaded from file: " + filename);
            return depositors;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error loading data from file: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }
        return null;
    }
    
}
