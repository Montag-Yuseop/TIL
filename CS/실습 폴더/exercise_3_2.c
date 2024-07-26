#include <stdio.h>
#include <unistd.h>
#include <wait.h>

int value = 5;

int main()
{
    pid_t pid;
    pid = fork();
    value += 5;
    pid = fork();
    value += 5;
    pid = fork();
    value += 5;

    printf("run! %d, %d\n", pid, value);

    return 0;
}