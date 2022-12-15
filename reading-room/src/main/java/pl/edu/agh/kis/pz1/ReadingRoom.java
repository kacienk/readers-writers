package pl.edu.agh.kis.pz1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

public class ReadingRoom {
    private static final Logger logger = LogManager.getLogger(ReadingRoom.class);
    private static final int MAX_NUMBER_OF_READERS = 5;
    private final Semaphore writeSemaphore = new Semaphore(1);
    private final Semaphore readSemaphore = new Semaphore(MAX_NUMBER_OF_READERS, true);
    private String resource = "default";
    private int writersInQueue = 0;
    private int readersInQueue = 0;


    public void requestRead(int id) throws InterruptedException {
        logger.info("Reader {} wants to enter the reading room.", id);
        this.readersInQueue++;
        logger.info("Readers in queue: {}", this.readersInQueue);

        readSemaphore.acquire();

        this.readersInQueue--;
        logger.info("Reader {} entered to enter the reading room.", id);
        logger.info("Readers in queue: {}", this.readersInQueue);
    }

    public void finishRead(int id) {
        logger.info("Reader {} read: {}", id, this.resource);
        logger.info("Reader {} left the reading room.", id);
        readSemaphore.release();
    }

    public void requestWrite(int id) throws InterruptedException {
        logger.info("Writer {} wants to enter the reading room.", id);
        this.writersInQueue++;
        logger.info("Writers in queue: {}", this.writersInQueue);

        readSemaphore.acquire(MAX_NUMBER_OF_READERS);
        writeSemaphore.acquire();

        this.writersInQueue--;
        logger.info("Writer {} entered reading room.", id);
        logger.info("Writers in queue: {}", this.writersInQueue);
    }

    public void finishWrite(int id) {
        this.resource = "Set by writer" + id + ".";
        logger.info("Writer {} written to the resource.", id);

        logger.info("Writer {} left the reading room.", id);
        readSemaphore.release(MAX_NUMBER_OF_READERS);
        writeSemaphore.release();
    }
}
