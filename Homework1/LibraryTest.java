import Book.Book.java;

public class LibraryTest {
    public static void main(String[] args) {
        
        Book Book1 = new Book("Book A", "Author A", 1);
        Book Book2 = new Book("Book B", "Author B", 2);

        Book1.displayBookInfo();
        Book1.borrowBook();
        Book1.displayBookInfo();
        Book1.borrowBook();
        Book1.returnBook();
        Book1.displayBookInfo();


        Book2.displayBookInfo();
        Book2.borrowBook();
        Book2.displayBookInfo();
        Book2.borrowBook();
        Book2.returnBook();
        Book2.displayBookInfo();

    }
}
