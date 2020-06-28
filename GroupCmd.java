import java.util.*;

public class GroupCmd extends LibraryCommand {
    /** A string that represent title. */
    private static final String TITLE = "TITLE";
    /** A string that represent author. */
    private static final String AUTHOR = "AUTHOR";
    /** A string that represent double hash followed by a space. */
    private static final String DOUBLE_HASH = "## ";
    /** A string that represent digit. */
    private static final String DIGIT = "[0-9]";
    /** A string that represent the indentation of title. */
    private static final String TITLE_INDENT = "    ";
    /** the type input by the user, which should be either TITLE or AUTHOR */
    private static String groupType;
    /**
     * Create the group command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected to be either TITLE or AUTHOR.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public GroupCmd(String argumentInput) {
        super(CommandType.GROUP, argumentInput);
    }

    /**
     * Check the validity of the argument input and parse it in.
     *
     * @param argumentInput argument input which expected to be either TITLE or AUTHOR
     * @return true if the argument are valid
     * @throws NullPointerException if the argument input is null
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given input argument must not be null.");
        groupType = argumentInput;
        return (argumentInput.equals(TITLE) || argumentInput.equals(AUTHOR));
    }

    /**
     * Group the book respect to the type inputted by user and print them accordingly in the console
     *
     * @param data book data for grouping
     * @throws NullPointerException if the data is null
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data,"The data shouldn't be null.");
        List<BookEntry> books = data.getBookData();
        if (checkBookSize(books)) return;
        System.out.println("Grouped data by " + groupType);
        if (groupType.equals(TITLE)){
            groupByTitle(books);
        }
        else{
            groupByAuthor(books);
        }
    }

    private boolean checkBookSize(List<BookEntry> books) {
        if (books.size() == 0){
            System.out.println("The library has no book entries.");
            return true;
        }
        return false;
    }

    /**
     * Group the book according to their author and print them lexicographically
     *
     * @param books the list of books to be grouped
     */
    private void groupByAuthor(List<BookEntry> books) {
        List<String> authors = getAllAuthor(books);
        for (String author: authors){
            System.out.println(DOUBLE_HASH + author);
            searchAuthor(books, author);
        }
    }

    /**
     * Iterate the list of books and print their title if one of their authors are matched to the argument
     * @param books the list of books iterate to
     * @param author the author that search
     */
    private void searchAuthor(List<BookEntry> books, String author) {
        for (BookEntry book:books){
            List<String> bookAuthor = Arrays.asList(book.getAuthors());
            if (bookAuthor.contains(author)){
                System.out.println(TITLE_INDENT + book.getTitle());
            }
        }
    }

    /**
     * Get all the author from the list of books
     *
     * @param books the list of books that the author come from
     *
     * @return a list of authors from the list of books
     */
    private List<String> getAllAuthor(List<BookEntry> books){
        Set<String> allAuthorsSet = new HashSet<>(Collections.emptySet());
        for (BookEntry book: books){
            String[] authors = book.getAuthors();
            allAuthorsSet.addAll(Arrays.asList(authors));
        }
        List<String> allAuthors = new ArrayList<>(allAuthorsSet);
        Collections.sort(allAuthors);
        return(allAuthors);
    }


    /**
     * Group the book according to their title and print them lexicographically
     *
     * @param books the list of books to be grouped
     */

    private void groupByTitle(List<BookEntry> books){
        /* Sort the bookEntry by their title by implementing an own class. */
        books.sort(new BookEntryTitleComparator());
        char leadingChar = 0;
        boolean digitNotYetFound = true;
        for (BookEntry book: books){
            String title = book.getTitle();
            char titleLeadingChar = title.toUpperCase().charAt(0);
            if (isNewLeadingAlphabet(leadingChar, titleLeadingChar)){
                leadingChar = titleLeadingChar;
                System.out.println(DOUBLE_HASH+ leadingChar);
            }
            else if(isNewLeadingDigit(digitNotYetFound , titleLeadingChar)){
                System.out.println(DOUBLE_HASH + DIGIT);
                digitNotYetFound = false;
            }
            System.out.println(TITLE_INDENT + title);
        }
    }

    private boolean isNewLeadingDigit(boolean digitNotYetFound, char titleLeadingChar) {
        return Character.isDigit(titleLeadingChar) && digitNotYetFound;
    }

    private boolean isNewLeadingAlphabet(char leadingChar, char titleHeadingChar) {
        return leadingChar != titleHeadingChar && !Character.isDigit(titleHeadingChar);
    }

}

/**
 * Implement a new class for comparing between two book entry which will compare according to their title but digit will
 * larger than alphabet as we want the digit come at last.
 */
class BookEntryTitleComparator implements Comparator<BookEntry> {

    @Override
    public int compare(BookEntry bookOne, BookEntry bookTwo) {
        String BookOneTitle = bookOne.getTitle().toLowerCase();
        String BookTwoTitle = bookTwo.getTitle().toLowerCase();
        if (Character.isDigit(BookOneTitle.charAt(0)) && Character.isAlphabetic(BookTwoTitle.charAt(0))){
            return 1;
        }
        else if (Character.isAlphabetic(BookOneTitle.charAt(0)) && Character.isDigit(BookTwoTitle.charAt(0))){
            return -1;
        }
        else{
            return (BookOneTitle.compareTo(BookTwoTitle));
        }
    }
}
