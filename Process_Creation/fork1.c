#include <stdio.h>
#include <unistd.h>

int main()
{
    int pid = fork();

    if (pid == 0)
    {
        // Child process
        printf("Entered the child process, PID: %d\n", getpid());
        printf("Child's parent PID: %d\n", getppid());
    }
    else if (pid > 0)
    {
        // Parent process
        printf("Parent process, PID: %d\n", getpid());
        printf("Child PID: %d\n", pid);
    }
    else
    {
        // Fork failed
        printf("Fork failed!\n");
        return 1;
    }

    printf("Hello World from process %d!\n", getpid());
    return 0;
}