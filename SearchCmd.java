import java.util.List;
import java.util.Objects;

public class SearchCmd extends LibraryCommand {
    /** A string that represent a space */
    private static String SPACE = " ";
    /** The keyword that user want to find.*/
    private static String searchWord;
    /**
     * Create the Search command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected to be a single words.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public SearchCmd(String argumentInput) {
        super(CommandType.SEARCH, argumentInput);
    }


    /**
     * Check validity for the argument user inputted and parse the argument in.
     *
     * @param argumentInput user input that expected to be the keyword of title wanted to search
     *                      and should be a single words.
     * @return true if the user input a single words that neither white space in between or entirely blank.
     * @throws NullPointerException if the argumentInput is null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given input argument must not be null.");
        searchWord = argumentInput;
        return !(argumentInput.contains(SPACE) || argumentInput.isBlank());
    }

    /**
     * Search through the library data with the keyword that is inputted by user and
     * print the book title if contained.
     *
     * @param data book data to be considered for searching.
     * @throws NullPointerException if the library data is null.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data,"The data shouldn't be null.");
        bookSearching(data);
    }

    /**
     * A helper function that iterate and search through the library data.
     *
     * @param data the library data that searched
     */
    private void bookSearching(LibraryData data) {
        boolean found = false;
        String searchWordToLower = searchWord.toLowerCase();
        List<BookEntry> books = data.getBookData();
        for (BookEntry book:books){
            String bookTitle = book.getTitle();
            String bookTitleToLower = bookTitle.toLowerCase();
            if (bookTitleToLower.contains(searchWordToLower)){
                found = true;
                System.out.println(bookTitle);
            }
        }
        if (!found){
            System.out.println("No hits found for search term: " + searchWord);
        }
    }
}
