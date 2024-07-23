package client;

import builder.MusicBandBuilder;
import exceptions.UserCancelledOperationException;
import music.BestAlbum;
import music.MusicBand;
import music.MusicGenre;
import validator.ConsoleValidator;
import validator.ValidationResult;

import java.util.Scanner;

import static messages.UserMessages.*;
import static messages.ResultMessages.*;

public class ConsoleReader {
    private final ConsoleValidator consoleValidator;
    private final Scanner consoleScanner;

    public ConsoleReader() {
        this.consoleScanner = new Scanner(System.in);
        this.consoleValidator = new ConsoleValidator();
    }

    public String readRequest() {
        if (!consoleScanner.hasNextLine()) {
            return "exit";
        }
        String string = consoleScanner.nextLine().trim();
        if (!string.equalsIgnoreCase("q")) {
            return string;
        } else {
            throw new UserCancelledOperationException(INTERRUPT_MESSAGE);
        }
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
            name = readRequest();
            validationResult = consoleValidator.isCorrectName(name);
            printIfNotCorrect(validationResult);
        }
        return name;
    }

    private int readNumberOfParticipants() {
        ValidationResult validationResult = new ValidationResult("");
        String numberOfParticipants = "";
        while (!validationResult.isValid()) {
            showMessage(ENTER_NUMBER_OF_PARTICIPANTS);
            numberOfParticipants = readRequest();
            validationResult = consoleValidator.isCorrectNumberOfParticipants(numberOfParticipants);
            printIfNotCorrect(validationResult);
        }
        return Integer.parseInt(numberOfParticipants);
    }

    private MusicGenre readMusicGenre() {
        ValidationResult validationResult = new ValidationResult("");
        String musicGenre = "";
        while (!validationResult.isValid()) {
            showMessage(ENTER_THE_GENRE_OF_MUSIC);
            musicGenre = readRequest().toUpperCase();
            validationResult = consoleValidator.isCorrectGenre(musicGenre);
            printIfNotCorrect(validationResult);
        }
        return MusicGenre.valueOf(musicGenre.toUpperCase());
    }

    public BestAlbum readBestAlbum() {
        return new BestAlbum(readNameBestAlbum(), readSales());
    }

    public String readNameBestAlbum() {
        ValidationResult validationResult = new ValidationResult("");
        String name = "";
        while (!validationResult.isValid()) {
            showMessage(ENTER_NAME_OF_THE_BEST_ALBUM);
            name = readRequest();
            validationResult = consoleValidator.isCorrectNameBestAlbum(name);
            printIfNotCorrect(validationResult);
        }
        return name;
    }

    public long readSales() {
        ValidationResult validationResult = new ValidationResult("");
        String sales = "";
        while (!validationResult.isValid()) {
            showMessage(ENTER_THE_SALES_OF_THE_BEST_ALBUM);
            sales = readRequest();
            validationResult = consoleValidator.isCorrectSalesBestAlbum(sales);
            printIfNotCorrect(validationResult);
        }
        return Long.parseLong(sales);
    }

    public String readPath() {
        ValidationResult validationResult = new ValidationResult("");
        String path = "";
        while (!validationResult.isValid()) {
            showMessage(ENTER_PATH);
            path = readRequest();
            validationResult = consoleValidator.isCorrectPath(path);
            printIfNotCorrect(validationResult);
        }
        return path;
    }

    private void showMessage(String message) {
        System.out.println(message);
    }

    private void printIfNotCorrect(ValidationResult validationResult) {
        if (!validationResult.isValid()) {
            showMessage(validationResult.getErrorMessage());
        }
    }

}
