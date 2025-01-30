public class Book {
    // Class attributes
    private String title;
    private String author;
    private int yearPublished;
    private boolean isAvailable;


    // Constructor
    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isAvailable = true;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("You have borrowed the book " + title);
        } else {
            System.out.println("Sorry, the book " + title + " is already borrowed");
        }

    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("You have returned the book " + title);
        } else {
            System.out.println("Sorry, the book " + title + " is already available");
        }
    }

    public void displayBookInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Year Published: " + yearPublished);
        System.out.println("Availability: " + (isAvailable) + "\n\n");
    }
}