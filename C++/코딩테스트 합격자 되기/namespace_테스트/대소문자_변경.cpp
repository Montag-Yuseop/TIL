#include <iostream>
#include <string>
#include <algorithm>

using std::cout;
using std::cin;
using std::string;

int main() {
	string s = "aBcDe";

	for (int i = 0; i < s.length(); i++) {
		char c = s[i];
		
		if (c > 74) {
			s[i] = toupper(s[i]);
		}
		else {
			s[i] = tolower(s[i]);
		}
	}

	cout << s;

	return 0;
}