package manager;

import builder.MusicBandBuilder;
import music.BestAlbum;
import music.MusicBand;
import music.MusicGenre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;

import static messages.ResultMessages.*;

public class CollectionManagerTest {
    CollectionManager collectionManager = new CollectionManager(new HashSet<>());

    @BeforeEach
    void clearCollection() {
        collectionManager.clear();
    }

    /*
        help() test
    */
    @Test
    void shouldListCommand() {
        Assertions.assertEquals(LIST_OF_AVAILABLE_COMMAND, collectionManager.help());
    }

    /*
        add(MusicBand musicBand) test
    */
    @Test
    void shouldAdditionSuccessIfMusicBandIsNotExistInsideCollection() {
        MusicBand musicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220000000)).build();
        String resultMessage = collectionManager.add(musicBand);
        Assertions.assertTrue(collectionManager.getMusicBands().contains(musicBand));
        Assertions.assertEquals(MUSIC_BAND_HAS_BEEN_ADDED_SUCCESSFUL, resultMessage);
    }

    @Test
    void shouldAdditionMistakeIfMusicBandExistsAlready() {
        MusicBand musicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220000000)).build();
        collectionManager.add(musicBand);

        MusicBand equalMusicBand = new MusicBandBuilder().name("name").numberOfParticipants(23).genre(MusicGenre.BRIT_POP)
                .bestAlbum(new BestAlbum("The best", 22000420)).build();
        collectionManager.add(equalMusicBand);

        Assertions.assertEquals(1, collectionManager.getMusicBands().size());
        Assertions.assertEquals(ADDITION_MISTAKE, collectionManager.add(equalMusicBand));
    }


    /*
        updateById(MusicBand musicBand, long id) test
    */
    @Test
    void shouldUpdateSuccessIfIdIsExistsAndIfBandNameIsUnique() {
        MusicBand oldMusicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220000000)).build();
        collectionManager.add(oldMusicBand);

        MusicBand newMusicBand = new MusicBandBuilder().name("new_name").numberOfParticipants(2).genre(MusicGenre.MATH_ROCK)
                .bestAlbum(new BestAlbum("Top777", 777)).build();

        String resultMessage = collectionManager.updateById(newMusicBand, 1);
        Assertions.assertTrue(collectionManager.getMusicBands().contains(newMusicBand));
        Assertions.assertFalse(collectionManager.getMusicBands().contains(oldMusicBand));
        Assertions.assertEquals(MUSIC_BAND_HAS_BEEN_UPDATED_SUCCESSFUL, resultMessage);
    }

    @Test
    void shouldUpdatedMistakeIfIdIsExistsAndIfBandNameIsNotUnique() {
        MusicBand oldMusicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220000000)).build();
        collectionManager.add(oldMusicBand);

        MusicBand newMusicBand = new MusicBandBuilder().name("name").numberOfParticipants(2).genre(MusicGenre.MATH_ROCK)
                .bestAlbum(new BestAlbum("Top777", 777)).build();

        String resultMessage = collectionManager.updateById(newMusicBand, 1);
        Assertions.assertTrue(collectionManager.getMusicBands().contains(oldMusicBand));
        Assertions.assertEquals(1, collectionManager.getMusicBands().size());
        Assertions.assertEquals(UPDATED_MISTAKE, resultMessage);
    }

    @Test
    void shouldNoSuchIdIfIdIsNotExists() {
        MusicBand oldMusicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220000000)).build();
        collectionManager.add(oldMusicBand);

        MusicBand newMusicBand = new MusicBandBuilder().name("new_name").numberOfParticipants(2).genre(MusicGenre.MATH_ROCK)
                .bestAlbum(new BestAlbum("Top777", 777)).build();

        String resultMessage = collectionManager.updateById(newMusicBand, 2);
        Assertions.assertTrue(collectionManager.getMusicBands().contains(oldMusicBand));
        Assertions.assertEquals(1, collectionManager.getMusicBands().size());
        Assertions.assertEquals(NO_SUCH_ID, resultMessage);
    }

    /*
        removeById(long id) test
    */
    @Test
    void shouldDeleteSuccessIfIdIsExists() {
        MusicBand musicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220000000)).build();
        collectionManager.add(musicBand);

        String resultMessage = collectionManager.removeById(1);
        Assertions.assertTrue(collectionManager.getMusicBands().isEmpty());
        Assertions.assertEquals(MUSIC_BAND_HAS_BEEN_DELETED_SUCCESSFUL, resultMessage);
    }

    @Test
    void shouldDeleteMistakeIfIdIsNotExists() {
        MusicBand musicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220000000)).build();
        collectionManager.add(musicBand);

        String resultMessage = collectionManager.removeById(2);
        Assertions.assertFalse(collectionManager.getMusicBands().isEmpty());
        Assertions.assertEquals(DELETED_MISTAKE, resultMessage);
    }

    /*
        clear() test
    */
    @Test
    void shouldDeleteSuccess() {
        MusicBand musicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220000000)).build();
        collectionManager.add(musicBand);
        String resultMessage = collectionManager.clear();

        Assertions.assertTrue(collectionManager.getMusicBands().isEmpty());
        Assertions.assertEquals(COLLECTION_HAS_BEEN_DELETED, resultMessage);
    }

    /*
        addIfMin(MusicBand musicBand) test
    */
    @Test
    void shouldEmptyCollectionMistakeIfCollectionIsEmpty() {
        MusicBand musicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220000000)).build();
        String resultMessage = collectionManager.addIfMin(musicBand);
        Assertions.assertTrue(collectionManager.getMusicBands().isEmpty());
        Assertions.assertEquals(EMPTY_COLLECTION_MISTAKE, resultMessage);
    }

    @Test
    void shouldAdditionSuccessIfSalesLessThanAllMusicBandsAndIfMusicBandIsUnique() {
        MusicBand musicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220)).build();
        collectionManager.add(musicBand);

        MusicBand newMusicBand = new MusicBandBuilder().name("new_group").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 219)).build();
        String resultMessage = collectionManager.addIfMin(newMusicBand);

        Assertions.assertEquals(2, collectionManager.getMusicBands().size());
        Assertions.assertTrue(collectionManager.getMusicBands().contains(newMusicBand));
        Assertions.assertEquals(MUSIC_BAND_HAS_BEEN_ADDED_SUCCESSFUL, resultMessage);
    }

    @Test
    void shouldAdditionMistakeIfSalesLessThanAllMusicAndIfMusicBandIsNotUnique() {
        MusicBand musicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220)).build();
        collectionManager.add(musicBand);

        MusicBand equalMusicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 219)).build();

        String resultMessage = collectionManager.addIfMin(equalMusicBand);

        Assertions.assertEquals(1, collectionManager.getMusicBands().size());
        Assertions.assertTrue(collectionManager.getMusicBands().contains(musicBand));
        Assertions.assertEquals(ADDITION_MISTAKE, resultMessage);
    }

    @Test
    void shouldSalesBiggerThanMinElement() {
        MusicBand musicBand = new MusicBandBuilder().name("name").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 220)).build();
        collectionManager.add(musicBand);

        MusicBand newMusicBand = new MusicBandBuilder().name("new_group").numberOfParticipants(21).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best", 221)).build();

        String resultMessage = collectionManager.addIfMin(newMusicBand);

        Assertions.assertEquals(1, collectionManager.getMusicBands().size());
        Assertions.assertTrue(collectionManager.getMusicBands().contains(musicBand));
        Assertions.assertEquals(SALES_BIGGER_THAN_MIN_ELEMENT, resultMessage);
    }

    /*
        removeLower(long sales) test
    */
    @Test
    void shouldCollectionIsEmpty() {
        String resultMessage = collectionManager.removeLower(12);
        Assertions.assertEquals(COLLECTION_IS_EMPTY, resultMessage);
    }

    @Test
    void shouldDeleteSuccessIfSalesBiggerThanAnyMusicBand() {
        MusicBand firstBand = new MusicBandBuilder().name("first").numberOfParticipants(21).genre(MusicGenre.MATH_ROCK)
                .bestAlbum(new BestAlbum("The best first", 500)).build();
        MusicBand secondBand = new MusicBandBuilder().name("second").numberOfParticipants(2).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best second", 1200)).build();
        MusicBand thirdBand = new MusicBandBuilder().name("third").numberOfParticipants(11).genre(MusicGenre.BRIT_POP)
                .bestAlbum(new BestAlbum("The best third", 1250)).build();
        MusicBand fourthBand = new MusicBandBuilder().name("fourth").numberOfParticipants(5).genre(MusicGenre.PSYCHEDELIC_CLOUD_RAP)
                .bestAlbum(new BestAlbum("The best fourth", 22000)).build();
        MusicBand fifthBand = new MusicBandBuilder().name("fifth").numberOfParticipants(4).genre(MusicGenre.JAZZ)
                .bestAlbum(new BestAlbum("The best fifth", 100000)).build();
        collectionManager.add(firstBand);
        collectionManager.add(secondBand);
        collectionManager.add(thirdBand);
        collectionManager.add(fourthBand);
        collectionManager.add(fifthBand);

        collectionManager.removeLower(100001);
        Assertions.assertTrue(collectionManager.getMusicBands().isEmpty());
    }

    @Test
    void shouldAllElementBigger() {
        MusicBand firstBand = new MusicBandBuilder().name("first").numberOfParticipants(21).genre(MusicGenre.MATH_ROCK)
                .bestAlbum(new BestAlbum("The best first", 500)).build();
        MusicBand secondBand = new MusicBandBuilder().name("second").numberOfParticipants(2).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best second", 1200)).build();
        MusicBand thirdBand = new MusicBandBuilder().name("third").numberOfParticipants(11).genre(MusicGenre.BRIT_POP)
                .bestAlbum(new BestAlbum("The best third", 1250)).build();
        MusicBand fourthBand = new MusicBandBuilder().name("fourth").numberOfParticipants(5).genre(MusicGenre.PSYCHEDELIC_CLOUD_RAP)
                .bestAlbum(new BestAlbum("The best fourth", 22000)).build();
        MusicBand fifthBand = new MusicBandBuilder().name("fifth").numberOfParticipants(4).genre(MusicGenre.JAZZ)
                .bestAlbum(new BestAlbum("The best fifth", 100000)).build();
        collectionManager.add(firstBand);
        collectionManager.add(secondBand);
        collectionManager.add(thirdBand);
        collectionManager.add(fourthBand);
        collectionManager.add(fifthBand);

        String resultMessage = collectionManager.removeLower(400);
        Assertions.assertEquals(5, collectionManager.getMusicBands().size());
        Assertions.assertEquals(ALL_ELEMENT_BIGGER, resultMessage);
    }

    /*
        minByBestAlbum() test
    */
    @Test
    void shouldCollectionIsEmptyInMinByBestAlbumMethod() {
        String resultMessage = collectionManager.minByBestAlbum();
        Assertions.assertEquals(COLLECTION_IS_EMPTY, resultMessage);
    }

    @Test
    void shouldMinMusicBands() {
        MusicBand firstBand = new MusicBandBuilder().name("first").numberOfParticipants(21).genre(MusicGenre.MATH_ROCK)
                .bestAlbum(new BestAlbum("The best first", 500)).build();
        MusicBand secondBand = new MusicBandBuilder().name("second").numberOfParticipants(2).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best second", 1200)).build();
        MusicBand thirdBand = new MusicBandBuilder().name("third").numberOfParticipants(11).genre(MusicGenre.BRIT_POP)
                .bestAlbum(new BestAlbum("The best third", 1250)).build();
        collectionManager.add(firstBand);
        collectionManager.add(secondBand);
        collectionManager.add(thirdBand);

        String resultMessage = collectionManager.minByBestAlbum();

        Assertions.assertEquals(500, Collections.min(collectionManager.getMusicBands()).getBestAlbum().getSales());
        Assertions.assertEquals(resultMessage, Collections.min(collectionManager.getMusicBands()).toString());
    }

    /*
        filterByBestAlbum(BestAlbum bestAlbum) test
    */
    @Test
    void shouldCollectionIsEmptyInFilterByBestAlbumMethod() {
        BestAlbum bestAlbum = new BestAlbum("Love natural", 3990);
        String resultMessage = collectionManager.filterByBestAlbum(bestAlbum);
        Assertions.assertEquals(COLLECTION_IS_EMPTY, resultMessage);
    }

    @Test
    void shouldShowThreeMusicBands() {
        MusicBand firstBand = new MusicBandBuilder().name("first").numberOfParticipants(21).genre(MusicGenre.MATH_ROCK)
                .bestAlbum(new BestAlbum("The best first", 1500)).build();
        MusicBand secondBand = new MusicBandBuilder().name("second").numberOfParticipants(2).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best second", 1500)).build();
        MusicBand thirdBand = new MusicBandBuilder().name("third").numberOfParticipants(11).genre(MusicGenre.BRIT_POP)
                .bestAlbum(new BestAlbum("The best third", 1500)).build();
        MusicBand fourthBand = new MusicBandBuilder().name("fourth").numberOfParticipants(5).genre(MusicGenre.PSYCHEDELIC_CLOUD_RAP)
                .bestAlbum(new BestAlbum("The best fourth", 22000)).build();
        MusicBand fifthBand = new MusicBandBuilder().name("fifth").numberOfParticipants(4).genre(MusicGenre.JAZZ)
                .bestAlbum(new BestAlbum("The best fifth", 100000)).build();
        collectionManager.add(firstBand);
        collectionManager.add(secondBand);
        collectionManager.add(thirdBand);
        collectionManager.add(fourthBand);
        collectionManager.add(fifthBand);

        BestAlbum bestAlbum = new BestAlbum("Love natural", 1500);

        String resultMessage = collectionManager.filterByBestAlbum(bestAlbum);
        Assertions.assertTrue(resultMessage.contains("first"));
        Assertions.assertTrue(resultMessage.contains("second"));
        Assertions.assertTrue(resultMessage.contains("third"));
        Assertions.assertFalse(resultMessage.contains("fourth"));
        Assertions.assertFalse(resultMessage.contains("fifth"));
    }

    @Test
    void shouldNoSuchElements() {
        MusicBand firstBand = new MusicBandBuilder().name("first").numberOfParticipants(21).genre(MusicGenre.MATH_ROCK)
                .bestAlbum(new BestAlbum("The best first", 1500)).build();
        MusicBand secondBand = new MusicBandBuilder().name("second").numberOfParticipants(2).genre(MusicGenre.BLUES)
                .bestAlbum(new BestAlbum("The best second", 1500)).build();
        MusicBand thirdBand = new MusicBandBuilder().name("third").numberOfParticipants(11).genre(MusicGenre.BRIT_POP)
                .bestAlbum(new BestAlbum("The best third", 1500)).build();
        MusicBand fourthBand = new MusicBandBuilder().name("fourth").numberOfParticipants(5).genre(MusicGenre.PSYCHEDELIC_CLOUD_RAP)
                .bestAlbum(new BestAlbum("The best fourth", 22000)).build();
        MusicBand fifthBand = new MusicBandBuilder().name("fifth").numberOfParticipants(4).genre(MusicGenre.JAZZ)
                .bestAlbum(new BestAlbum("The best fifth", 100000)).build();
        collectionManager.add(firstBand);
        collectionManager.add(secondBand);
        collectionManager.add(thirdBand);
        collectionManager.add(fourthBand);
        collectionManager.add(fifthBand);

        BestAlbum bestAlbum = new BestAlbum("Love natural", 1550);

        String resultMessage = collectionManager.filterByBestAlbum(bestAlbum);
        Assertions.assertEquals(NO_SUCH_ELEMENTS, resultMessage);
    }
}
