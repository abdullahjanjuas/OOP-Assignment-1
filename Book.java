public class Book {
    
    //attributes of book class
    public int bookId;
    public String title;
    public String author;
    public String genre;
    public boolean availability;

    //default constructor
    public Book() {
        bookId = 0;  
        author = "";
        genre = "";
        availability = true; // all books are available initially
        title = "";
    }

    //parameterised constructor
    public Book(int id, String t, String au, String g, boolean a) {
        bookId = id;
        title = t;
        author = au;
        genre = g;
        availability = a;
    }

    // returns availibilty status of the book
    boolean isAvailable(){    
        return availability;
    }

    //to_String function to display 
    @Override
    public String toString() {
        return "Book ID: " + bookId + " Title: " + title + " Author: " + author + " Availability: " + (availability ? " Available" : " Not Available");
    }
}
