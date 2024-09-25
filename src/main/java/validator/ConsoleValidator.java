package validator;

import music.MusicGenre;

import static messages.ValidationMessages.*;

public class ConsoleValidator {

    public ValidationResult isCorrectLogin(String login) {
        if (isEmpty(login)) {
            return new ValidationResult(LOGIN_IS_EMPTY);
        }
        if (login.length() > 30) {
            return new ValidationResult(LOGIN_IS_MORE_THAN_MAX);
        }
        if (hasOneSymbol(login)) {
            return new ValidationResult(LOGIN_CONTAINS_SYMBOL);
        }
        return ValidationResult.OK;
    }

    public ValidationResult isCorrectPass(String pass) {
        if (isEmpty(pass)) {
            return new ValidationResult(PASS_IS_EMPTY);
        }
        if (hasOneSymbol(pass) && hasOneInteger(pass) && hasOneLetterLowerCase(pass) && hasOneLetterUpperCase(pass)) {
            return ValidationResult.OK;
        }
        return new ValidationResult(PASS_MESSAGE);
    }

    private boolean hasOneSymbol(String string) {
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            if (aChar >= '!' && aChar <= '/'
                    || aChar >= ':' && aChar <= '@'
                    || aChar >= '[' && aChar <= '`'
                    || aChar >= '{' && aChar <= '~') {
                return true;
            }
        }
        return false;
    }

    private boolean hasOneInteger(String string) {
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            if (aChar >= '0' && aChar <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean hasOneLetterLowerCase(String string) {
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            if (aChar >= 'a' && aChar <= 'z') {
                return true;
            }
        }
        return false;
    }

    private boolean hasOneLetterUpperCase(String string) {
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
        if (!isInteger(numberOfParticipants)) {
            return new ValidationResult(NUMBER_OF_PARTICIPANTS_IS_NOT_INTEGER);
        }
        if (Integer.parseInt(numberOfParticipants) <= 0) {
            return new ValidationResult(NUMBER_OF_PARTICIPANTS_LESS_THAN_ONE);
        }
        if (Integer.parseInt(numberOfParticipants) > 1000) {
            return new ValidationResult(NUMBER_OF_PARTICIPANTS_MORE_THAN_MAX);
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
        if (!isLong(salesBestAlbum)) {
            return new ValidationResult(BEST_ALBUM_SALES_IS_NOT_LONG);
        }
        if (Long.parseLong(salesBestAlbum) <= 0) {
            return new ValidationResult(BEST_ALBUM_SALES_LESS_THAN_ONE);
        }
        return ValidationResult.OK;
    }

    public ValidationResult isCorrectArg(String arg) {
        if (isEmpty(arg)) {
            return new ValidationResult(ARG_IS_EMPTY);
        }
        if (!isLong(arg)) {
            return new ValidationResult(ARG_IS_NOT_CORRECT_NUMBER);
        }
        if (Long.parseLong(arg) <= 0) {
            return new ValidationResult(ARG_IS_LESS_THAN_ONE);
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
