import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] dp = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		// 첫 번째 값으로 다 초기화.
		dp[0] = arr[0];
		int res = arr[0];

		for (int i = 1; i < N; i++) {
			// 음수를 만날 경우.
			if (arr[i] < 0) {
				if (arr[i] > res) {
					res = arr[i];
					continue;
				} else if (arr[i] + dp[i - 1] > 0) {
					dp[i] = arr[i] + dp[i - 1];
				} else {
					continue;
				}

			} // 양수를 만나면 더해줌.
			else {
				if (dp[i - 1] > 0) {
					dp[i] = arr[i] + dp[i - 1];
				} else {
					dp[i] = arr[i];
				}
			}
			res = Math.max(res, dp[i]);
		}

		System.out.println(res);

	}

}
