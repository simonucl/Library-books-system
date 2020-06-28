import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class RemoveCmd extends LibraryCommand {
    /** String that represent title */
    private static final String TITLE = "TITLE";
    /** String that represent author */
    private static final String AUTHOR = "AUTHOR";
    /** String that represent space */
    private static final String SPACE = " ";
    /** The remove type that user inputs, should be either TITLE or AUTHOR.*/
    private static String removeType;
    /** The keyword inputted by user that remove those from the library data if contained.*/
    private static String keyword;
    /**
     * Create the remove command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected to be either TITLE or AUTHOR, followed by the keyword.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public RemoveCmd(String argumentInput) {
        super(CommandType.REMOVE, argumentInput);
    }

    /**
     * Check the validity of the input argument and parse it in separately.
     *
     * @param argumentInput argument input which expected to be the type to remove followed by the keyword.
     * @return true if the first part are either TITLE or AUTHOR and the second shouldn't be blank.
     * @throws NullPointerException if the argument input is null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given input argument must not be null.");
        return checkValidInput(argumentInput);
    }

    private boolean checkValidInput(String argumentInput) {
        int splitIndex = argumentInput.indexOf(SPACE);
        if (!(splitIndex == -1)){
            removeType = argumentInput.substring(0,splitIndex);
            keyword = argumentInput.substring(splitIndex + 1);
            return (!(keyword.isBlank())&&((removeType.equals(TITLE)) || (removeType.equals(AUTHOR))));
        }
        else{
            return false;
        }
    }

    /**
     * Check the remove type that has parsed in then remove the corresponding one
     *
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if the book data input is null.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data,"The data shouldn't be null.");
        List<BookEntry> books = data.getBookData();
        if (removeType.equals(TITLE)){
            removeTitle(books,keyword);
        }
        else if(removeType.equals(AUTHOR)){
            removeAuthor(books,keyword);
        }
    }

    /**
     * Iterate through the book data and remove them if their title are equals to the keyword.
     * @param books the list of books that iterate from
     * @param keyword the keyword that remove if the title are equals to
     */
    private void removeTitle(List<BookEntry> books, String keyword){
        Iterator<BookEntry> booksIterator = books.iterator();
        boolean found = false;
        while (booksIterator.hasNext()){
            BookEntry book = booksIterator.next();
            String title = book.getTitle();
            if (title.equals(keyword)){
                booksIterator.remove();
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println(keyword + ": removed successfully.");
        }
        else{
            System.out.println(keyword + ": not found.");
        }
    }

    /**
     * Iterate through the book data and remove them if one of their authors are equals to the keyword.
     * @param books the list of books that iterate from
     * @param keyword the keyword that remove if one of the authors are equal
     */
    private void removeAuthor(List<BookEntry> books, String keyword){
        Iterator<BookEntry> booksIterator = books.iterator();
        int count = 0;
        while (booksIterator.hasNext()){
            BookEntry book = booksIterator.next();
            String[] authors = book.getAuthors();
            for (String author:authors){
                if (author.equals(keyword)){
                    booksIterator.remove();
                    count++;
                }
            }
        }
        System.out.println(count +" books removed for author: " + keyword);
    }
}
