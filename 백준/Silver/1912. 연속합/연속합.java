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
			dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
			res = Math.max(res, dp[i]);
		}

		System.out.println(res);

	}

}
