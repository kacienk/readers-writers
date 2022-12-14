package pl.edu.agh.kis.pz1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ReadingRoom readingRoom = new ReadingRoom();
        ArrayList<Writer> writers = new ArrayList<>();
        ArrayList<Reader> readers = new ArrayList<>();
        int numberOfWriters = 3;
        int numberOfReaders = 10;


        for (int i = 0; i < numberOfWriters; i++)
            writers.add(new Writer(readingRoom, i + 1));

        for (int i = 0; i < numberOfReaders; i++)
            readers.add(new Reader(readingRoom, i + 1));

        for (Writer writer: writers)
            writer.start();

        for (Reader reader: readers)
            reader.start();
    }
}