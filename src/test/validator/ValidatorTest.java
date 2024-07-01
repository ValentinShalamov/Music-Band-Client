package test.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidatorTest {
    Validator validator = new Validator();

    /*
    Test isCorrectName() method
     */

    @ParameterizedTest
    @ValueSource(strings = {"", "         "})
    void shouldFalseIfNameIsEmpty(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectName().isCorrectData());
    }

    @Test
    void shouldFalseIfNameIsNull() {
        validator.setStringInputData(null);
        Assertions.assertFalse(validator.isCorrectName().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {Integer.MIN_VALUE + "", "-9999999", "-00100000", "-10", "0", "010",
            "114", "8945", "147850", Integer.MAX_VALUE + "", "2.21", "-0.0", "0.0", "-001231.321", "03331.321"})
    void shouldFalseIfNameIsIntegerOrDouble(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectName().isCorrectData());
    }

    @Test
    void shouldFalseIfNameLengthIsThanFifty() {
        validator.setStringInputData("100000000000000000000000000000000000000000000000000000000000gggggggfffff");
        Assertions.assertFalse(validator.isCorrectName().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"aAZasdf:;2", "Bro23'/.", ";;leonl;';", "kdazZZA2;.", "ZASD;;.", "     /dw/'sasdwazx"})
    void shouldTrueIfNameIsStringWithSymbols(String str) {
        validator.setStringInputData(str);
        Assertions.assertTrue(validator.isCorrectName().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"'']']'/'/'/'[[", "/**%#$@", "^^^^^#@!", "&()$*(@)$", "!@#%$^&*()_*//-", "#"})
    void shouldTrueIfNameIsOnlySymbols(String str) {
        validator.setStringInputData(str);
        Assertions.assertTrue(validator.isCorrectName().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Zikker", "Tops", "Bisquits", "V4 cover"})
    void shouldTrueIfNameWithoutSymbols(String str) {
        validator.setStringInputData(str);
        Assertions.assertTrue(validator.isCorrectName().isCorrectData());
    }

    /*
    Test isCorrectNumberOfParticipants() method
     */

    @Test
    void shouldFalseIfNumberOfParticipantsIsNull() {
        validator.setStringInputData(null);
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "         "})
    void shouldFalseIfNumberOfParticipantsIsEmpty(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Tood", "Eqwexc231", "..;dkk-31", "@#$#@zfew", "ASDKLMKLDAS", "@$E3333", "ddssssss dssa asd"})
    void shouldFalseIfNumberOfParticipantsIsString(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-123.12312", "-100.0000", "-01.21311", "-0.0", "0.0", "01.22134", "00312341.23334555112235"})
    void shouldFalseIfNumberOfParticipantsIsDouble(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1100", "-00103", "-01", "-1", "0"})
    void shouldFalseIfNumberOfParticipantsIsLessThanOne(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1001", "01001", "15444", "004111144752"})
    void shouldFalseIfNumberOfParticipantsIsMoreThanOneThousand(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "23", "0193", "000356", "777", "999", "1000"})
    void shouldTrueIfNumberOfParticipantsBetweenOneAndOneThousand(String str) {
        validator.setStringInputData(str);
        Assertions.assertTrue(validator.isCorrectNumberOfParticipants().isCorrectData());
    }

    /*
    Test isCorrectGenre() method
    */

    @ParameterizedTest
    @ValueSource(strings = {"", "         "})
    void shouldFalseIfGenreIsEmpty(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectGenre().isCorrectData());
    }

    @Test
    void shouldFalseIfGenreIsNull() {
        validator.setStringInputData(null);
        Assertions.assertFalse(validator.isCorrectGenre().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-3334", "-231", "-2", "0", "12", "245",
            "-231.345", "-032.321456", "-21.9", "-1.0", "0.0", "1.4", "0124.564", "4411.3"})
    void shouldFalseIfGenreIsIntegerOrDouble(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectGenre().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"aka", "ee3_33e_ees"})
    void shouldFalseIfStringIsMismatchWithGenre(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectGenre().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"PSYCHEDELIC_CLOUD_RAP", "JAZZ", "BLUES", "MATH_ROCK", "BRIT_POP",
    "psychedelic_cloud_rap", "JaZZ", "Blues", "math_ROCK", "BRIt_POp"})
    void shouldTrueIfStringIsMatchWithGenre(String str) {
        validator.setStringInputData(str);
        Assertions.assertTrue(validator.isCorrectGenre().isCorrectData());
    }

    /*
    Test isCorrectNameBestAlbum() method
    */

    @ParameterizedTest
    @ValueSource(strings = {"", "         "})
    void shouldFalseIfNameBestAlbumIsEmpty(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectNameBestAlbum().isCorrectData());
    }

    @Test
    void shouldFalseIfNameBestAlbumIsNull() {
        validator.setStringInputData(null);
        Assertions.assertFalse(validator.isCorrectNameBestAlbum().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-3334", "-231", "-2", "0", "12", "245",
            "-231.345", "-032.321456", "-21.9", "-1.0", "0.0", "1.4", "0124.564", "4411.3"})
    void shouldFalseIfNameBestAlbumIsIntegerOrDouble(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectNameBestAlbum().isCorrectData());
    }

    @Test
    void shouldFalseIfNameBestAlbumLengthMoreThanFifty() {
        validator.setStringInputData("100000000000000000000000000000000000000000000000000000000000gggggggfffff");
        Assertions.assertFalse(validator.isCorrectNameBestAlbum().isCorrectData());

    }

    @ParameterizedTest
    @ValueSource(strings = {"aAZasdf:;2", "Bro23'/.", ";;leonl;';", "kdazZZA2;.", "ZASD;;.", "     /dw/'sasdwazx, ",
            "'']']'/'/'/'[[", "/**%#$@", "^^^^^#@!", "&()$*(@)$", "!@#%$^&*()_*//-", "#",
            "Zikker", "Tops", "Bisquits", "V4 cover"})
    void shouldTrueIfNameBestAlbumHasSymbolsAndLetters(String str) {
        validator.setStringInputData(str);
        Assertions.assertTrue(validator.isCorrectNameBestAlbum().isCorrectData());
    }

    /*
    Test isCorrectSalesBestAlbum() method
    */

    @ParameterizedTest
    @ValueSource(strings = {"", "         "})
    void shouldFalseIfSalesBestAlbumIsEmpty(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectSalesBestAlbum().isCorrectData());
    }

    @Test
    void shouldFalseIfSalesBestAlbumIsNull() {
        validator.setStringInputData(null);
        Assertions.assertFalse(validator.isCorrectSalesBestAlbum().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Tood", "Eqwexc231", "..;dkk-31", "@#$#@zfew", "ASDKLMKLDAS", "@$E3333", "ddssssss dssa asd"})
    void shouldFalseIfSalesBestAlbumIsString(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectSalesBestAlbum().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-231.345", "-032.321456", "-21.9", "-1.0", "0.0", "1.4", "0124.564", "4411.3"})
    void shouldFalseIfSalesBestAlbumIsDouble(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectSalesBestAlbum().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {Long.MIN_VALUE + "","-223141", "-2134", "-100", "-1", "0"})
    void shouldFalseIfSalesBestAlbumIsLessThanOne(String str) {
        validator.setStringInputData(str);
        Assertions.assertFalse(validator.isCorrectSalesBestAlbum().isCorrectData());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "114", "4002", "0334115", "21212444", Long.MAX_VALUE + ""})
    void shouldTrueIfSalesBestAlbumIsPositiveInteger(String str) {
       validator.setStringInputData(str);
       Assertions.assertTrue(validator.isCorrectSalesBestAlbum().isCorrectData());
    }
}
