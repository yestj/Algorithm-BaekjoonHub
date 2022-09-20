import java.util.Scanner;

public class Main {

	static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		dp = new int[N + 1];

		dp[0] = 0;
		dp[1] = 0;

		makeOne(N);
		System.out.println(dp[N]);

	}

	static void makeOne(int N) {
		if (N <= 1) {
			return;
		}

		dp[N] = Integer.MAX_VALUE;

		// 1. 3으로 나눴을 때
		if (N % 3 == 0) {
			if (N / 3 > 1 && dp[N / 3] == 0)
				makeOne(N / 3);
			dp[N] = Math.min(dp[N], 1 + dp[N / 3]);
		}
		// 2. 2로 나눴을 때
		if (N % 2 == 0) {
			if (N / 2 > 1 && dp[N / 2] == 0)
				makeOne(N / 2);
			dp[N] = Math.min(dp[N], 1 + dp[N / 2]);
		}
		// 3. 1을 뺐을 때
		if (N - 1 > 1 && dp[N - 1] == 0)
			makeOne(N - 1);
		dp[N] = Math.min(dp[N], 1 + dp[N - 1]);

		return;
	}
}