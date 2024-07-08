#include <stdio.h>
#pragma warning(disable:4996)

//int main() {
//	int A, B, C, D;
//
//	cin >> A >> B >> C >> D;
//
//	cout << abs((A + D) - (B + C));
//
//	return 0;
//}

//int main() {
//
//	vector<int> arr;
//
//	for (int i = 0; i < 4; i++) {
//		int num;
//		cin >> num;
//		arr.push_back(num);
//	}
//
//	cout << abs(arr[0] + arr[3] - arr[1] - arr[2]);
//	
//	return 0;
//}

//int main() {
//
//	int answer = 0;
//
//	for (int i = 0; i < 4; i++) {
//		int num;
//		cin >> num;
//		if (i == 0 || i == 3) {
//			answer += num;
//		} else {
//			answer -= num;
//		}
//	}
//
//	if (answer < 0) cout << answer * -1;
//	else cout << answer;
//
//	return 0;
//}

int main() {

	int A = 0, B = 0, C = 0, D = 0;

	scanf("%d %d %d %d", &A, &B, &C, &D);

	int answer = (A + D) - (B + C);

	if (answer < 0) answer *= -1;
	
	printf("%d", answer);

	return 0;
}