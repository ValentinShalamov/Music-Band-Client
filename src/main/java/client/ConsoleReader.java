package client;

import builder.MusicBandBuilder;
import command.Command;
import exceptions.UserCancelledOperationException;
import music.BestAlbum;
import music.MusicBand;
import music.MusicGenre;
import validator.ConsoleValidator;
import validator.ValidationResult;

import java.util.Scanner;

import static messages.ConnectionMessages.YOU_HAVE_SELECTED_HOST;
import static messages.ConnectionMessages.YOU_HAVE_SELECTED_PORT;
import static messages.ResultMessages.INTERRUPT_MESSAGE;
import static messages.UserMessages.*;
import static messages.ValidationMessages.LOGIN_MUST_CONTAIN;
import static messages.ValidationMessages.PASS_MUST_CONTAIN;
import static util.DefaultValues.DEFAULT_HOST;
import static util.DefaultValues.DEFAULT_PORT;

public class ConsoleReader {
    private final ConsoleValidator consoleValidator;
    private final Scanner consoleScanner;

    public ConsoleReader() {
        this.consoleScanner = new Scanner(System.in);
        this.consoleValidator = new ConsoleValidator();
    }

    public Command readAuthenticationCommand() {
        showMessage(INTERRUPT);
        while (true) {
            showMessage(STARTUP_MESSAGE);
            String request = readRequest();
            if (request.equalsIgnoreCase("register")) {
                String login = readLogin(true);
                String pass = readPass(true);
                return new Command(request.toLowerCase(), login, pass);
            }
            if (request.equalsIgnoreCase("login")) {
                String login = readLogin(false);
                String pass = readPass(false);
                return new Command(request.toLowerCase(), login, pass);
            }
        }
    }

    public String readHost() {
        showMessage(ENTER_HOST_ADDRESS);
        String host = readRequest();
        if (host.trim().isEmpty()) {
            showMessage(YOU_HAVE_SELECTED_HOST + DEFAULT_HOST + "\n");
            return DEFAULT_HOST;
        }
        showMessage(String.format(YOU_HAVE_SELECTED_HOST + "%s \n", host.trim()));
        return host;
    }

    public int readPort() {
        ValidationResult validationResult = new ValidationResult("");
        String port = "";

        while (!validationResult.isValid()) {
            showMessage(ENTER_PORT_NUMBER);
            port = readRequest();
            if (port.trim().isEmpty()) {
                showMessage(YOU_HAVE_SELECTED_PORT + DEFAULT_PORT + "\n");
                return DEFAULT_PORT;
            } else {
                validationResult = consoleValidator.isCorrectPort(port);
                printIfNotCorrect(validationResult);
            }
        }
        showMessage(String.format(YOU_HAVE_SELECTED_PORT + "%s \n", port.trim()));
        return Integer.parseInt(port);
    }

    private String readLogin(boolean isRegistration) {
        ValidationResult validationResult = new ValidationResult("");
        String login = "";
        if (isRegistration) showMessage(LOGIN_MUST_CONTAIN);
        while (!validationResult.isValid()) {
            showMessage(ENTER_LOGIN);
            login = readRequest();
            validationResult = consoleValidator.isCorrectLogin(isRegistration, login);
            printIfNotCorrect(validationResult);
        }
        return login;
    }

    private String readPass(boolean isRegistration) {
        ValidationResult validationResult = new ValidationResult("");
        String pass = "";
        if (isRegistration) showMessage(PASS_MUST_CONTAIN);
        while (!validationResult.isValid()) {
            showMessage(ENTER_PASS);
            pass = readRequest();
            validationResult = consoleValidator.isCorrectPass(isRegistration, pass);
            printIfNotCorrect(validationResult);
        }
        return pass;
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
        showMessage(ENTER_THE_GENRE_OF_MUSIC);
        while (true) {
            switch (readRequest().trim()) {
                case "1" -> {
                    return MusicGenre.PSYCHEDELIC_CLOUD_RAP;
                }
                case "2" -> {
                    return MusicGenre.JAZZ;
                }
                case "3" -> {
                    return MusicGenre.BLUES;
                }
                case "4" -> {
                    return MusicGenre.MATH_ROCK;
                }
                case "5" -> {
                    return MusicGenre.BRIT_POP;
                }
                default -> showMessage(DEFAULT_MUSIC_GENRE_MESSAGE);
            }
        }
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
            showMessage(validationResult.errorMessage());
        }
    }
}
