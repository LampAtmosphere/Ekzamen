import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private double balance;
    private final Lock lock = new ReentrantLock();
    private final Condition sufficientFunds = lock.newCondition();

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            sufficientFunds.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) throws InterruptedException {
        lock.lock();
        try {
            while (balance <= amount) {
                sufficientFunds.await();
            }
            while (balance >= 500) {
                balance -= amount;
                System.out.println("Снятие " + amount + " рублей. Баланс: " + balance + " рублей.");
            }
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        Account account = new Account(1000);
        Random random = new Random();

        Thread depositThread = new Thread(() -> {
            while (true) {
                double amount = random.nextDouble() * 100;
                double roundedAmount = Math.round(amount * 100.0) / 100.0;
                account.deposit(amount);
                System.out.println("Пополнение на " + roundedAmount + " рублей. Баланс: " + account.getBalance() + " рублей.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread withdrawThread = new Thread(() -> {
            try {
                double amount = 500;
                account.withdraw(amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        depositThread.start();
        withdrawThread.start();
    }
}