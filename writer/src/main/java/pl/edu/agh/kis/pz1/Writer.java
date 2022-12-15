package pl.edu.agh.kis.pz1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Writer extends Thread {
    private static final Logger logger = LogManager.getLogger(Writer.class);
    private final ReadingRoom readingRoom;
    private final Random random = new Random();
    int id;

    Writer(ReadingRoom readingRoom, int id) {
        this.readingRoom = readingRoom;
        this.id = id;
    }

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
