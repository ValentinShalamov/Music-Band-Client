package messages;

public class ResultMessages {
    public static final String MUSIC_BAND_HAS_BEEN_ADDED_SUCCESSFUL = "The music band has been added successful \n";
    public static final String ADDITION_MISTAKE = "The music band hasn't been added. Such name of the band exists already \n";
    public static final String COLLECTION_IS_EMPTY = "The collection is empty \n";
    public static final String NO_SUCH_ID = "The music band hasn't been updated because of isn't such id \n";
    public static final String NOT_SUCH_ELEMENTS = "There's not such elements \n";
    public static final String MUSIC_BAND_HAS_BEEN_UPDATED_SUCCESSFUL = "The music band has been updated successful \n";
    public static final String UPDATED_MISTAKE = "The music band hasn't been updated. Such name of the band exists already \n";
    public static final String MUSIC_BAND_HAS_BEEN_DELETED_SUCCESSFUL = "The music band has been deleted successful \n";
    public static final String MUSIC_BAND_HAS_BEEN_DELETED_SUCCESSFUL_ID = "The music band has been deleted successful, id = ";
    public static final String DELETED_MISTAKE = "The music band hasn't been deleted, please, make sure that such id is exists \n";
    public static final String DELETED_MISTAKE_ID = "The music band hasn't been deleted, please, try again, id = ";
    public static final String COLLECTION_HAS_BEEN_DELETED = "The music bands have been deleted successful \n";
    public static final String SALES_BIGGER_THAN_MIN_ELEMENT = "The sales of the entered of the best album should be less than the min sales \n";
    public static final String CANNOT_ADD_ELEM_IN_EMPTY_COLLECTION = "You can't add the band with min of the sales until" +
            " the collection is empty \n";
    public static final String ALL_ELEMENT_BIGGER = "The all music bands has sales bigger than the entered \n";
    public static final String COLLECTION_HAS_BEEN_READ = "The music band from the files has been read successful \n";
    public static final String NO_SUCH_FILE = "There's not such file \n";
    public static final String FILE_READER_MISTAKE = "The file hasn't been read, please, try again \n";
    public static final String FILE_IS_EMPTY = "The file is empty \n";
    public static final String SAVING_MISTAKE = "The collection of music bands haven't been saved in the file, please, try again \n";
    public static final String FILE_CONTENT_INCORRECT = "The content of file isn't correct, check your file \n";
    public static final String SAVING_SUCCESSFUL = "The collection has been added successful," +
            "The path of the file : \n";
    public static final String NOT_RIGHT_ACCESS_ON_WRITE = "You don't have a right access on write \n";
    public static final String NOT_RIGHT_ACCESS_ON_READ = "You don't have a right access on read \n";
    public static final String PROGRAM_HAS_BEEN_COMPLETED = "The program has been completed \n";
    public static final String INTERRUPT_MESSAGE = "The data input has been interrupted \n";

    public static final String LIST_OF_AVAILABLE_COMMAND = """
            The list of available command:\s
            - help: show the list of available commands\s
            - info: show the information about music bands\s
            - show: show everything music bands\s
            - add: add a music band\s
            - update <id> : update the music band by its id\s
            - remove <id> : remove the music band by its id\s
            - clear: delete the all music bands\s
            - exit: stop the program\s
            - add_if_min: add a music band if its sales less than the min sales of the collection \s
            - remove_lower: delete the all music bands which have the sales less than will entered\s
            - history: show last 12 commands\s
            - min_by_best_album: show any music band who have min sales of the best album\s
            - filter_by_best_album: show the all music bands who have the sales of the best album equals will entered\s
            - print_field_asc_best_album: show the all music bands in ascending order of sales of the best album\s
            - save : save the music bands in a file \s
            - execute_script : run a script from a file \s
            
            -q use for an interrupt of the input data \s
            """;

}
