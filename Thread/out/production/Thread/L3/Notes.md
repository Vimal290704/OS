Critical Section:
Shared piece of code where we should not allow multiple threads to work concurrently

- By synchronizing that part we can make sure that part should not be corrupted when multiple threads/tasks are trying
  to access that resource


Ideal Solution: 
- Mutual exclusion – only one thread can execute the method at a certain point in time, using locks
- Cooperation – the ability to make threads wait for certain conditions to be met, using wait-set
- We can either use Priority Scheduling Algorithm with ageing or FIFO approach for waiting of threads


- wait() and notify() are key methods in Java used in synchronized blocks that enable collaboration between threads.

- wait() orders the calling thread to release the monitor and go to sleep until some other thread enters this monitor and calls notify().  
- Also, notify() wakes up the first thread that called wait() on the specific object.