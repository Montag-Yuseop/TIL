#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using std::string;
using std::cin;
using std::cout;

int main() {
    int min = 101;
    int sum = 0;

    for (int i = 0; i < 4; i++) {
        int now;

        cin >> now;

        sum += now;
        min = std::min(min, now);
    }

    sum -= min;
    min = 101;

    for (int i = 0; i < 2; i++) {
        int now;

        cin >> now;

        sum += now;
        min = std::min(min, now);
    }

    sum -= min;

    cout << sum;

    return 0;
}