import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable class encapsulating data for a single book entry.
 * @author Simon U
 * @version 1.0
 * @since 2020-03-22
 */

public class BookEntry {
    /**
     * Each book will have its unique title,authors,rating,pages and ISBN.
     */
    private String title;
    private String[] authors;
    private float rating;
    private String ISBN;
    private int pages;

    /**
     * Construct a Book entry by using their details.
     * @param title The title of the book.
     * @param authors The author or authors of the book.
     * @param rating A rating between 0 and 5.
     * @param ISBN The International Standard Book Number of the book.
     * @param pages The number of pages of the book.
     * @throws NullPointerException if one of the data input are null.
     * @throws IllegalArgumentException if rating are not between 0 and 5.
     * @throws IllegalArgumentException if pages are negative.
     */
    public BookEntry(String title,String[] authors,float rating,String ISBN, int pages){
        Objects.requireNonNull(title,"The title shouldn't be null.");
        Objects.requireNonNull(authors,"The authors shouldn't be null.");
        Objects.requireNonNull(ISBN,"The ISBN shouldn't be null.");
        if (rating > 5 || rating < 0){
            throw new IllegalArgumentException("The rating should be within the interval of 0 and 5.");
        }
        else if (pages < 0){
            throw new IllegalArgumentException("The given pages must not be negative.");
        }
        this.title = title;
        this.authors = authors;
        this.rating = rating;
        this.pages = pages;
        this.ISBN = ISBN;
    }
    /**
     * Get the title of the book.
     * @return A string representing the book's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the authors of the book.
     * @return A String array representing the authors of the book.
     */

    public String[] getAuthors() {
        return authors;
    }

    /**
     * Get the rating of the book.
     * @return A float number representing the rating of the book.
     */
    public float getRating() {
        return rating;
    }

    /**
     * Get the ISBN of the book.
     * @return A string representing the ISBN.
     */

    public String getISBN() {
        return ISBN;
    }

    /**
     * Get the number of pages of the book.
     * @return An int representing the number of pages.
     */
    public int getPages() {
        return pages;
    }

    /**
     * Represent the details of the book in string.
     * @return A string that representing the details of the book.
     */
    @Override
    public String toString() {
        return  title + '\n' +
                "by " + String.join(", ",authors) + "\n" +
                "Rating: " + String.format("%.2f",rating) + "\n" +
                "ISBN: " + ISBN + '\n' +
                pages + " pages";
    }

    /**
     * Check whether it is equal to another book or not.
     * @param anotherBook Given another book to compare.
     * @return true if the book are equals to another book.
     */
    @Override
    public boolean equals(Object anotherBook) {
        if (this == anotherBook) return true;
        if (anotherBook == null || getClass() != anotherBook.getClass()) {
            return false;
        }
        BookEntry bookEntry = (BookEntry) anotherBook;
        return Float.compare(bookEntry.rating, rating) == 0 &&
                pages == bookEntry.pages &&
                Objects.equals(title, bookEntry.title) &&
                Arrays.equals(authors, bookEntry.authors) &&
                Objects.equals(ISBN, bookEntry.ISBN);
    }

    /**
     * Hashcode method of the Object superclass for BookEntry.
     * @return An int that represent the hashcode of the object.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(title, rating, ISBN, pages);
        result = 31 * result + Arrays.hashCode(authors);
        return result;
    }
    
}
