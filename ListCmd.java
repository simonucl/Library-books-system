import java.util.List;
import java.util.Objects;

public class ListCmd extends LibraryCommand {
    /** A string that represent long list type.*/
    private static final String LONG = "long";
    /** A string that represent short list type.*/
    private static final String SHORT = "short";
    /** String that represent user inputs nothing.*/
    private static final String NOTHING = "";
    /** Generate a string to remember the list type user inputted.*/
    private String listType;
    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected to be long,short or blank.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public ListCmd(String argumentInput) {
        super(CommandType.LIST, argumentInput);
    }

    /**
     * Check the validity of the argument input and parse the list type in.
     * @param argumentInput argument input which expected to be either SHORT,LONG or NOTHING.
     * @return true if the argument input is valid.
     * @throws NullPointerException if given argument are null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given input argument must not be null.");
        if (argumentInput.equals(LONG)){
            listType = LONG;
            return true;
        }
        else if(argumentInput.equals(SHORT) || argumentInput.equals(NOTHING)){
            listType = SHORT;
            return true;
        }
        return false;
    }

    /**
     * Execute the list command. This will list the data from the
     * library data with the list type that user inputted.
     *
     * @param data book data to be printed.
     * @throws NullPointerException if given LibraryData is null.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data,"The data shouldn't be null.");
        List<BookEntry> books = data.getBookData();
        if (checkAndPrintBookSize(books.size())){
            if (listType.equals(LONG)){
                printListLong(books);
            }
            else if(listType.equals(SHORT)){
                printListShort(books);
            }
        }
    }

    /**
     * Check the number of books inside the library data and print how many books in the console.
     * @param booksSize number of books inside the library data.
     * @return true if the number of books are not zero.
     */
    private boolean checkAndPrintBookSize(int booksSize){
        if (booksSize == 0){
            System.out.println("The library has no book entries.");
            return false;
        }
        else{
            System.out.println(booksSize + " books in library:");
            return true;
        }
    }

    /**
     * Printing the long list for library data in the console that includes every details of each book.
     * @param books the list of books inside library data.
     */
    private void printListLong(List<BookEntry> books){
        for (BookEntry book : books) {
            System.out.println(book.toString());
            System.out.println();
        }
    }

    /**
     * Printing the short list for library data in the console that only includes the title of each book.
     * @param books the list of books inside library data.
     */
    private void printListShort(List<BookEntry> books){
        for (BookEntry book : books) {
            System.out.println(book.getTitle());
        }
    }
}
