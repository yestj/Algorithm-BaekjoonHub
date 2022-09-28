import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] hallway;
	static boolean[][] visited;

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int cnt, max;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		int K = sc.nextInt();

		hallway = new int[N][M];
		visited = new boolean[N][M];

		for (int k = 0; k < K; k++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			hallway[r][c] = 1;
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				// 카운트 초기화.
				if (!visited[r][c] && hallway[r][c] == 1) {
					cnt = 0;
					leftover(r, c);
					max = Math.max(cnt, max);
				}
			}
		}

		System.out.println(max);

	}

	static void leftover(int r, int c) {
		// 1. 만약 해당자리가 범위를 벗어나면 return
		if (r < 0 || r >= N || c < 0 || c >= M) {
			return;
		}
		// 2. 해당 자리가 0이거나 이미 방문한 자리라면 return
		if (hallway[r][c] == 0 || visited[r][c]) {
			return;
		}

		visited[r][c] = true;
		cnt++;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			leftover(nr, nc);
		}

	}

}