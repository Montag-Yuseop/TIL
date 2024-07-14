#include <iostream>
#include <string>
#include <vector>
#include <utility>

using std::vector;
using std::string;
using std::cin;
using std::cout;
using std::pair;

const auto npos = std::string::npos;// ��Ī ����?

//int main() {
//	int N;
//	cin >> N; // N �Է�
//
//	vector<pair<string, string>> keyword;
//
//	for (int i = 0; i < N; i++) {
//		string s1, s2;
//		cin >> s1 >> s2;
//		keyword.push_back(make_pair(s1, s2));
//	}
//
//	string after;
//	cin >> after;
//
//	int startIdx, endIdx;
//	cin >> startIdx >> endIdx;
//
//	// ��� �Է� ����, after ���ڸ� ������� replace ����
//	// Ű���带 ������� ���� ����
//	for (int i = 0; i < keyword.size(); i++) {
//		string from = keyword[i].first; // from
//		string to = keyword[i].second; // to
//
//	//	to�� �ٽ� from���� ����
//		while (after.find(to) != -1) {
//			after.replace(after.find(to), to.length(), from);
//		}
//	}
//
//	cout << after.substr(startIdx-1, endIdx - startIdx + 1);
//
//	return 0;
//}

int main() {
	int N;
	cin >> N; // N �Է�

	vector<pair<string, char>> keyword;

	for (int i = 0; i < N; i++) {
		string s1;
		char s2;
		cin >> s1 >> s2;
		keyword.push_back(make_pair(s1, s2));
	}

	string after, before;
	cin >> after;

	int startIdx, endIdx;
	cin >> startIdx >> endIdx;

	// ��� �Է� ����, after ���ڸ� ������� replace ����
	// Ű���带 ������� ���� ����
	for (int i = 0; i < after.size(); i++) {
		for (int j = 0; j < keyword.size(); j++) {
			if (after[i] == keyword[j].second) {
				before += keyword[j].first;
				break;
			}
		}
	}
	cout << before.substr(startIdx - 1, endIdx - startIdx + 1);

	return 0;
}