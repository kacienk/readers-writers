package pl.edu.agh.kis.pz1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Reader thread implementation in readers-writers problem.
 *
 * @author Kacper Cienkosz
 */
public class Reader extends Thread{
    private static final Logger logger = LogManager.getLogger(Reader.class);
    private ReadingRoom readingRoom = null;
    Random random = new Random();
    private int id;

    /**
     * Creates a Reader with the given ID that reads the resource of given ReadingRoom.
     *
     * @param readingRoom ReadingRoom which resource Reader will read.
     * @param id ID of the Reader.
     */
    public Reader(ReadingRoom readingRoom, int id) {
        this.readingRoom =  readingRoom;
        this.id = id;
    }

    /**
     * Runs Reader thread.
     */
    @Override
    public void run() {
        try {
            do {
                readingRoom.requestRead(id);

                sleep((long) 1000 + random.nextInt(2001));

                readingRoom.finishRead(id);
            } while(true);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Reader {} thread has been interrupted.", id);
        }

    }
}
