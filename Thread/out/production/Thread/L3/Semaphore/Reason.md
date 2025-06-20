Semaphore is data structure used in multithreading that helps us allow only fix number of threads to access that resources.
.acquire() is used to allow the thread to access that resource (if it is full then it will be blocked as it acts like FIFO)
.release() is used to release the thread from the critical section