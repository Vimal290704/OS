package L3.Semaphore.ReadWrite;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NoteBook {
    private final Semaphore ReaderCountLock = new Semaphore(1, true);
    private final Semaphore writeLock = new Semaphore(1, true);
    private final Semaphore readCountSemaphore = new Semaphore(3, true);
    private int readerCount = 0;
    private final StringBuilder sb;

    public NoteBook() {
        this.sb = new StringBuilder();
    }

    public void write(String text) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is trying to write to NoteBook");

        writeLock.acquire();
        try {
            System.out.println(Thread.currentThread().getName() + " is writing to NoteBook");
            Thread.sleep(1000);
            sb.append(text).append("\n");
            System.out.println(Thread.currentThread().getName() + " finished writing. Content: " + sb.toString().trim());
        } finally {
            writeLock.release();
        }
        notifyAll();
    }

    public void read() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is trying to read from NoteBook");

        readCountSemaphore.acquire();
        try {
            ReaderCountLock.acquire();
            try {
                readerCount++;
                // if this is the first reader then acquire writerLock to make sure that no Writer anything
                if (readerCount == 1) {
                    writeLock.acquire();
                }
            } finally {
                ReaderCountLock.release();
            }

            System.out.println(Thread.currentThread().getName() + " is reading from NoteBook");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " read: " + sb.toString().trim());

            ReaderCountLock.acquire();
            try {
                readerCount--;
                // if this was the last reader then release writerLock so that now they can write
                if (readerCount == 0) {
                    writeLock.release();
                }
            } finally {
                ReaderCountLock.release();
            }
        } finally {
            readCountSemaphore.release();
        }
    }
}