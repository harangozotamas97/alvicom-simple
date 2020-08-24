package alvicom_sample;

import alvicom_sample.model.BankAccountDetail;
import alvicom_sample.model.TransactionMessage;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
    private static HashMap<String, BankAccountDetail> datas;
    private static final Scanner scanner;

    public Main() {
    }

    public static void main(String[] args) {
        initEngine();
        int counter = 0;

        for(boolean isRunning = true; isRunning; isRunning = !scanner.nextLine().equals("NEM")) {
            TransactionMessage nextTransaction = getInputFromConsole();
            if (nextTransaction != null) {
                BankAccountDetail bankAccountDetail = (BankAccountDetail)datas.get(nextTransaction.getAccountNumber());
                if (nextTransaction.getChangeRate() == null) {
                    bankAccountDetail.setActualAmount(bankAccountDetail.getActualAmount() + nextTransaction.getAmount());
                } else {
                    bankAccountDetail.setActualAmount(bankAccountDetail.getActualAmount() + nextTransaction.getChangeRate() * nextTransaction.getAmount());
                }

                bankAccountDetail.getTransactions().add(nextTransaction);
                ++counter;
                if (counter % 10 == 0) {
                    riport();
                }
            }

            System.out.println("Szeretné folytatni? A nem megadásával az alkalmazás leáll! (NEM) ");
        }

    }

    private static void initEngine() {
        datas = new HashMap();
        BankAccountDetail account1 = new BankAccountDetail("HUF", 150000.0F);
        BankAccountDetail account2 = new BankAccountDetail("USD", 1230.0F);
        datas.put("11111111-22222222", account1);
        datas.put("22222222-33333333", account2);
    }

    private static TransactionMessage getInputFromConsole() {
        TransactionMessage result = new TransactionMessage();
        System.out.println("Kérem adja meg melyik számlát szeretné használni a tranzakcióhoz!");
        String bankAccountNumber = scanner.nextLine();
        if (!datas.containsKey(bankAccountNumber)) {
            System.out.println("Ilyen számlaszám nem található a rendszerben!");
            return null;
        } else {
            System.out.println("Kérem adja meg milyen pénznemet használ a tranzakció során! (HUF/USD/CAD)");
            String currency = scanner.nextLine();
            if (!currency.equals("HUF") && !currency.equals("CAD") && !currency.equals("USD")) {
                System.out.println("Hibás pénznemet adott meg!");
                return null;
            } else {
                System.out.println("Kérem adja meg az összeget!");
                Float amount = scanner.nextFloat();
                Float changeRate = null;
                if (!currency.equals(((BankAccountDetail)datas.get(bankAccountNumber)).getCurrency())) {
                    System.out.println("Kérem adja meg a valutaárfolyamot!");
                    changeRate = scanner.nextFloat();
                }

                result.setAccountNumber(bankAccountNumber);
                result.setAmount(amount);
                result.setChangeRate(changeRate);
                result.setCurrency(currency);
                return result;
            }
        }
    }

    private static void riport() {
        Iterator var0 = datas.entrySet().iterator();

        while(var0.hasNext()) {
            Entry<String, BankAccountDetail> account = (Entry)var0.next();
            System.out.println("A " + (String)account.getKey() + " számlaszámhoz tartozó számla adatai");
            PrintStream var10000 = System.out;
            Float var10001 = ((BankAccountDetail)account.getValue()).getActualAmount();
            var10000.println("Aktuális összeg: " + var10001 + " " + ((BankAccountDetail)account.getValue()).getCurrency());
            System.out.println("Tranzakciók:");
            Iterator var2 = ((BankAccountDetail)account.getValue()).getTransactions().iterator();

            while(var2.hasNext()) {
                TransactionMessage transaction = (TransactionMessage)var2.next();
                System.out.println("----------------");
                System.out.println("Összeg: " + transaction.getAmount());
                System.out.println("Pénznem: " + transaction.getCurrency());
                System.out.println("Valutaárfolyam: " + transaction.getChangeRate());
                System.out.println("----------------");
            }
        }

    }

    static {
        scanner = (new Scanner(System.in)).useLocale(Locale.GERMAN);
    }
}
