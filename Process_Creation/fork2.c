#include <stdio.h>
#include <unistd.h>
int main()
{
    int pid1 = fork();
    int pid2 = fork();
    if(pid1 == 0){
        // Child1 process entered 
        if(pid2 == 0){
            // Child3 
        }
    }
}