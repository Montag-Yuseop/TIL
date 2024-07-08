#include <iostream>
#include <vector>
#include <string>

using std::vector;
using std::string;
using std::cin;
using std::cout;

int main()
{
	int N, M;
	cin >> N >> M;

	vector<std::vector<int>> matrix;
	string line;

	for (int i = 0; i < N; ++i) {
		cin >> line;
		
		vector<int> temp;
		for (int j = 0; j < M; ++j) {
			temp.push_back(line[j] - '0');
		}
		matrix.push_back(temp);
	}

	for (int i = 0; i < matrix.size(); ++i) {
		for (int j = matrix[i].size() - 1; j >= 0; --j) {
			cout << matrix[i][j];
		}
		cout << "\n";
	}

	return 0;
}
