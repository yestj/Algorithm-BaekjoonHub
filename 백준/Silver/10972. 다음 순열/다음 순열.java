import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		boolean flag = true;

		int N = sc.nextInt();

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		// 1. 꼭대기 찾기
		int i = N - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			--i;
		if (i == 0)
			flag = false;

		if (i != 0) {
			// 2. 낭떠러지보다 최초 큰 수 찾아 바꿔주기.
			int j = N - 1;
			while (j > 0 && arr[i - 1] >= arr[j])
				--j;
			swap(arr, i - 1, j);

			// 3. 역순으로 뒤집어주기.
			int k = N - 1;
			while (k > i) {
				swap(arr, i++, k--);
			}
		}

		if (flag) {
			for (int x : arr) {
				System.out.print(x + " ");
			}
		} else {
			System.out.println("-1");
		}

	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}