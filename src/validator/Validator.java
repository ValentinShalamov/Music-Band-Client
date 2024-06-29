package validator;

import music.MusicGenre;

import static validator.MessagesByMistakes.*;

public class Validator {
    public String stringInputData;

    public Validator() {
    }

    public void setStringInputData(String stringInputData) {
        this.stringInputData = stringInputData;
    }

    public ValidationResult isCorrectName() {
        if (stringInputData == null || stringInputData.isEmpty()) {
            return new ValidationResult(false, NAME_IS_EMPTY);
        }
        if (isInteger(stringInputData)) {
            return new ValidationResult(false, NAME_IS_NUMBER);
        }
        if (stringInputData.length() > 50) {
            return new ValidationResult(false, NAME_IS_MORE_THAN_MAX);
        }
        return new ValidationResult(true, null);
    }

    public ValidationResult isCorrectNumberOfParticipants() {
        if (stringInputData == null || stringInputData.isEmpty()) {
            return new ValidationResult(false, NUMBER_OF_PARTICIPANTS_IS_NULL);
        }
        if (!isInteger(stringInputData)) {
            return new ValidationResult(false, NUMBER_OF_PARTICIPANTS_IS_NOT_INTEGER);
        }
        if (Integer.parseInt(stringInputData) <= 0) {
            return new ValidationResult(false, NUMBER_OF_PARTICIPANTS_LESS_THAN_ONE);
        }
        if (Integer.parseInt(stringInputData) > 1000) {
            return new ValidationResult(false, NUMBER_OF_PARTICIPANTS_MORE_THAN_MAX);
        }
        return new ValidationResult(true, null);
    }

    public ValidationResult isCorrectGenre() {
        if (stringInputData == null || stringInputData.isEmpty()) {
            return new ValidationResult(false, MUSIC_GENRE_MESSAGE);
        }
        if (isInteger(stringInputData)) {
            return new ValidationResult(false, MUSIC_GENRE_MESSAGE);
        }
        for (MusicGenre musicGenre : MusicGenre.values()) {
            if (stringInputData.equals(musicGenre.name())) {
                return new ValidationResult(true, null);
            }
        }
        return new ValidationResult(false, MUSIC_GENRE_MESSAGE);
    }

    public ValidationResult isCorrectNameBestAlbum() {
        if (stringInputData == null || stringInputData.isEmpty()) {
            return new ValidationResult(false, BEST_ALBUM_IS_EMPTY);
        }
        if (isInteger(stringInputData)) {
            return new ValidationResult(false, BEST_ALBUM_IS_NUMBER);
        }
        if (stringInputData.length() > 50) {
            return new ValidationResult(false, BEST_ALBUM_MORE_THAN_MAX);
        }
        return new ValidationResult(true, null);
    }

    public ValidationResult isCorrectSalesBestAlbum() {
        if (stringInputData == null || stringInputData.isEmpty()) {
            return new ValidationResult(false, BEST_ALBUM_SALES_IS_NULL);
        }
        if (!isLong(stringInputData)) {
            return new ValidationResult(false, BEST_ALBUM_SALES_IS_NOT_LONG);
        }
        if (Long.parseLong(stringInputData) <= 0) {
            return new ValidationResult(false, BEST_ALBUM_SALES_LESS_THAN_ONE);
        }
        return new ValidationResult(true, null);
    }

    public ValidationResult isCorrectArg() {
        if (stringInputData == null || stringInputData.isEmpty()) {
            return new ValidationResult(false, null);
        }
        if (!isInteger(stringInputData)) {
            return new ValidationResult(false, null);
        }
        if (Integer.parseInt(stringInputData) <= 0) {
            return new ValidationResult(false, null);
        }
        return new ValidationResult(true, null);
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
