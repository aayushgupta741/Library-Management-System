public class Book implements BookActions {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    // Static count to track the total number of books
    private static int bookCount = 0;

    // Constructor to initialize a new book
    public Book(String bookId, String title, String author) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be null or empty");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;  // Default to available when a book is created
        bookCount++;  // Increment book count
    }

    // Getter and Setter for bookId
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be null or empty");
        }
        this.bookId = bookId;
    }

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    // Getter and Setter for author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.author = author;
    }

    // Getter and Setter for availability
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Method to mark the book as unavailable when borrowed
    public void markAsUnavailable() {
        if (!isAvailable) {
            throw new IllegalStateException("The book is already marked as unavailable.");
        }
        this.isAvailable = false;
    }

    // Method to mark the book as available when returned
    public void markAsAvailable() {
        if (isAvailable) {
            throw new IllegalStateException("The book is already marked as available.");
        }
        this.isAvailable = true;
    }

    // Static method to get the current book count
    public static int getBookCount() {
        return bookCount;
    }

    // Method to remove a book and decrement the book count
    public static void removeBook(Book book) {
        if (book != null) {
            bookCount--;  // Decrement book count
            System.out.println("Book removed: " + book.getTitle());
        }
    }

    // Method to display book details
    public void displayBookDetails() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
    }

    // Override toString() method for easy printing
    @Override
    public String toString() {
        String availability = isAvailable ? "Available" : "Not Available";
        return "Book ID: " + bookId +
               ", Title: " + title +
               ", Author: " + author +
               ", Availability: " + availability;
    }

    // Method to compare two books based on bookId (if needed)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return bookId.equals(book.bookId);
    }

    // Optional: Override hashCode if using Book in hash-based collections
    @Override
    public int hashCode() {
        return bookId.hashCode();
    }
}
