package client;

import builder.MusicBandBuilder;
import music.BestAlbum;
import music.MusicBand;
import music.MusicGenre;
import validator.ValidationResult;
import validator.Validator;

import java.util.Scanner;

import static client.MessagesForUser.*;

public class Reader {
    private Validator validator;
    private Scanner console;
    private String inputStringCommand;

    public Reader(Scanner console) {
        this.console = console;
        this.validator = new Validator();
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
        ValidationResult validationResult = new ValidationResult(false, null);
        while (!validationResult.isCorrectData()) {
            showMessage(ENTER_THE_NAME);
            inputStringCommand = console.nextLine().trim();
            validator.setStringInputData(inputStringCommand);
            validationResult = validator.isCorrectName();
            printIfNotCorrect(validationResult.isCorrectData(), validationResult.getMessage());
        }
        return inputStringCommand;
    }

    private int readNumberOfParticipants() {
        ValidationResult validationResult = new ValidationResult(false, null);
        while (!validationResult.isCorrectData()) {
            showMessage(ENTER_NUMBER_OF_PARTICIPANTS);
            inputStringCommand = console.nextLine().trim();
            validator.setStringInputData(inputStringCommand);
            validationResult = validator.isCorrectNumberOfParticipants();
            printIfNotCorrect(validationResult.isCorrectData(), validationResult.getMessage());
        }
        return Integer.parseInt(inputStringCommand);
    }

    private MusicGenre readMusicGenre() {
        ValidationResult validationResult = new ValidationResult(false, null);
        while (!validationResult.isCorrectData()) {
            showMessage(ENTER_THE_GENRE_OF_MUSIC);
            inputStringCommand = console.nextLine().toUpperCase().trim();
            validator.setStringInputData(inputStringCommand);
            validationResult = validator.isCorrectGenre();
            printIfNotCorrect(validationResult.isCorrectData(), validationResult.getMessage());
        }
        return MusicGenre.valueOf(inputStringCommand.toUpperCase());
    }

    public BestAlbum readBestAlbum() {
        String name;
        long sales;
        ValidationResult validationResult = new ValidationResult(false, null);
        while (!validationResult.isCorrectData()) {
            showMessage(ENTER_NAME_OF_THE_BEST_ALBUM);
            inputStringCommand = console.nextLine().trim();
            validator.setStringInputData(inputStringCommand);
            validationResult = validator.isCorrectNameBestAlbum();
            printIfNotCorrect(validationResult.isCorrectData(), validationResult.getMessage());
        }
        name = inputStringCommand;
        validationResult = new ValidationResult(false, null);
        while (!validationResult.isCorrectData()) {
            showMessage(ENTER_THE_SALES_OF_THE_BEST_ALBUM);
            inputStringCommand = console.nextLine().trim();
            validator.setStringInputData(inputStringCommand);
            validationResult = validator.isCorrectSalesBestAlbum();
            printIfNotCorrect(validationResult.isCorrectData(), validationResult.getMessage());
        }
        sales = Long.parseLong(inputStringCommand);
        return new BestAlbum(name, sales);
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
