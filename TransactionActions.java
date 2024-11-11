public interface TransactionActions {
    void displayTransactionDetails(); // Display transaction details
    void removeTransaction(Transaction transaction); // Remove the transaction and decrement the transaction count
}
