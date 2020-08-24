package alvicom_sample.model;


import java.util.ArrayList;
import java.util.List;

public class BankAccountDetail {
    private String currency;
    private Float actualAmount;
    private List<TransactionMessage> transactions;

    public BankAccountDetail(String currency, Float actualAmount) {
        this.currency = currency;
        this.actualAmount = actualAmount;
        this.transactions = new ArrayList();
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getActualAmount() {
        return this.actualAmount;
    }

    public void setActualAmount(Float actualAmount) {
        this.actualAmount = actualAmount;
    }

    public List<TransactionMessage> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<TransactionMessage> transactions) {
        this.transactions = transactions;
    }
}
