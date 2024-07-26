#include <stdio.h>
#include <unistd.h>
#include <wait.h>

#define SIZE 5
int nums[SIZE] = {0, 1, 2, 3, 4};

int main()
{
    pid_t pid, my_id;
    int i;
    pid = fork();

    if (pid == 0) {
        for (i = 0; i < SIZE; i++) {
            nums[i] *= i;
            printf("Child: %d \n", nums[i]);
        }
    }

    else if (pid > 0) {
        wait(NULL);
        for (i = 0; i < SIZE; i++) {
            printf("Parent: %d \n", nums[i]);
        }
    }


    return 0;
}