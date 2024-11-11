public interface BookActions {
    void markAsUnavailable(); // Mark the book as unavailable when borrowed
    void markAsAvailable(); // Mark the book as available when returned
    void displayBookDetails(); // Display the details of the book
}
