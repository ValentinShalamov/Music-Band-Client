package script;

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
            throw new MusicBandParsingException(validationResult.errorMessage());
        }
    }

    private int parseNumberOfParticipants(String numberOfParticipants) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectNumberOfParticipants(numberOfParticipants);
        if (validationResult.isValid()) {
            return Integer.parseInt(numberOfParticipants);
        } else {
            throw new MusicBandParsingException(validationResult.errorMessage());
        }
    }

    private MusicGenre parseMusicGenre(String genre) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectGenre(genre);
        if (validationResult.isValid()) {
            return MusicGenre.valueOf(genre.toUpperCase());
        } else {
            throw new MusicBandParsingException(validationResult.errorMessage());
        }
    }

    public BestAlbum parseBestAlbum(Scanner fileScanner) throws RuntimeException {
        String name = fileScanner.nextLine();
        String sales = fileScanner.nextLine();
        ValidationResult validationResultName = new ConsoleValidator().isCorrectNameBestAlbum(name);
        ValidationResult validationResultSales = new ConsoleValidator().isCorrectSalesBestAlbum(sales);
        if (validationResultName.isValid() && validationResultSales.isValid()) {
            return new BestAlbum(name, Long.parseLong(sales));
        } else if (!validationResultName.isValid()) {
            throw new MusicBandParsingException(validationResultName.errorMessage());
        } else {
            throw new MusicBandParsingException(validationResultSales.errorMessage());
        }
    }

    public void parseId(String id) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectArg(id);
        if (!validationResult.isValid()) {
            throw new MusicBandParsingException(validationResult.errorMessage());
        }
    }

    public void parseSales(String sales) throws RuntimeException {
        ValidationResult validationResult = new ConsoleValidator().isCorrectSalesBestAlbum(sales);
        if (!validationResult.isValid()) {
            throw new MusicBandParsingException(validationResult.errorMessage());
        }
    }
}
