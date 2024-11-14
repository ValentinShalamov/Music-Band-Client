package messages;

public class ValidationMessages {
    public static final String NAME_IS_EMPTY = "The name of the band can not be empty \n";
    public static final String NAME_IS_MORE_THAN_MAX = "The name of the band can not be more than 50 characters \n";
    public static final String NUMBER_OF_PARTICIPANTS_IS_NULL = "The number of participants can not be less than one \n";
    public static final String NUMBER_OF_PARTICIPANTS_SHOULD_BE = "The number of participants can be from 1 to 1000 \n";

    public static final String MUSIC_GENRE_MESSAGE = "The music genre should be one of the following : \n" +
            "    PSYCHEDELIC_CLOUD_RAP,\n" +
            "    JAZZ,\n" +
            "    BLUES,\n" +
            "    MATH_ROCK,\n" +
            "    BRIT_POP \n";
    public static final String BEST_ALBUM_IS_EMPTY = "The name of the best album can not be empty \n";
    public static final String BEST_ALBUM_MORE_THAN_MAX = "The name of the best album can not be more than 50 characters \n";
    public static final String BEST_ALBUM_SALES_IS_NULL = "The sales of the best album can not be less than one \n";
    public static final String BEST_ALBUM_SALES_SHOULD_BE = "The number of sales of the best album must be higher than one \n";
    public static final String ARG_IS_EMPTY = "The argument of the command can not be empty \n";
    public static final String ARG_SHOULD_BE = "The argument of the command must be a higher than one \n";
    public static final String PATH_IS_EMPTY = "The path to file can not be empty \n";

    public static final String YOU_HAVE_NOT_ENTERED_LOGIN = "You have not entered a login";
    public static final String YOU_HAVE_NOT_ENTERED_PASS = "You have not entered a password";
    public static final String LOGIN_OR_PASS_LESS_32_CHAR = "The login or password must be less than 32 characters";
    public static final String LOGIN_MUST_CONTAIN = "The login must contain at least 6 least 6 characters long. The login can contain Latin letters, numbers or '-' and '_' characters";
    public static final String PASS_MUST_CONTAIN = "The password must contain at least one Latin letter, one number and be at least 6 characters long";

    public static final String PORT_MUST_BE = "The port must be a number between 0 and 65535";
}
