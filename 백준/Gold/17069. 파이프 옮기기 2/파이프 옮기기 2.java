import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] map = new int[N][N];
		long[][][] dp = new long[N][N][3];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		// 현재 파이프가 놓여있는 상태 0: 가로 1: 세로 2: 대각선
		// 시작값 입력.
		dp[0][1][0] = 1;

		// dp 값 입력 하기.
		for (int r = 0; r < N; r++) {
			for (int c = 2; c < N; c++) {
				if (map[r][c] == 0) {
					// 1. 가로로 놓여있을 때
					if (c > 0 && map[r][c - 1] == 0)
						dp[r][c][0] = dp[r][c - 1][0] + dp[r][c - 1][2];
					// 2. 세로로 놓여있을 때
					if (r > 0 && map[r - 1][c] == 0)
						dp[r][c][1] = dp[r - 1][c][1] + dp[r - 1][c][2];
					// 3. 대각선으로 놓여있을 때
					if (r > 0 && c > 0 && map[r][c - 1] == 0 && map[r - 1][c] == 0 && map[r - 1][c - 1] == 0) {
						dp[r][c][2] = dp[r - 1][c - 1][0] + dp[r - 1][c - 1][1] + dp[r - 1][c - 1][2];
					}
				}
			}
		}
		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);

	}

}