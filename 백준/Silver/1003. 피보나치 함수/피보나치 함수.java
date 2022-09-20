import java.util.Scanner;

public class Main {

	static int[][] dp;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			dp = new int[N + 1][2];
			fibo(N);
			System.out.println(dp[N][0] + " " + dp[N][1]);
		}

	}

	static void fibo(int N) {
		if (N == 0) {
			dp[N][0] = 1;
		} else if (N == 1) {
			dp[N][1] = 1;
		} else {
			if (dp[N - 2][0] == 0 && dp[N - 2][1] == 0)
				fibo(N - 2);
			if (dp[N - 1][0] == 0 && dp[N - 1][1] == 0)
				fibo(N - 1);
			dp[N][0] = dp[N - 2][0] + dp[N - 1][0];
			dp[N][1] = dp[N - 2][1] + dp[N - 1][1];
		}
	}
}