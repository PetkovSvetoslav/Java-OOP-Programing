public interface Transaction {
    int getId();

    TransactionStatus getStatus();

    String getSender();

    String getReceiver();

    double getAmount();

    void setStatus(TransactionStatus status);
}
