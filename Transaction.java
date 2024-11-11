public class Transaction implements TransactionActions {
    private String bookId;
    private String memberId;
    private String transactionType; // "borrow" or "return"
    private String transactionId;

    // Static count to track the total number of transactions
    private static int transactionCount = 0;

    // Constructor to initialize a transaction
    public Transaction(String bookId, String memberId, String transactionType) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be null or empty.");
        }
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty.");
        }
        if (transactionType == null || (!transactionType.equals("borrow") && !transactionType.equals("return"))) {
            throw new IllegalArgumentException("Transaction type must be 'borrow' or 'return'.");
        }

        this.bookId = bookId;
        this.memberId = memberId;
        this.transactionType = transactionType;
        this.transactionId = generateTransactionId(); // Generate a unique transaction ID
        transactionCount++;  // Increment transaction count

    }

    // Getter for bookId
    public String getBookId() {
        return bookId;
    }

    // Setter for bookId
    public void setBookId(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be null or empty.");
        }
        this.bookId = bookId;
    }

    // Getter for memberId
    public String getMemberId() {
        return memberId;
    }

    // Setter for memberId
    public void setMemberId(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty.");
        }
        this.memberId = memberId;
    }

    // Getter for transactionType
    public String getTransactionType() {
        return transactionType;
    }

    // Setter for transactionType
    public void setTransactionType(String transactionType) {
        if (transactionType == null || (!transactionType.equals("borrow") && !transactionType.equals("return"))) {
            throw new IllegalArgumentException("Transaction type must be 'borrow' or 'return'.");
        }
        this.transactionType = transactionType;
    }

    // Getter for transactionId
    public String getTransactionId() {
        return transactionId;
    }

    // Method to generate a unique transaction ID (example implementation)
    private String generateTransactionId() {
        // For simplicity, combining bookId and memberId to create a transaction ID
        return "TXN-" + bookId + "-" + memberId + "-" + System.currentTimeMillis();
    }


     // Static method to get the current transaction count
     public static int getTransactionCount() {
        return transactionCount;
    }

    // Method to remove a transaction and decrement the transaction count
    public void removeTransaction(Transaction transaction) {
        if (transaction != null) {
            transactionCount--;  // Decrement transaction count
            System.out.println("Transaction removed: " + transaction.getTransactionId());
        }
    }

    // Method to display transaction details
    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + 
               ", Book ID: " + bookId + 
               ", Member ID: " + memberId + 
               ", Transaction Type: " + transactionType;
    }

    @Override
    public void displayTransactionDetails() {
        System.out.println("Transaction ID: " + transactionId + 
                           ", Book ID: " + bookId + 
                           ", Member ID: " + memberId + 
                           ", Transaction Type: " + transactionType);
    }
    

}
