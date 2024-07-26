package file;

import builder.MusicBandBuilder;
import exceptions.MusicBandParsingException;
import music.BestAlbum;
import music.MusicBand;
import music.MusicGenre;
import validator.ConsoleValidator;
import validator.ValidationResult;

import java.util.Scanner;

public class MusicBandFieldsParser {

    public MusicBand createBand(Scanner fileScanner) throws RuntimeException {
        return new MusicBandBuilder()
                .name(parseName(fileScanner.nextLine()))
                .numberOfParticipants(parseNumberOfParticipants(fileScanner.nextLine()))
                .genre(parseMusicGenre(fileScanner.nextLine()))
                .bestAlbum(parseBestAlbum(fileScanner))
                .build();
    }

    private String parseName(String name) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectName(name);
        if (validationResult.isValid()) {
            return name;
        } else {
            throw new MusicBandParsingException(validationResult.getErrorMessage());
        }
    }

    private int parseNumberOfParticipants(String numberOfParticipants) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectNumberOfParticipants(numberOfParticipants);
        if (validationResult.isValid()) {
            return Integer.parseInt(numberOfParticipants);
        } else {
            throw new MusicBandParsingException(validationResult.getErrorMessage());
        }
    }

    private MusicGenre parseMusicGenre(String genre) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectGenre(genre);
        if (validationResult.isValid()) {
            return MusicGenre.valueOf(genre.toUpperCase());
        } else {
            throw new MusicBandParsingException(validationResult.getErrorMessage());
        }
    }

    public BestAlbum parseBestAlbum(Scanner fileScanner) throws RuntimeException {
        String name = fileScanner.nextLine();
        String sales = fileScanner.nextLine();
        ValidationResult validationResultName = new ConsoleValidator().isCorrectNameBestAlbum(name);
        ValidationResult validationResultSales = new ConsoleValidator().isCorrectSalesBestAlbum(sales);
        if (validationResultName.isValid() && validationResultSales.isValid()) {
            return new BestAlbum(name, Long.parseLong(sales));
        } else {
            throw new MusicBandParsingException(validationResultName.getErrorMessage() + validationResultSales.getErrorMessage());
        }
    }

    public String parsePath(String path) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectPath(path);
        if (validationResult.isValid()) {
            return path;
        } else {
            throw new MusicBandParsingException(validationResult.getErrorMessage());
        }
    }

    public long parseId(String id) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectArg(id);
        if (validationResult.isValid()) {
            return Long.parseLong(id);
        } else {
            throw new MusicBandParsingException(validationResult.getErrorMessage());
        }
    }

    public long parseSales(String sales) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectSalesBestAlbum(sales);
        if (validationResult.isValid()) {
            return Long.parseLong(sales);
        } else {
            throw new MusicBandParsingException(validationResult.getErrorMessage());
        }
    }
}
