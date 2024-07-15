package file;

import builder.MusicBandBuilder;
import music.BestAlbum;
import music.MusicBand;
import music.MusicGenre;
import validator.ConsoleValidator;
import validator.ValidationResult;

import java.util.Scanner;

public class FileReader {

    public MusicBand createBand(Scanner fileScanner) throws RuntimeException {
        return new MusicBandBuilder()
                .name(readName(fileScanner.nextLine()))
                .numberOfParticipants(readNumberOfParticipants(fileScanner.nextLine()))
                .genre(readMusicGenre(fileScanner.nextLine()))
                .bestAlbum(readBestAlbum(fileScanner))
                .build();
    }

    private String readName(String name) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectName(name);
        if (validationResult.isValid()) {
            return name;
        } else {
            throw new RuntimeException(validationResult.getErrorMessage());
        }
    }

    private int readNumberOfParticipants(String numberOfParticipants) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectNumberOfParticipants(numberOfParticipants);
        if (validationResult.isValid()) {
            return Integer.parseInt(numberOfParticipants);
        } else {
            throw new RuntimeException(validationResult.getErrorMessage());
        }
    }

    private MusicGenre readMusicGenre(String genre) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectGenre(genre);
        if (validationResult.isValid()) {
            return MusicGenre.valueOf(genre.toUpperCase());
        } else {
            throw new RuntimeException(validationResult.getErrorMessage());
        }
    }

    public BestAlbum readBestAlbum(Scanner fileScanner) throws RuntimeException {
        String name = fileScanner.nextLine();
        String sales = fileScanner.nextLine();
        ValidationResult validationResultName = new ConsoleValidator().isCorrectNameBestAlbum(name);
        ValidationResult validationResultSales = new ConsoleValidator().isCorrectSalesBestAlbum(sales);
        if (validationResultName.isValid() && validationResultSales.isValid()) {
            return new BestAlbum(name, Long.parseLong(sales));
        } else {
            throw new RuntimeException(validationResultName.getErrorMessage() + validationResultSales.getErrorMessage());
        }
    }

    public String readPath(String path) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectPath(path);
        if (validationResult.isValid()) {
            return path;
        } else {
            throw new RuntimeException(validationResult.getErrorMessage());
        }
    }

    public long readId(String id) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectArg(id);
        if (validationResult.isValid()) {
            return Long.parseLong(id);
        } else {
            throw new RuntimeException(validationResult.getErrorMessage());
        }
    }
}
