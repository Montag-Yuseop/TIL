#include <stdio.h>
#include <unistd.h>
#include <wait.h>

int value = 5;

int main()
{
    pid_t pid;
    pid = fork();
    if (pid == 0) {
        value += 15;
        printf("Child: Value = %d\n", value);
        return 0;
    }
    else if(pid > 0) {
        wait(NULL);
        printf("Parents: Value = %d\n", value);
    }
}