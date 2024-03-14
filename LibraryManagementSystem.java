import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
// scanner for input of all data

public class LibraryManagementSystem {

    private static Scanner scanner = new Scanner(System.in);
    private static LIbrary lib = new LIbrary();

    public static void main(String[] args) {
        //flag to keep the menu running till user exits
        boolean flag = false;

        //Menu for operations of LMS
        while (!flag) {
            System.out.println("--Welcome to Library Management System--");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Display Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Search Books by User ID");
            System.out.println("7. Search Books by Title");
            System.out.println("8. Search Books by Author");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
    
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
    
                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        addUser();
                        break;
                    case 3:
                        displayBooks();
                        break;
                    case 4:
                        borrowBook();
                        break;
                    case 5:
                        returnBook();
                        break;
                    case 6:
                        searchBooksByUserId();
                        break;
                    case 7:
                        searchBooksByTitle();
                        break;
                    case 8:
                        searchBooksByAuthor();
                        break;
                    case 9:
                        flag = true;
                        System.out.println("Leaving Library Management System. Goodbye!");
                        break;
                    default:
                        System.out.println("Error! Please choose again");
                }
            } catch (InputMismatchException e) {
                // Clear the scanner's buffer
                scanner.nextLine();
                System.out.println("Error! Invalid input. Please enter a number.");
            }
        }
        // Close the scanner after the loop ends
        scanner.close();
    }
    

    // function for adding books
    private static void addBook() {
        System.out.println("Enter book details:");
    
        int id;
        String title;
        String author;
        String genre;
    
        //read and validate Book ID
        try {
            System.out.print("Book ID: ");
            id = scanner.nextInt();
            scanner.nextLine(); 
        } catch (InputMismatchException e) {
            // Clear the scanner's buffer to ignore the error and so that it is not stored 
            scanner.nextLine();
            System.out.println("Error! Please enter a valid integer ID.");
            return;
        }
    
        System.out.print("Title: ");
        title = scanner.nextLine();
    
        //Validate title input
        if (title.matches(".*\\d+.*")) {
            System.out.println("Error! Please enter a valid title.");
            return;
        }
    

        System.out.print("Author: ");
        author = scanner.nextLine();
    
        // Validate author input
        if (author.matches(".*\\d+.*")) {
            System.out.println("Error! Author should not contain numbers. Please enter a valid author.");
            return;
        }
    
        System.out.print("Genre: ");
        genre = scanner.nextLine();
    
        // Validate genre input
        if (genre.matches(".*\\d+.*")) {
            System.out.println("Error! Genre should not contain numbers. Please enter a valid genre.");
            return;
        }
    
        // Create and add book
        Book book = new Book(id, title, author, genre, true);
        lib.addBook(book);
        System.out.println("Book has been added to library");
    }
    


    // function for adding users
    private static void addUser() {
        System.out.println("Enter user details:");
        
        int userId;
        String name;
        long contact;
        
        //read and validate User ID
        try {
            System.out.print("User ID: ");
            userId = scanner.nextInt();
            scanner.nextLine(); 
        } catch (InputMismatchException e) {
            // Clear the scanner's buffer so that wrong input is cleared
            scanner.nextLine();
            System.out.println("Error! Invalid input for User ID. Please enter a valid integer ID.");
            return;
        }
    

        System.out.print("Name: ");
        name = scanner.nextLine();
    
        // Read and validate Contact
        try {
            System.out.print("Contact: ");
            contact = scanner.nextLong();
            scanner.nextLine(); 
        } catch (InputMismatchException e) {

            scanner.nextLine();
            System.out.println("Error! Invalid input for contact. Please enter a valid contact number.");
            return;
        }
    
        // Create and add user
        User user = new User(userId, name, contact);
        lib.addUser(user);
        System.out.println("User has been added to library");
    }
    
    // function to print books in LMS
    private static void displayBooks() {
        System.out.println("Books in the library:");
        for (Book book : lib.books) {
            System.out.println(book);
        }
    }

    // function for exceptions 
    private static int readIntegerInput(String prompt) {
        int value;
        try {
            System.out.print(prompt);
            value = scanner.nextInt();
        } catch (InputMismatchException e) {
            // Clear the scanner's buffer
            scanner.nextLine();
            System.out.println("Error! Input must be an integer value.");
            return -1;
        }
        return value;
    }
    
    private static void borrowBook() {
        int userId = readIntegerInput("Enter user Id: ");
        if (userId == -1) {
            return; //exit the method if there was an error
        }
    
        int bookId = readIntegerInput("Enter book Id: ");
        if (bookId == -1) {
            return; //exit the method if there was an error
        }
    
        User user = userFinder(userId);
        Book book = bookFinder(bookId);
    
        if (user != null && book != null) {
            lib.checkOutBook(user, book);
        } else {
            System.out.println("User or book was not found");
        }
    }
    
    private static void returnBook() {
        int userId = readIntegerInput("Enter user ID: ");
        if (userId == -1) {
            return; //exit the method if there was an error
        }
    
        int bookId = readIntegerInput("Enter book ID: ");
        if (bookId == -1) {
            return; //exit the method if there was an error
        }
    
        User user = userFinder(userId);
        Book book = bookFinder(bookId);
    
        if (user != null && book != null) {
            lib.returnBook(user, book);
        } else {
            System.out.println("User or book was not found");
        }
    }
    
    // find by user id
    private static void searchBooksByUserId() {
        int userId = readIntegerInput("Enter user ID: ");
        if (userId == -1) {
            return; //exit the method if there was an error
        }
    
        User user = userFinder(userId);
        if (user != null) {
            System.out.println("Books borrowed by user " + userId + ":");
            for (Book book : user.books) {
                System.out.println(book);
            }
        } else {
            System.out.println("User was not found");
        }
    }

    // find by book title
    private static void searchBooksByTitle()    {
    System.out.println("Search for books by title:");
    List<Book> booksByTitle = lib.searchByTitle("Title of the Book");
    if (!booksByTitle.isEmpty()) {
        System.out.println("Books found:");
        for (Book book : booksByTitle) {
            System.out.println(book);
        }
    } else {
        System.out.println("No books found with the specified title.");
    }
    }

    // find by author name
    private static void searchBooksByAuthor()   {
    System.out.println("Search for books by author:");
    List<Book> booksByAuthor = lib.searchByAuthor("Author Name");
    if (!booksByAuthor.isEmpty()) {
        System.out.println("Books found:");
        for (Book book : booksByAuthor) {
            System.out.println(book);
        }
    } else {
        System.out.println("No books found by the specified author.");
    }
    }

    // helper function to find users
    private static User userFinder(int userId) {
        for (User u : lib.users) {
            if (u.userId == userId) {
                return u;
            }
        }
        return null;
    }

    //helper function to find books
    private static Book bookFinder(int bookId) {
        for (Book book : lib.books) {
            if (book.bookId == bookId) {
                return book;
            }
        }
        return null;
    }
}
