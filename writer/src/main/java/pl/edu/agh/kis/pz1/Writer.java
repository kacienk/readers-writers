package pl.edu.agh.kis.pz1;

import java.util.Random;

public class Writer extends Thread {
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

                sleep((int) 1000 + random.nextInt(2001));

                readingRoom.finishWrite(id);

                sleep((int) 2000 + random.nextInt(8001));
            } while(true);
        }
        catch (InterruptedException ignored) {}
    }
}
