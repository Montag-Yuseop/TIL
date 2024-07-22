#include <iostream>

using std::cin;
using std::cout;

void print(int A[]);
void heapify(int A[], int k, int n);
void build_min_heap(int A[], int n);
void heap_sort(int A[]);

int N, K;
int cnt = 0;

void print(int A[]) {
	for (int i = 1; i <= N; i++) {
		printf("%d ", A[i]);
	}

	exit(0);
}

void heap_sort(int A[]) {
	build_min_heap(A, N);

	for (int i = N; i > 1; i--) {
		int temp = A[1];
		A[1] = A[i];
		A[i] = temp;

		if (++cnt == K) print(A);

		heapify(A, 1, i - 1);
	}
}

void heapify(int A[], int k, int n) {
	int left = 2 * k;
	int right = 2 * k + 1;
	int smaller;

	if (right <= n) {
		if (A[left] < A[right]) {
			smaller = left;
		}
		else {
			smaller = right;
		}
	}
	else if (left <= n) {
		smaller = left;
	}
	else {
		return;
	}

	if (A[smaller] < A[k]) {
		int temp = A[k];
		A[k] = A[smaller];
		A[smaller] = temp;
		

		if (++cnt == K) print(A);

		heapify(A, smaller, n);
	}
}

void build_min_heap(int A[], int n) {
	for (int i = (n / 2); i > 0; i--) {
		heapify(A, i, n);
	}
}


int main() {

	// 1. �迭�� ũ��� ��ȯ Ƚ���� �Է¹޴´�
	cin >> N >> K;

	// 2. ���� �迭 ����
	int* arr = new int[N + 1];

	// 3. �迭 �� �Է�
	for (int i = 1; i < N + 1; i++) {
		cin >> arr[i];
	}

	// 4. heap_sort
	heap_sort(arr);

	printf("-1\n");
	// �迭 �޸� ����
	delete[] arr;
	
	return 0;
}