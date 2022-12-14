package pl.edu.agh.kis.pz1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

public class ReadingRoom {
    private static final Logger logger = LogManager.getLogger(ReadingRoom.class);
    private final int maxNumberOfReaders = 5;
    private final Semaphore writeSemaphore = new Semaphore(1);
    private final Semaphore readSemaphore = new Semaphore(maxNumberOfReaders, true);
    private String resource = "default";
    private int writersInQueue = 0;
    private int readersInQueue = 0;


    public void requestRead(int id) {
        logger.info("Reader " + id + " wants to enter the reading room.");
        this.readersInQueue++;
        logger.info("Readers in queue: " + this.readersInQueue);

        try {
            readSemaphore.acquire();
        } catch (InterruptedException ignored) { }

        this.readersInQueue--;

        logger.info("Reader " + id + " entered to enter the reading room.");
        logger.info("Readers in queue: " + this.readersInQueue);
    }

    public void finishRead(int id) {
        logger.info("Reader " + id + " read: " + resource);
        logger.info("Reader " + id + " left the reading room.");
        readSemaphore.release();
    }

    public void requestWrite(int id) {
        logger.info("Writer " + id + " wants to enter the reading room.");
        this.writersInQueue++;
        logger.info("Writers in queue: " + this.writersInQueue);

        try {
            readSemaphore.acquire(maxNumberOfReaders);
            writeSemaphore.acquire();
        } catch (InterruptedException ignored) { }


        this.writersInQueue--;

        logger.info("Writer " + id + " entered reading room.");
        logger.info("Writers in queue: " + this.writersInQueue);
    }

    public void finishWrite(int id) {
        this.resource = "Set by writer" + id + ".";
        logger.info("Writer " + id + " written to the resource.");

        logger.info("Writer " + id + " left the reading room.");
        readSemaphore.release(maxNumberOfReaders);
        writeSemaphore.release();
    }
}
