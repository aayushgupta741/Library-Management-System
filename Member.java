import java.util.Stack;

public class Member implements MemberActions{
    private String memberId;
    private String name;
    private Stack<Transaction> transactionHistory;  // Stack to store member's transactions

    // Static count to track the total number of members
    private static int memberCount = 0;

    // Constructor to initialize the member
    public Member(String memberId, String name) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.memberId = memberId;
        this.name = name;
        this.transactionHistory = new Stack<>();
        memberCount++;  // Increment member count
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

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    // Getter for transaction history
    public Stack<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    // Method to borrow a book and add the transaction to history
    public void borrowBook(Book book) {
        if (book == null) {
            System.out.println("Invalid book.");
            return;
        }
        
        if (book.isAvailable()) {
            book.markAsUnavailable();  // Mark book as borrowed
            Transaction transaction = new Transaction(book.getBookId(), memberId, "borrow");
            pushTransaction(transaction);  // Add transaction to the history
            System.out.println("Book borrowed: " + book.getTitle());
        } else {
            System.out.println("Book is not available for borrowing.");
        }
    }

    // Method to return a book and add the transaction to history
    public void returnBook(Book book) {
        if (book == null) {
            System.out.println("Invalid book.");
            return;
        }

        if (!book.isAvailable()) {
            book.markAsAvailable();  // Mark book as available again
            Transaction transaction = new Transaction(book.getBookId(), memberId, "return");
            pushTransaction(transaction);  // Add transaction to the history
            System.out.println("Book returned: " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed.");
        }
    }

    // Push a transaction onto the stack (transaction history)
    public void pushTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Cannot push a null transaction.");
        }
        transactionHistory.push(transaction);  // Push the transaction to the stack
    }

    // Pop the most recent transaction from the stack
    public Transaction popTransaction() {
        if (transactionHistory.isEmpty()) {
            throw new IllegalStateException("No transactions to pop.");
        }
        return transactionHistory.pop();  // Pop the most recent transaction
    }

    // Peek at the most recent transaction without removing it
    public Transaction peekTransaction() {
        if (transactionHistory.isEmpty()) {
            throw new IllegalStateException("No transactions to peek.");
        }
        return transactionHistory.peek();  // Peek at the top transaction
    }

    // Check if the transaction stack is empty
    public boolean hasTransactions() {
        return !transactionHistory.isEmpty();  // Return true if there are transactions
    }

    // Method to view the transaction history of the member
    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found for this member.");
        } else {
            System.out.println("Transaction History for " + name + ":");
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Static method to get the current member count
    public static int getMemberCount() {
        return memberCount;
    }

    // Method to remove a member and decrement the member count
    public static void removeMember(Member member) {
        if (member != null) {
            // Clear the member's transaction history
            member.transactionHistory.clear();
            memberCount--;  // Decrement member count
            System.out.println("Member removed: " + member.getName());
        }
    }
    
    // Override toString() for displaying member details
    @Override
    public String toString() {
        return "Member ID: " + memberId + ", Name: " + name + ", Transactions: " + transactionHistory.size();
    }
}
