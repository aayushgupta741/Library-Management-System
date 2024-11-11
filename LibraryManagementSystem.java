import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class LibraryManagementSystem implements LibraryActions {

    private LinkedList<Book> books; // LinkedList to store available books
    private Queue<Member> waitlist; // Queue to manage members waiting for books
    private Stack<Transaction> transactionHistory; // Stack to store all transactions (borrow/return)
    private List<Member> members; // List to store registered members

    // Constructor to initialize the library system
    public LibraryManagementSystem() {
        this.books = new LinkedList<>();
        this.waitlist = new LinkedList<>();
        this.transactionHistory = new Stack<>();
        this.members = new ArrayList<>(); // Initialize the members list
    }

    // Method to add a new book to the library
    public void addBook(Book book) {
        // Check if the book already exists
        for (Book b : books) {
            if (b.getBookId().equals(book.getBookId())) {
                System.out.println("This book already exists in the library.");
                return;
            }
        }
        books.add(book); // Add the book to the available books list
        System.out.println("Book added: " + book.getTitle());
    }

    // Method to register a new member
    public void registerMember(Member member) {
        // Ensure the member does not already exist (optional check based on implementation)
        members.add(member); // Add the member to the members list
        System.out.println("Member registered: " + member.getName());
    }

    // Method to borrow a book for a member
    public void borrowBook(Book book, Member member) {
        if (book == null) {
            System.out.println("Book does not exist.");
            return;
        }

        if (book.isAvailable()) {
            book.markAsUnavailable(); // Mark the book as unavailable
            Transaction transaction = new Transaction(book.getBookId(), member.getMemberId(), "borrow");
            transactionHistory.push(transaction); // Add the transaction to history
            System.out.println("Book borrowed: " + book.getTitle());
        } else {
            if (waitlist.contains(member)) {
                System.out.println("You are already on the waitlist.");
            } else {
                waitlist.add(member); // Add member to waitlist if the book is unavailable
                System.out.println("Book is currently unavailable. You have been added to the waitlist.");
            }
        }
    }

    // Method to return a borrowed book
    public void returnBook(Book book, Member member) {
        if (book == null) {
            System.out.println("Book does not exist.");
            return;
        }

        if (!book.isAvailable()) {
            book.markAsAvailable(); // Mark the book as available again
            Transaction transaction = new Transaction(book.getBookId(), member.getMemberId(), "return");
            transactionHistory.push(transaction); // Add the transaction to history
            System.out.println("Book returned: " + book.getTitle());

            // Check if anyone is in the waitlist
            if (!waitlist.isEmpty()) {
                Member nextMember = waitlist.poll(); // Remove the member from the front of the queue
                System.out.println("Notifying next member in waitlist: " + nextMember.getName());
                borrowBook(book, nextMember); // Borrow the book for the next member in the waitlist
            }
        } else {
            System.out.println("This book was not borrowed.");
        }
    }

    // Method to display available books
    public void showAvailableBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Available books:");
            boolean foundAvailableBook = false;
            for (Book book : books) {
                if (book.isAvailable()) {
                    System.out.println(book);
                    foundAvailableBook = true;
                }
            }
            if (!foundAvailableBook) {
                System.out.println("No available books at the moment.");
            }
        }
    }

    // Method to view all transactions
    public void viewAllTransactions() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Additional edge case handling:
    // Method to check if the library is empty
    public void checkLibraryStatus() {
        if (books.isEmpty() && waitlist.isEmpty()) {
            System.out.println("The library is empty and no members are waiting.");
        } else {
            System.out.println("Library status checked.");
        }
    }

    // Method to get all books in the library
    public List<Book> getBooks() {
        return books; // Return the list of available books
    }

    // Method to get all members (including waitlisted members)
    public Queue<Member> getMembers() {
        return waitlist; // Return the queue of members (assuming all members are in the waitlist)
    }
}
