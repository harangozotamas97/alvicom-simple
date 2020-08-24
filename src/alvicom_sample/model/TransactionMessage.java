package alvicom_sample.model;

public class TransactionMessage {
    private String accountNumber;
    private String currency;
    private Float amount;
    private Float changeRate;

    public TransactionMessage(String accountNumber, String currency, Float amount, Float changeRate) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.amount = amount;
        this.changeRate = changeRate;
    }

    public TransactionMessage() {
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getChangeRate() {
        return this.changeRate;
    }

    public void setChangeRate(Float changeRate) {
        this.changeRate = changeRate;
    }
}
