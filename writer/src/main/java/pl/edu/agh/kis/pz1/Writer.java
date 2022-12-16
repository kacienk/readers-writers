package pl.edu.agh.kis.pz1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Writer extends Thread {
    private static final Logger logger = LogManager.getLogger(Writer.class);
    private final ReadingRoom readingRoom;
    private final Random random = new Random();
    private int id;

    /**
     * Creates a Writer with the given ID that writes to the resource of given ReadingRoom.
     *
     * @param readingRoom ReadingRoom to which resource Writer will write.
     * @param id ID of the Writer.
     */
    Writer(ReadingRoom readingRoom, int id) {
        this.readingRoom = readingRoom;
        this.id = id;
    }

    /**
     * Runs Writer thread.
     */
    @Override
    public void run() {
        try {
            do {
                readingRoom.requestWrite(id);

                sleep((long) 1000 + random.nextInt(2001));

                readingRoom.finishWrite(id);

                sleep((long) 2000 + random.nextInt(8001));
            } while(true);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Writer {} thread has been interrupted.", id);
        }
    }
}
