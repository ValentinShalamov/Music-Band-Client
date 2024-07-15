package client;

import builder.MusicBandBuilder;
import music.BestAlbum;
import music.MusicBand;
import music.MusicGenre;
import validator.ConsoleValidator;
import validator.ValidationResult;

import java.util.Scanner;

import static messages.UserMessages.*;

public class ConsoleReader {
    private final ConsoleValidator consoleValidator;
    private final Scanner consoleScanner;

    public ConsoleReader() {
        this.consoleScanner = new Scanner(System.in);
        this.consoleValidator = new ConsoleValidator();
    }

    public String readRequest() {
        return consoleScanner.nextLine().toLowerCase().trim();
    }

    public MusicBand createBand() {
        String bandName = readName();
        int numberOfParticipants = readNumberOfParticipants();
        MusicGenre genre = readMusicGenre();
        BestAlbum bestAlbum = readBestAlbum();
        return new MusicBandBuilder().name(bandName).numberOfParticipants(numberOfParticipants).
                genre(genre).bestAlbum(bestAlbum).build();
    }

    private String readName() {
        ValidationResult validationResult = new ValidationResult("");
        String name = "";
        while (!validationResult.isValid()) {
            showMessage(ENTER_THE_NAME);
            name = consoleScanner.nextLine().trim();
            validationResult = consoleValidator.isCorrectName(name);
            printIfNotCorrect(validationResult.isValid(), validationResult.getErrorMessage());
        }
        return name;
    }

    private int readNumberOfParticipants() {
        ValidationResult validationResult = new ValidationResult("");
        String numberOfParticipants = "";
        while (!validationResult.isValid()) {
            showMessage(ENTER_NUMBER_OF_PARTICIPANTS);
            numberOfParticipants = consoleScanner.nextLine().trim();
            validationResult = consoleValidator.isCorrectNumberOfParticipants(numberOfParticipants);
            printIfNotCorrect(validationResult.isValid(), validationResult.getErrorMessage());
        }
        return Integer.parseInt(numberOfParticipants);
    }

    private MusicGenre readMusicGenre() {
        ValidationResult validationResult = new ValidationResult("");
        String musicGenre = "";
        while (!validationResult.isValid()) {
            showMessage(ENTER_THE_GENRE_OF_MUSIC);
            musicGenre = consoleScanner.nextLine().toUpperCase().trim();
            validationResult = consoleValidator.isCorrectGenre(musicGenre);
            printIfNotCorrect(validationResult.isValid(), validationResult.getErrorMessage());
        }
        return MusicGenre.valueOf(musicGenre.toUpperCase());
    }

    public BestAlbum readBestAlbum() {
        ValidationResult validationResult = new ValidationResult("");
        String name = "";
        String sales = "";
        while (!validationResult.isValid()) {
            showMessage(ENTER_NAME_OF_THE_BEST_ALBUM);
            name = consoleScanner.nextLine().trim();
            validationResult = consoleValidator.isCorrectNameBestAlbum(name);
            printIfNotCorrect(validationResult.isValid(), validationResult.getErrorMessage());
        }

        validationResult = new ValidationResult("");
        while (!validationResult.isValid()) {
            showMessage(ENTER_THE_SALES_OF_THE_BEST_ALBUM);
            sales = consoleScanner.nextLine().trim();
            validationResult = consoleValidator.isCorrectSalesBestAlbum(sales);
            printIfNotCorrect(validationResult.isValid(), validationResult.getErrorMessage());
        }
        return new BestAlbum(name, Long.parseLong(sales));
    }

    public String readPath() {
        ValidationResult validationResult = new ValidationResult("");
        String path = "";
        while (!validationResult.isValid()) {
            showMessage(ENTER_PATH);
            path = consoleScanner.nextLine().trim();
            validationResult = consoleValidator.isCorrectPath(path);
            printIfNotCorrect(validationResult.isValid(), validationResult.getErrorMessage());
        }
        return path;
    }

    private void showMessage(String message) {
        System.out.println(message);
    }

    private void printIfNotCorrect(boolean isCorrect, String message) {
        if (!isCorrect) {
            System.out.println(message);
        }
    }

}
