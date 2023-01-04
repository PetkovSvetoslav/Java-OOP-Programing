import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {

    private final Map<Integer, Transaction> transactionsById;

    public ChainblockImpl() {
        this.transactionsById = new LinkedHashMap<>();
    }

    public int getCount() {
        return this.transactionsById.size();
    }

    public void add(Transaction transaction) {
        if (!this.contains(transaction)) {
            this.transactionsById.put(transaction.getId(), transaction);
        }
    }

    public boolean contains(Transaction transaction) {
        return this.transactionsById.containsKey(transaction.getId());
    }

    public boolean contains(int id) {
        return this.transactionsById.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        transactionValidation(id);
        this.transactionsById.get(id).setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        transactionValidation(id);
        this.transactionsById.remove(id);
    }

    public Transaction getById(int id) {
        transactionValidation(id);
        return this.transactionsById.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = this.transactionsById.values()
                .stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());

        ensureNonEmpty(transactions);

        return transactions;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<String> senders = this.transactionsById.values()
                .stream()
                .filter(s -> s.getStatus().equals(status))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .map(Transaction::getSender)
                .collect(Collectors.toList());

        ensureNonEmpty(senders);

        return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<String> receivers = this.transactionsById.values()
                .stream()
                .filter(s -> s.getStatus().equals(status))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .map(Transaction::getReceiver)
                .collect(Collectors.toList());

        ensureNonEmpty(receivers);

        return receivers;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.transactionsById.values()
                .stream()
                .sorted(transactionComparator())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> senderTransactions = this.transactionsById.values()
                .stream()
                .filter(t -> t.getSender().equals(sender))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        ensureNonEmpty(senderTransactions);

        return senderTransactions;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> transactions = this.transactionsById.values()
                .stream()
                .filter(t -> t.getReceiver().equals(receiver))
                .sorted(transactionComparator())
                .collect(Collectors.toList());

        ensureNonEmpty(transactions);

        return transactions;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return this.transactionsById.values()
                .stream()
                .filter(t -> t.getStatus().equals(status))
                .filter(t -> t.getAmount() <= amount)
                .sorted(transactionComparator())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> transactions = this.transactionsById.values()
                .stream()
                .filter(t -> t.getSender().equals(sender))
                .filter(t -> t.getAmount() > amount)
                .sorted(transactionComparator())
                .collect(Collectors.toList());

        ensureNonEmpty(transactions);

        return transactions;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> transactions = this.transactionsById.values()
                .stream()
                .filter(t -> t.getReceiver().equals(receiver))
                .filter(t -> t.getAmount() >= lo && t.getAmount() < hi)
                .sorted(transactionComparator())
                .collect(Collectors.toList());

        ensureNonEmpty(transactions);

        return transactions;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return this.transactionsById.values()
                .stream()
                .filter(t -> t.getAmount() >= lo && t.getAmount() < hi)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return this.transactionsById.values().iterator();
    }

    private void transactionValidation(int id) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException("There is no transaction with such id!");
        }
    }

    private void ensureNonEmpty(Collection<?> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("There is no transaction with such a status!");

        }
    }

    private Comparator<Transaction> transactionComparator() {
        return (f, s) -> {
            int result = Double.compare(s.getAmount(), f.getAmount());

            if (result == 0) {
                result = Integer.compare(f.getId(), s.getId());
            }

            return result;
        };
    }
} 