import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] map = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		int[][] dp = new int[N][M];
		// 맵을 돌면서 왼쪽, 왼쪽위, 위쪽에 있는 값들을 저장해줌.

		int[] dr = { 0, -1, -1 };
		int[] dc = { -1, -1, 0 };

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				dp[r][c] += map[r][c];
				int temp = 0;
				for (int d = 0; d < 3; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
						temp = Math.max(temp, dp[nr][nc]);
					}
				}
				dp[r][c] += temp;
			}
		}
		System.out.println(dp[N - 1][M - 1]);

	}

}
