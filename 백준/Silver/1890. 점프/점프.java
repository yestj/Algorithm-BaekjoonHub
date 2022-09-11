import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] map = new int[N][N];
		long[][] dp = new long[N][N];

		// 지도에 값 입력 받기.
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		dp[0][0] = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (r == N - 1 && c == N - 1)
					break;
				if (r + map[r][c] < N) {
					dp[r + map[r][c]][c] += dp[r][c];
				}
				if (c + map[r][c] < N) {
					dp[r][c + map[r][c]] += dp[r][c];
				}
			}
		}

		System.out.println(dp[N - 1][N - 1]);
	}

}