package validator;

import music.MusicGenre;

import static messages.ValidationMessages.*;

public class ConsoleValidator {
    private final char CHAR_DEC_33 = '!'; // DEC = 33 in the ASCII
    private final char CHAR_DEC_45 = '-'; // DEC = 45 in the ASCII
    private final char CHAR_DEC_47 = '/'; // DEC = 47 in the ASCII
    private final char CHAR_DEC_58 = ':'; // DEC = 58 in the ASCII
    private final char CHAR_DEC_64 = '@'; // DEC = 64 in the ASCII
    private final char CHAR_DEC_91 = '['; // DEC = 91 in the ASCII
    private final char CHAR_DEC_95 = '_'; // DEC = 95 in the ASCII
    private final char CHAR_DEC_96 = '`'; // DEC = 96 in the ASCII
    private final char CHAR_DEC_123 = '{'; // DEC = 123 in the ASCII
    private final char CHAR_DEC_126 = '~'; // DEC = 126 in the ASCII
    private final int REQUIRED_LOGIN_LENGTH = 6;
    private final int REQUIRED_PASSWORD_LENGTH = 6;
    private final int MAX_LOGIN_LENGTH = 32;
    private final int MAX_PASSWORD_LENGTH = 32;

    public ValidationResult isCorrectPort(String port) {
        if (!isInteger(port) || Integer.parseInt(port) < 0 || Integer.parseInt(port) > 65535) {
            return new ValidationResult(PORT_MUST_BE);
        }
        return ValidationResult.OK;
    }

    public ValidationResult isCorrectLogin(boolean isRegistration, String login) {
        if (isEmpty(login)) {
            return new ValidationResult(YOU_HAVE_NOT_ENTERED_LOGIN);
        }
        if (login.length() > MAX_LOGIN_LENGTH) {
            return new ValidationResult(String.format(LOGIN_MUST_BE_LESS_THAN, MAX_LOGIN_LENGTH));
        }
        if (!isRegistration) {
            return ValidationResult.OK;
        }
        if (login.length() < REQUIRED_LOGIN_LENGTH || hasForbiddenCharacterForLogin(login)) {
            return new ValidationResult(String.format(LOGIN_MUST_CONTAIN, REQUIRED_LOGIN_LENGTH));
        }
        if (hasLowerCaseLetter(login) || hasUpperCaseLetter(login) || hasDigit(login)) {
            return ValidationResult.OK;
        }
        return new ValidationResult(String.format(LOGIN_MUST_CONTAIN, REQUIRED_LOGIN_LENGTH));
    }

    public ValidationResult isCorrectPass(boolean isRegistration, String pass) {
        if (isEmpty(pass)) {
            return new ValidationResult(YOU_HAVE_NOT_ENTERED_PASS);
        }
        if (pass.length() > MAX_PASSWORD_LENGTH) {
            return new ValidationResult(String.format(PASS_MUST_BE_LESS_THAN, MAX_PASSWORD_LENGTH));
        }
        if (isRegistration) {
            if (pass.length() >= REQUIRED_PASSWORD_LENGTH
                    && hasDigit(pass)
                    && hasLowerCaseLetter(pass) || hasUpperCaseLetter(pass)) {
                return ValidationResult.OK;
            } else {
                return new ValidationResult(String.format(PASS_MUST_CONTAIN, REQUIRED_PASSWORD_LENGTH));
            }
        }
        return ValidationResult.OK;
    }

    private boolean hasForbiddenCharacterForLogin(String login) {
        char[] chars = login.toCharArray();
        for (char aChar : chars) {
            if (aChar >= CHAR_DEC_33 && aChar < CHAR_DEC_45
                    || aChar > CHAR_DEC_45 && aChar <= CHAR_DEC_47
                    || aChar >= CHAR_DEC_58 && aChar <= CHAR_DEC_64
                    || aChar >= CHAR_DEC_91 && aChar < CHAR_DEC_95
                    || aChar == CHAR_DEC_96
                    || aChar >= CHAR_DEC_123 && aChar <= CHAR_DEC_126) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDigit(String string) {
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            if (aChar >= '0' && aChar <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean hasLowerCaseLetter(String string) {
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            if (aChar >= 'a' && aChar <= 'z') {
                return true;
            }
        }
        return false;
    }

    private boolean hasUpperCaseLetter(String string) {
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            if (aChar >= 'A' && aChar <= 'Z') {
                return true;
            }
        }
        return false;
    }

    public ValidationResult isCorrectName(String name) {
        if (isEmpty(name)) {
            return new ValidationResult(NAME_IS_EMPTY);
        }
        if (name.length() > 50) {
            return new ValidationResult(NAME_IS_MORE_THAN_MAX);
        }
        return ValidationResult.OK;
    }

    public ValidationResult isCorrectNumberOfParticipants(String numberOfParticipants) {
        if (isEmpty(numberOfParticipants)) {
            return new ValidationResult(NUMBER_OF_PARTICIPANTS_IS_NULL);
        }
        if (!isInteger(numberOfParticipants) || Integer.parseInt(numberOfParticipants) <= 0 || Integer.parseInt(numberOfParticipants) > 1000) {
            return new ValidationResult(NUMBER_OF_PARTICIPANTS_SHOULD_BE);
        }
        return ValidationResult.OK;
    }

    public ValidationResult isCorrectGenre(String genre) {
        if (isEmpty(genre)) {
            return new ValidationResult(MUSIC_GENRE_MESSAGE);
        }
        if (isInteger(genre)) {
            return new ValidationResult(MUSIC_GENRE_MESSAGE);
        }
        for (MusicGenre musicGenre : MusicGenre.values()) {
            if (genre.equalsIgnoreCase(musicGenre.name())) {
                return ValidationResult.OK;
            }
        }
        return new ValidationResult(MUSIC_GENRE_MESSAGE);
    }

    public ValidationResult isCorrectNameBestAlbum(String nameBestAlbum) {
        if (isEmpty(nameBestAlbum)) {
            return new ValidationResult(BEST_ALBUM_IS_EMPTY);
        }
        if (nameBestAlbum.length() > 50) {
            return new ValidationResult(BEST_ALBUM_MORE_THAN_MAX);
        }
        return ValidationResult.OK;
    }

    public ValidationResult isCorrectSalesBestAlbum(String salesBestAlbum) {
        if (isEmpty(salesBestAlbum)) {
            return new ValidationResult(BEST_ALBUM_SALES_IS_NULL);
        }
        if (!isLong(salesBestAlbum) || Long.parseLong(salesBestAlbum) <= 0) {
            return new ValidationResult(BEST_ALBUM_SALES_SHOULD_BE);
        }
        return ValidationResult.OK;
    }

    public ValidationResult isCorrectArg(String arg) {
        if (isEmpty(arg)) {
            return new ValidationResult(ARG_IS_EMPTY);
        }
        if (!isLong(arg) || Long.parseLong(arg) <= 0) {
            return new ValidationResult(ARG_SHOULD_BE);
        }
        return ValidationResult.OK;
    }

    public ValidationResult isCorrectPath(String path) {
        if (isEmpty(path)) {
            return new ValidationResult(PATH_IS_EMPTY);
        }
        return ValidationResult.OK;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isBlank();
    }

    private boolean isLong(String stringInputData) {
        try {
            Long.parseLong(stringInputData);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isInteger(String stringInputData) {
        try {
            Integer.parseInt(stringInputData);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
