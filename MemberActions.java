public interface MemberActions {
    void borrowBook(Book book);  // Method to borrow a book
    void returnBook(Book book);  // Method to return a book
    void viewTransactionHistory();  // Method to view transaction history
    String getMemberId();  // Method to get the member's ID
    String getName();  // Method to get the member's name
}

