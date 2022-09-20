import java.util.Scanner;

public class Main {

	static int N, d;
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];
		dp = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}
		move(0, 1);
		System.out.println(dp[N - 1][N - 1]);

	}

	static void move(int row, int col) {

		// 우, 하, 우하
		int[] dr = { 0, 1, 1 };
		int[] dc = { 1, 0, 1 };

		dp[row][col]++;

		// 1. 파이프가 가로로 놓여 있을 경우,
		if (d == 0) {
			// 1-1. 오른쪽 방향 탐색.
			if (col + dc[d] < N && map[row][col + dc[d]] != 1) {
				move(row, col + dc[d]);
			}
			// 1-2. 대각선 방향 탐색.
			d = 2;
			if (row + dr[d] < N && col + dc[d] < N && map[row + dr[d]][col] != 1 && map[row][col + dc[d]] != 1
					&& map[row + dr[d]][col + dc[d]] != 1) {
				move(row + dr[d], col + dc[d]);
			}
			d = 0;
		}

		// 2. 파이프가 세로로 놓여 있을 경우,
		if (d == 1) {
			// 2-1. 아래 방향 탐색.
			if (row + dr[d] < N && map[row + dr[d]][col] != 1) {
				move(row + dr[d], col);
			}
			// 2-2. 대각선 방향 탐색.
			d = 2;
			if (row + dr[d] < N && col + dc[d] < N && map[row + dr[d]][col] != 1 && map[row][col + dc[d]] != 1
					&& map[row + dr[d]][col + dc[d]] != 1) {
				move(row + dr[d], col + dc[d]);
			}
			d = 1;
		}

		// 3. 파이프가 대각선으로 놓여 있을 경우,
		if (d == 2) {
			// 3-1. 대각선 방향 탐색.
			if (row + dr[d] < N && col + dc[d] < N && map[row + dr[d]][col] != 1 && map[row][col + dc[d]] != 1
					&& map[row + dr[d]][col + dc[d]] != 1) {
				move(row + dr[d], col + dc[d]);
			}
			// 3-2. 오른쪽 방향 탐색.
			d = 0;
			if (col + dc[d] < N && map[row][col + dc[d]] != 1) {
				move(row, col + dc[d]);
			}
			// 3-3. 아래 방향 탐색.
			d = 1;
			if (row + dr[d] < N && map[row + dr[d]][col] != 1) {
				move(row + dr[d], col);
			}
			d = 2;
		}

	}
}