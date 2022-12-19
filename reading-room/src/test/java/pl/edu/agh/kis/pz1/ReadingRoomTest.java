package pl.edu.agh.kis.pz1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReadingRoomTest {
    private static final Logger logger = LogManager.getLogger(ReadingRoomTest.class);

    @Test
    public void givenNothing_whenReadPermitsAvailable_thenReadResource() {
        ReadingRoom readingRoom = new ReadingRoom();
        String expectedValue = "default";

        try {
            readingRoom.requestRead(1);
            String actualValue = readingRoom.finishRead(1);

            assertEquals(actualValue, expectedValue);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread was interrupted during thread.");
        }
    }

    @Test
    public void givenNothing_whenWritePermitsAvailable_thenWriteToResource() {
        ReadingRoom readingRoom = new ReadingRoom();
        String expectedValue = "Set by writer 1.";

        try {
            readingRoom.requestWrite(1);
            String actualValue = readingRoom.finishWrite(1);

            assertEquals(actualValue, expectedValue);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread was interrupted during thread.");
        }
    }
}