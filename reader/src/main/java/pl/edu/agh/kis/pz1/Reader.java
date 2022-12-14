package pl.edu.agh.kis.pz1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Reader extends Thread{
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

                sleep((int) 1000 + random.nextInt(2001));

                readingRoom.finishRead(id);
            } while(true);
        }
        catch (InterruptedException ignored) {}
    }
}
