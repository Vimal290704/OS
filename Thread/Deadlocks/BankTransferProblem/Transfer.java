package Deadlocks.BankTransferProblem;

public class Transfer extends Thread {
    private Account from;
    private Account to;

    public Transfer(String name, Account from, Account to) {
        super(name);
        this.from = from;
        this.to = to;
    }
}
