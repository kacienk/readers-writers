package pl.edu.agh.kis.pz1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Reader extends Thread{
    private static final Logger logger = LogManager.getLogger(Reader.class);
    private ReadingRoom readingRoom = null;
    Random random = new Random();
    private int id;

    public Reader(ReadingRoom readingRoom, int id) {
        this.readingRoom =  readingRoom;
        this.id = id;
    }

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
