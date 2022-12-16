package pl.edu.agh.kis.pz1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * ReadingRoom is a class allowing Reader and Writer threads access to the wrapped resource.
 * At most 5 Readers can read at one time.
 * When Writer is writing to the resource, then no Reader can read resource.
 * Only one Writer can write to the resource at one time.
 * Threads have fair access to the resource.
 *
 * @author Kacper Cienkosz
 */
public class ReadingRoom {
    private static final Logger logger = LogManager.getLogger(ReadingRoom.class);
    private static final int MAX_NUMBER_OF_READERS = 5;
    private final Semaphore writeSemaphore = new Semaphore(1);
    private final Semaphore readSemaphore = new Semaphore(MAX_NUMBER_OF_READERS, true);
    private String resource = "default";
    private int writersInQueue = 0;
    private int readersInQueue = 0;

    /**
     * Method should be called by Reader thread when it wants to read.
     * Method allows Reader to read if there are fewer readers than MAX_NUMBER_OF_READERS and no Writer has acquired right to write.
     * If there are more Readers than MAX_NUMBER_OF_READERS or Writer is writing to the resource,
     *then Reader thread is forced to wait until another thread releases its permit.
     *
     * @param id ID of the Reader thread.
     * @throws InterruptedException Called when thread was interrupted while waiting for access.
     * For more information see {@link java.lang.InterruptedException}
     */
    public void requestRead(int id) throws InterruptedException {
        logger.info("Reader {} wants to enter the reading room.", id);
        this.readersInQueue++;
        logger.info("Readers in queue: {}", this.readersInQueue);

        readSemaphore.acquire();

        this.readersInQueue--;
        logger.info("Reader {} entered to enter the reading room.", id);
        logger.info("Readers in queue: {}", this.readersInQueue);
    }

    /**
     * Method should be called by Reader thread when it finishes its 'stay' in ReadingRoom.
     * By calling this method specifically Reader thread reads the resource.
     *
     * @param id ID of the Reader thread.
     * @return Returns value of resource read by Reader thread.
     */
    public String finishRead(int id) {
        logger.info("Reader {} read: {}", id, this.resource);
        logger.info("Reader {} left the reading room.", id);
        readSemaphore.release();

        return this.resource;
    }

    /**
     * Method should be called by Writer thread when it wants to write.
     * Method allows Writer to write to the resource if no Reader is reading and no Writer is writing.
     * If there is another thread that has access to the resource,
     * then Writer thread is forced to wait all reading and writings permits are released.
     *
     * @param id ID of the Writer thread.
     * @throws InterruptedException Called when thread was interrupted while waiting for access.
     * For more information see {@link java.lang.InterruptedException}
     */
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

    /**
     * Method should be called by Writer thread when it finishes its 'stay' in ReadingRoom.
     * By calling this method specifically Writer thread writes to the resource.
     *
     * @param id ID of the Reader thread.
     * @return Returns value of resource read by Reader thread.
     */
    public String finishWrite(int id) {
        this.resource = "Set by writer" + id + ".";
        logger.info("Writer {} written to the resource.", id);

        logger.info("Writer {} left the reading room.", id);
        readSemaphore.release(MAX_NUMBER_OF_READERS);
        writeSemaphore.release();

        return this.resource;
    }
}
