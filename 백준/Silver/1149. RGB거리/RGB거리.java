import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N;
	static int[][] cost;
	static int[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		cost = new int[N][3];
		dp = new int[N][3];

		// 색깔별 가격 입력 받기.
		for (int i = 0; i < N; i++) {
			cost[i][0] = sc.nextInt();
			cost[i][1] = sc.nextInt();
			cost[i][2] = sc.nextInt();
		}

		// dp 첫 번째 행 초기화.
		for (int i = 0; i < 3; i++) {
			dp[0][i] = cost[0][i];
		}

		// 2번째 행부터 가능한 수 중 최소값을 저장.
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				int tmp1 = cost[i][j];
				int tmp2 = cost[i][j];
				for (int k = 0; k < 3; k++) {
					if (j == k)
						continue;
					if (tmp1 == cost[i][j]) {
						tmp1 += dp[i - 1][k];
					} else {
						tmp2 += dp[i - 1][k];
					}
				}
				dp[i][j] = Math.min(tmp1, tmp2);
			}
		}

		int res = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			res = Math.min(res, dp[N - 1][i]);
		}

		System.out.println(res);
	}

}
