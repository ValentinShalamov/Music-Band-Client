package validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ConsoleValidatorTest {
    ConsoleValidator validator = new ConsoleValidator();

    /*
    Test isCorrectName() method
     */

    @ParameterizedTest
    @ValueSource(strings = {"", "         "})
    void shouldFalseIfNameIsEmpty(String str) {
        Assertions.assertFalse(validator.isCorrectName(str).isValid());
    }

    @Test
    void shouldFalseIfNameIsNull() {
        Assertions.assertFalse(validator.isCorrectName(null).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"aAZasdf:;2", "Bro23'/.", ";;leonl;';", "kdazZZA2;.", "ZASD;;.", "     /dw/'sasdwazx"})
    void shouldTrueIfNameIsStringWithSymbols(String str) {
        Assertions.assertTrue(validator.isCorrectName(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"'']']'/'/'/'[[", "/**%#$@", "^^^^^#@!", "&()$*(@)$", "!@#%$^&*()_*//-", "#"})
    void shouldTrueIfNameIsOnlySymbols(String str) {
        Assertions.assertTrue(validator.isCorrectName(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Zikker", "Tops", "Bisquits", "V4 cover"})
    void shouldTrueIfNameWithoutSymbols(String str) {
        Assertions.assertTrue(validator.isCorrectName(str).isValid());
    }

    /*
    Test isCorrectNumberOfParticipants() method
     */

    @Test
    void shouldFalseIfNumberOfParticipantsIsNull() {
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants(null).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "         "})
    void shouldFalseIfNumberOfParticipantsIsEmpty(String str) {
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Tood", "Eqwexc231", "..;dkk-31", "@#$#@zfew", "ASDKLMKLDAS", "@$E3333", "ddssssss dssa asd"})
    void shouldFalseIfNumberOfParticipantsIsString(String str) {
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-123.12312", "-100.0000", "-01.21311", "-0.0", "0.0", "01.22134", "00312341.23334555112235"})
    void shouldFalseIfNumberOfParticipantsIsDouble(String str) {
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1100", "-00103", "-01", "-1", "0"})
    void shouldFalseIfNumberOfParticipantsIsLessThanOne(String str) {
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1001", "01001", "15444", "004111144752"})
    void shouldFalseIfNumberOfParticipantsIsMoreThanOneThousand(String str) {
        Assertions.assertFalse(validator.isCorrectNumberOfParticipants(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "23", "0193", "000356", "777", "999", "1000"})
    void shouldTrueIfNumberOfParticipantsBetweenOneAndOneThousand(String str) {
        Assertions.assertTrue(validator.isCorrectNumberOfParticipants(str).isValid());
    }

    /*
    Test isCorrectGenre() method
    */

    @ParameterizedTest
    @ValueSource(strings = {"", "         "})
    void shouldFalseIfGenreIsEmpty(String str) {
        Assertions.assertFalse(validator.isCorrectGenre(str).isValid());
    }

    @Test
    void shouldFalseIfGenreIsNull() {
        Assertions.assertFalse(validator.isCorrectGenre(null).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-3334", "-231", "-2", "0", "12", "245",
            "-231.345", "-032.321456", "-21.9", "-1.0", "0.0", "1.4", "0124.564", "4411.3"})
    void shouldFalseIfGenreIsIntegerOrDouble(String str) {
        Assertions.assertFalse(validator.isCorrectGenre(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"aka", "ee3_33e_ees"})
    void shouldFalseIfStringIsMismatchWithGenre(String str) {
        Assertions.assertFalse(validator.isCorrectGenre(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"PSYCHEDELIC_CLOUD_RAP", "JAZZ", "BLUES", "MATH_ROCK", "BRIT_POP",
    "psychedelic_cloud_rap", "JaZZ", "Blues", "math_ROCK", "BRIt_POp"})
    void shouldTrueIfStringIsMatchWithGenre(String str) {
        Assertions.assertTrue(validator.isCorrectGenre(str).isValid());
    }

    /*
    Test isCorrectNameBestAlbum() method
    */

    @ParameterizedTest
    @ValueSource(strings = {"", "         "})
    void shouldFalseIfNameBestAlbumIsEmpty(String str) {
        Assertions.assertFalse(validator.isCorrectNameBestAlbum(str).isValid());
    }

    @Test
    void shouldFalseIfNameBestAlbumIsNull() {
        Assertions.assertFalse(validator.isCorrectNameBestAlbum(null).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"aAZasdf:;2", "Bro23'/.", ";;leonl;';", "kdazZZA2;.", "ZASD;;.", "     /dw/'sasdwazx, ",
            "'']']'/'/'/'[[", "/**%#$@", "^^^^^#@!", "&()$*(@)$", "!@#%$^&*()_*//-", "#",
            "Zikker", "Tops", "Bisquits", "V4 cover"})
    void shouldTrueIfNameBestAlbumHasSymbolsAndLetters(String str) {
        Assertions.assertTrue(validator.isCorrectNameBestAlbum(str).isValid());
    }

    /*
    Test isCorrectSalesBestAlbum() method
    */

    @ParameterizedTest
    @ValueSource(strings = {"", "         "})
    void shouldFalseIfSalesBestAlbumIsEmpty(String str) {
        Assertions.assertFalse(validator.isCorrectSalesBestAlbum(str).isValid());
    }

    @Test
    void shouldFalseIfSalesBestAlbumIsNull() {
        Assertions.assertFalse(validator.isCorrectSalesBestAlbum(null).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Tood", "Eqwexc231", "..;dkk-31", "@#$#@zfew", "ASDKLMKLDAS", "@$E3333", "ddssssss dssa asd"})
    void shouldFalseIfSalesBestAlbumIsString(String str) {
        Assertions.assertFalse(validator.isCorrectSalesBestAlbum(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-231.345", "-032.321456", "-21.9", "-1.0", "0.0", "1.4", "0124.564", "4411.3"})
    void shouldFalseIfSalesBestAlbumIsDouble(String str) {
        Assertions.assertFalse(validator.isCorrectSalesBestAlbum(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {Long.MIN_VALUE + "","-223141", "-2134", "-100", "-1", "0"})
    void shouldFalseIfSalesBestAlbumIsLessThanOne(String str) {
        Assertions.assertFalse(validator.isCorrectSalesBestAlbum(str).isValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "114", "4002", "0334115", "21212444", Long.MAX_VALUE + ""})
    void shouldTrueIfSalesBestAlbumIsPositiveInteger(String str) {
       Assertions.assertTrue(validator.isCorrectSalesBestAlbum(str).isValid());
    }
}
