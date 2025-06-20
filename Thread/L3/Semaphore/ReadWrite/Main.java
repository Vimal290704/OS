package L3.Semaphore.ReadWrite;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        NoteBook noteBook = new NoteBook();
        for(int i = 0 ; i < 4 ; i++){
            new Writer("Writer " + i , noteBook).start();
            Thread.sleep(1000);
            new Reader("Reader " + i , noteBook).start();
        }
    }
}
