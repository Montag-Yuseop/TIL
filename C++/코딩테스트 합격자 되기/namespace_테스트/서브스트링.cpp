#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

using std::cout;
using std::cin;
using std::string;
using std::replace;
using std::vector;

int main() {
	string s = "aBcDe";

	vector<int> arr;

	s += "g";
	s.append("1");
	s.append("2");

	cout << s;
	

	return 0;
}