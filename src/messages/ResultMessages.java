package messages;

public class ResultMessages {
    public static final String MUSIC_BAND_HAS_BEEN_ADDED_SUCCESSFUL = "The music band has been added successful \n";
    public static final String ADDITION_MISTAKE = "The music band hasn't been added, please, try again \n";
    public static final String EXIT_MESSAGE = "The program has been completed successfully \n";
    public static final String COLLECTION_IS_EMPTY = "The collection is empty \n";
    public static final String NO_SUCH_ID = "The music band hasn't been updated because of isn't such id \n";
    public static final String NOT_SUCH_ELEMENTS = "There's not such elements \n";
    public static final String MUSIC_BAND_HAS_BEEN_UPDATED_SUCCESSFUL = "The music band has been updated successful \n";
    public static final String UPDATED_MISTAKE = "The music band hasn't been updated, please, try again \n";
    public static final String MUSIC_BAND_HAS_BEEN_DELETED_SUCCESSFUL = "The music band has been deleted successful \n";
    public static final String MUSIC_BAND_HAS_BEEN_DELETED_SUCCESSFUL_ID = "The music band has been deleted successful, id = ";
    public static final String DELETED_MISTAKE = "The music band hasn't been deleted, please, try again \n";
    public static final String DELETED_MISTAKE_ID = "The music band hasn't been deleted, please, try again, id = ";
    public static final String COLLECTION_HAS_BEEN_DELETED = "The music bands have been deleted successful \n";
    public static final String SALES_BIGGER_THAN_MIN_ELEMENT = "The sales of the entered of the best album should be less than the min sales \n";
    public static final String CANNOT_ADD_ELEM_IN_EMPTY_COLLECTION = "You can't add the band with min of the sales until" +
            " the collection is empty \n";
    public static final String ALL_ELEMENT_BIGGER = "The all music bands has sales bigger than the entered \n";

    public static final String LIST_OF_AVAILABLE_COMMAND = "The list of available command: \n" +
            "- help: show the list of available commands \n" +
            "- info: show the information about music bands \n" +
            "- show: show everything music bands \n" +
            "- add: add a music band \n" +
            "- update <id> : update the music band by its id \n" +
            "- remove <id> : remove the music band by its id \n" +
            "- clear: delete the all music bands \n" +
            "- exit: stop the program \n " +
            "- add_if_min: add a music band if its sales less than the min sales of the collection  \n" +
            "- remove_lower: delete the all music bands which have the sales less than will entered \n" +
            "- history: show last 12 commands \n " +
            "- min_by_best_album: show any music band who have min sales of the best album \n" +
            "- filter_by_best_album: show the all music bands who have the sales of the best album equals will entered \n" +
            "- print_field_asc_best_album: show the all music bands in ascending order of sales of the best album \n";

}
