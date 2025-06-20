package L3.Semaphore.ReadWrite;

public class Writer extends Thread {

    private final NoteBook noteBook;

    private static int i = 0;

    private final String[] values = {"a", "b", "c" , "d" , "e"};

    public Writer(String name, NoteBook noteBook) {
        super(name);
        this.noteBook = noteBook;
    }

    @Override
    public void run() {
        try {
            noteBook.write(values[i++]);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
