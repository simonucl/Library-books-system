import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AddCmd extends LibraryCommand{
    /** Generate a path to remember the path for book's data. */
    private Path bookDataPath;

    /**
     * Create an add command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected to be a existing path ended with .csv.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public AddCmd(String argumentInput) {
        super(CommandType.ADD, argumentInput);
    }

    /**
     * Execute the add command. This will add the data from the
     * input path to library data.
     * @param data book data to be considered for input data.
     * @throws NullPointerException if given LibraryData is null.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data,"Given data shouldn't be null.");
        data.loadData(bookDataPath);
    }

    /**
     * Check the validity of the argument input and parse the path in.
     * @param argumentInput argument input which expected to be a valid path.
     * @return true if the argument input is valid.
     * @throws NullPointerException if given argument are null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given input argument must not be null.");
        bookDataPath = Paths.get(argumentInput);
        return isValidPathInput(argumentInput);
    }

    private boolean isValidPathInput(String argumentInput) {
        try {
            String fileExtension = argumentInput.substring(argumentInput.lastIndexOf('.'));
            return (fileExtension.equals(".csv"));
        }
        catch (StringIndexOutOfBoundsException e){
            return false;
        }
    }
}
