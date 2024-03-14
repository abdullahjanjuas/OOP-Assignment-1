import java.util.ArrayList;
import java.util.List;

public class User {
    public int userId;
    public String name;
    public long contact;

    // array list for storing 
    public List<Book> books;

    //constructors
    public User() {
        userId = 0;
        name = "";
        contact = 0;
        books = new ArrayList<>();
    }

    public User(int Id, String n,long c) {
        userId = Id;
        name = n;
        contact = c;
        books = new ArrayList<>();
    }

    //to_String function to display 
    @Override
    public String toString() {
        return "User ID: " + userId + " Name: " + name + " Contact Information: " + contact + "\nBorrowed Books: " + books;
    }
}
