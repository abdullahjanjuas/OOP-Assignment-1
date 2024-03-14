import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LIbrary {
    // array list to store data type of user class and book class 
    // it is used for ease of access

    public List<Book> books;
    public List<User> users;

    //file handling is done in these files
    // this is only file path (content is stored and changed along with every functions)
    public final String booksFilePath = "books.txt";
    public final String usersFilePath = "users.txt";

    //constructor
    public LIbrary() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        readBooksFromFile();
        readUsersFromFile();
    }

    //funtion to add a new book to the library
    public void addBook(Book book) {
        books.add(book);
        writeBooksToFile();
    }

    //function to add a new user to the library
    public void addUser(User user) {
        users.add(user);
        writeUsersToFile();
    }

    // check out a book
    public void checkOutBook(User user, Book book) {
        if (books.contains(book) && book.isAvailable()) {
            user.books.add(book);
            book.availability =false;
            System.out.println("Book checked out successfully.");
            writeBooksToFile();
        } else {
            System.out.println("Book not available for checkout.");
        }
    }

    // return a book
    public void returnBook(User user, Book book) {
        if (user.books.contains(book)) {
            user.books.remove(book);
            book.availability=true;
            System.out.println("Book returned successfully.");
            writeBooksToFile();
        } else {
            System.out.println("Book not borrowed by this user.");
        }
    }

    // search for books by title
    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    // search for books by author
    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.author.equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    // Method to get the list of books in the library
    public List<Book> getBooks() {
        return books;
    }

    // Method to read books data from file
    private void readBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(booksFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int bookId = Integer.parseInt(data[0]);
                String title = data[1];
                String author = data[2];
                String genre = data[3];
                boolean availability = Boolean.parseBoolean(data[4]);
                books.add(new Book(bookId, title, author, genre, availability));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to write books data to file
    private void writeBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(booksFilePath))) {
            for (Book book : books) {
                writer.write(book.bookId + "," + book.title + "," + book.author + "," + book.genre + "," + book.availability);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(usersFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int userId = Integer.parseInt(data[0]);
                String name = data[1];
                long contact = Long.parseLong(data[2]); // Parse as long
                User user = new User(userId, name, contact);
                
                // Check if there are borrowed books information
                if (data.length > 3) {
                    for (int i = 3; i < data.length; i++) {
                        int bookId = Integer.parseInt(data[i]);
                        // Find the corresponding book in the library and add it to the user's borrowedBooks list
                        for (Book book : books) {
                            if (book.bookId == bookId) {
                                user.books.add(book);
                                break;
                            }
                        }
                    }
                }
                
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
    

    // Method to write users data to file
    private void writeUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFilePath))) {
            for (User user : users) {
                writer.write(user.userId + "," + user.name + "," + user.contact);
                writer.newLine();
                // You can also write borrowed books information for each user
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
