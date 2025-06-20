- In deadlock processes do not execute due to stuck but in starvation they will be executed after some time

Conditions for deadlock:

1. **Mutual Exclusion**
    - Only one process can use a resource at any given time i.e. the resources are non-sharable.

2. **Hold And Wait**
    - Multiple processes requiring some set of resource then what they will do is they will take the resource available
      right now and wait for other resources tp get free.
3. **No Premption**
    - A resource cannot be taken from a process unless the process releases the resource.
4. **Circular Wait**
    - set of processes are waiting for each other in a circular fashion. For example, lets say there are a set of
      processes {P0​,P1​,P2​,P3​} such that P0​ depends on P1​, P1​ depends on P2​, P2​ depends on
      P3​ and P3​ depends on P0​. This creates a circular relation between all these processes and they have to
      wait forever to be executed.

## Methods of Handling Deadlocks in OS

1. **Deadlock Prevention**
    - Mutual Exclusion : It cannot be controlled for non-shareable resources like printer
    - Hold and Wait : We can make sure that the process hold only those resources that is required by them and
      available (If you do not get all the resources then after a fixed amount of time , release all resources hold by
      it)
    - No Preemption : A higher priority task can take resources from lower priority task and after it completes and
      release resource then the other processes from which resources were taken they re-start.
    - Circular Wait: We can give the ordering of the resources in which they should execute
2. **Deadlock Avoidance**
    - Banker's Algorithm:
        - Safe State :- We have at least one sequence in which we can execute the process
        - Unsafe state :- Not of Safe State
        - What we do? := We check for the available resources and the resource which can execute with the help of
          available resource then it will execute that first & now we have new available resource;
        - Limitation:
            - Requires processes to declare maximum resources needed in the beginning
            - Checking safety again and again (after every allocation) is an overhead
3. **Deadlock Detection**
    - We can detect using either Banker's Algorithm or Resource Allocation Graph
4. **Deadlock Ignorance**