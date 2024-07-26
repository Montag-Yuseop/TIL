#include <stdio.h>
#include <unistd.h>
#include <wait.h>

int value = 5;

int main()
{
    pid_t pid, my_id;
    pid = fork();

    if (pid == 0) {
        my_id = getpid();
        printf("child: pid = %d\n", pid); // 자식의 반환 pid, 0
        printf("child: my_id = %d\n", my_id); // 자식 자신의 pid
    }
    else if (pid > 0) {
        my_id = getpid();
        printf("parent: pid = %d\n", pid); // 부모가 만든 자식의 pid
        printf("parent: my_id = %d\n", my_id); // 부모 자신의 pid
        wait(NULL);
    }

    return 0;
}