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
    private ConsoleValidator consoleValidator;
    private Scanner console;

    public ConsoleReader() {
        this.console = new Scanner(System.in);
        this.consoleValidator = new ConsoleValidator();
    }

    public String readRequest() {
        return console.nextLine().toLowerCase().trim();
    }

    public MusicBand createBand() {
        String bandName = readName();
        int numberOfParticipants = readNumberOfParticipants();
        MusicGenre genre = readMusicGenre();
        BestAlbum bestAlbum = readBestAlbum();
        return new MusicBandBuilder().setName(bandName).setNumberOfParticipants(numberOfParticipants).
                setGenre(genre).setBestAlbum(bestAlbum).build();
    }

    private String readName() {
        ValidationResult validationResult = new ValidationResult("");
        String name = "";
        while (!validationResult.isValid()) {
            showMessage(ENTER_THE_NAME);
            name = console.nextLine().trim();
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
            numberOfParticipants = console.nextLine().trim();
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
            musicGenre = console.nextLine().toUpperCase().trim();
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
            name = console.nextLine().trim();
            validationResult = consoleValidator.isCorrectNameBestAlbum(name);
            printIfNotCorrect(validationResult.isValid(), validationResult.getErrorMessage());
        }

        validationResult = new ValidationResult("");
        while (!validationResult.isValid()) {
            showMessage(ENTER_THE_SALES_OF_THE_BEST_ALBUM);
            sales = console.nextLine().trim();
            validationResult = consoleValidator.isCorrectSalesBestAlbum(sales);
            printIfNotCorrect(validationResult.isValid(), validationResult.getErrorMessage());
        }
        return new BestAlbum(name, Long.parseLong(sales));
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
