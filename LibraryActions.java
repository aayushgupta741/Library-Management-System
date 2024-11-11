public interface LibraryActions {
    void addBook(Book book); // Add a book to the library
    void registerMember(Member member); // Register a new member
    void borrowBook(Book book, Member member); // Borrow a book for a member
    void returnBook(Book book, Member member); // Return a borrowed book
    void showAvailableBooks(); // Display available books
    void viewAllTransactions(); // View all transactions
    void checkLibraryStatus(); // Check the library's current status
}
