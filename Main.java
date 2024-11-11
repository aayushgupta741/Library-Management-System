import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Create an instance of the Library Management System
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();

        // Create a scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Loop to display the menu and process user input until "quit" is selected
        while (running) {
            // Print the menu options for the user
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a new book");
            System.out.println("2. Register a new member");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Show available books");
            System.out.println("6. View all transactions");
            System.out.println("7. Check library status");
            System.out.println("8. Quit");

            // Ask the user to choose an option
            System.out.print("Please choose an option (1-8): ");
            String input = scanner.nextLine();
            int choice = -1;

            // Check if the input is a valid number
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1: // Add a new book
                    System.out.print("Enter book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();

                    if (bookId.isEmpty() || title.isEmpty() || author.isEmpty()) {
                        System.out.println("Book details cannot be empty. Try again.");
                        break;
                    }

                    // Check if the book already exists
                    Book existingBook = findBookById(librarySystem, bookId);
                    if (existingBook != null) {
                        System.out.println("Book with ID " + bookId + " already exists.");
                        break;
                    }

                    // Create and add the book
                    Book newBook = new Book(bookId, title, author);
                    librarySystem.addBook(newBook);
                    break;

                case 2: // Register a new member
                    System.out.print("Enter member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();

                    if (memberId.isEmpty() || memberName.isEmpty()) {
                        System.out.println("Member details cannot be empty. Try again.");
                        break;
                    }

                    // Check if the member already exists
                    Member existingMember = findMemberById(librarySystem, memberId);
                    if (existingMember != null) {
                        System.out.println("Member with ID " + memberId + " already exists.");
                        break;
                    }

                    // Register the member
                    Member newMember = new Member(memberId, memberName);
                    librarySystem.registerMember(newMember);
                    break;

                case 3: // Borrow a book
                    System.out.print("Enter book ID to borrow: ");
                    bookId = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextLine();

                    // Find the book and member
                    Book bookToBorrow = findBookById(librarySystem, bookId);
                    Member borrowingMember = findMemberById(librarySystem, memberId);

                    if (bookToBorrow == null || borrowingMember == null) {
                        break; // Book or member not found, break the case
                    }

                    if (bookToBorrow.isAvailable()) {
                        librarySystem.borrowBook(bookToBorrow, borrowingMember);
                    } else {
                        librarySystem.borrowBook(bookToBorrow, borrowingMember); // Add to waitlist if book is unavailable
                    }
                    break;

                case 4: // Return a book
                    System.out.print("Enter book ID to return: ");
                    bookId = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextLine();

                    // Find the book and member
                    Book bookToReturn = findBookById(librarySystem, bookId);
                    Member returningMember = findMemberById(librarySystem, memberId);

                    if (bookToReturn == null || returningMember == null) {
                        break; // Book or member not found, break the case
                    }

                    if (!bookToReturn.isAvailable()) {
                        librarySystem.returnBook(bookToReturn, returningMember);
                    } else {
                        System.out.println("This book was not borrowed.");
                    }
                    break;

                case 5: // Show available books
                    librarySystem.showAvailableBooks();
                    break;

                case 6: // View all transactions
                    librarySystem.viewAllTransactions();
                    break;

                case 7: // Check library status
                    librarySystem.checkLibraryStatus();
                    break;

                case 8: // Quit
                    System.out.println("Quitting the program. Goodbye!");
                    running = false; // Exit the loop and terminate the program
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        // Close the scanner after use
        scanner.close();
    }

    // Helper method to find a book by its ID
    private static Book findBookById(LibraryManagementSystem librarySystem, String bookId) {
        for (Book book : librarySystem.getBooks()) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        System.out.println("Book not found.");
        return null;
    }

    // Helper method to find a member by their ID
    private static Member findMemberById(LibraryManagementSystem librarySystem, String memberId) {
        for (Member member : librarySystem.getMembers()) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        System.out.println("Member not found.");
        return null;
    }
}
