package L3.Semaphore.ReadWrite;

public class Reader extends Thread {

    private final NoteBook noteBook;

    public Reader(String name, NoteBook noteBook) {
        super(name);
        this.noteBook = noteBook;
    }

    @Override
    public void run() {
        try {
            noteBook.read();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
