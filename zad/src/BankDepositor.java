import java.io.Serializable;

public class BankDepositor implements Serializable {

    private String egn;
    private String depositorName;
    private double depositAmount;
    private Date depositDate;
    private final double interestRate = 0.05;


    public BankDepositor(String egn, String depositorName, double depositAmount, Date depositDate) {
        setEgn(egn);
        this.depositorName = depositorName;
        this.depositAmount = depositAmount;
        this.depositDate = depositDate;
    }

    public double calculateCapitalIncrease() {
         return depositAmount * (1 + interestRate);

    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        if (!isValidEgn(egn)) {
            throw new IllegalArgumentException("Invalid EGN: " + egn);
        }
        this.egn = egn;
    }

    private boolean isValidEgn(String egn) {
        return egn != null && egn.length() == 10 && egn.matches("\\d{10}");
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    @Override
    public String toString() {
        return String.format("EGN: %s, Name: %s, Deposit Amount: %.2f, After Interest %.2f, Deposit Date: %s",
                egn, depositorName,depositAmount, calculateCapitalIncrease(), depositDate);
    }
}
