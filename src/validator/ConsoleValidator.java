package validator;

import music.MusicGenre;

import static messages.ValidationMessages.*;

public class ConsoleValidator {

    public ValidationResult isCorrectName(String name) {
        if (isEmpty(name)) {
            return new ValidationResult(NAME_IS_EMPTY);
        }
        if (name.length() > 50) {
            return new ValidationResult(NAME_IS_MORE_THAN_MAX);
        }
        return new ValidationResult(null);
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
        return new ValidationResult(null);
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
                return new ValidationResult(null);
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
        return new ValidationResult(null);
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
        return new ValidationResult(null);
    }

    public ValidationResult isCorrectArg(String arg) {
        if (isEmpty(arg)) {
            return new ValidationResult(ARG_IS_EMPTY);
        }
        if (!isInteger(arg)) {
            return new ValidationResult(ARG_IS_NUMBER);
        }
        if (Integer.parseInt(arg) <= 0) {
            return new ValidationResult(ARG_IS_LESS_THAN_ONE);
        }
        return new ValidationResult(null);
    }

    public ValidationResult isCorrectPath(String path) {
        if (isEmpty(path)) {
            return new ValidationResult(PATH_IS_EMPTY);
        }
        return new ValidationResult(null);
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
