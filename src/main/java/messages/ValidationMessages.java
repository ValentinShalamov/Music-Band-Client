package messages;

public class ValidationMessages {
    public static final String NAME_IS_EMPTY = "The name of the band can not be empty \n";
    public static final String NAME_IS_MORE_THAN_MAX = "The name of band can't be more than 50 symbols \n";
    public static final String NUMBER_OF_PARTICIPANTS_IS_NULL = "The number of participants can't be less than one \n";
    public static final String NUMBER_OF_PARTICIPANTS_IS_NOT_INTEGER = "The number of participants should be a number more than zero \n";
    public static final String NUMBER_OF_PARTICIPANTS_LESS_THAN_ONE = "The number of participants should be a number more than zero \n";
    public static final String NUMBER_OF_PARTICIPANTS_MORE_THAN_MAX = "The number of participants can't be more than 1000 \n";
    public static final String MUSIC_GENRE_MESSAGE = "The music genre should be one of the following : \n" +
            "    PSYCHEDELIC_CLOUD_RAP,\n" +
            "    JAZZ,\n" +
            "    BLUES,\n" +
            "    MATH_ROCK,\n" +
            "    BRIT_POP \n";
    public static final String BEST_ALBUM_IS_EMPTY = "The name of the best album can't be empty \n";
    public static final String BEST_ALBUM_MORE_THAN_MAX = "The name of the best album can't be more than 50 symbols \n";
    public static final String BEST_ALBUM_SALES_IS_NULL = "The sales of the best album can't be less than one \n";
    public static final String BEST_ALBUM_SALES_IS_NOT_LONG = "The sales of the best album should be more than zero \n";
    public static final String BEST_ALBUM_SALES_LESS_THAN_ONE = "The sales of the best album should be more than zero \n";
    public static final String ARG_IS_EMPTY = "The argument of the command can't be empty \n";
    public static final String ARG_IS_NOT_CORRECT_NUMBER = "The argument of the command should be a number and can't be too much number \n";
    public static final String ARG_IS_LESS_THAN_ONE = "The argument of the command should be more than zero \n";
    public static final String PATH_IS_EMPTY = "The path to file can't be empty \n";

    public static final String LOGIN_IS_EMPTY = "The login can not be empty \n";
    public static final String LOGIN_IS_MORE_THAN_MAX = "The login must be less than 30 symbols";
    public static final String LOGIN_CONTAINS_SYMBOL = "The login must not have any symbols \n";
    public static final String PASS_IS_EMPTY = "The pass can not be empty \n";
    public static final String PASS_MESSAGE = "The pass must have the least one symbol, one lower case letter, one upper case letter and one digit \n";

}
