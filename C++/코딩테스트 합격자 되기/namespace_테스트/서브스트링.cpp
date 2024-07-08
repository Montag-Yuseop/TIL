#include <iostream>
#include <string>
#include <algorithm>

using std::cout;
using std::cin;
using std::string;
using std::replace;

int main() {
	string s = "aBcDe";

	s = s.substr(0, 2);

	cout << s;

	return 0;
}