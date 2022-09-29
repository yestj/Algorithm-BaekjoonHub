import java.util.Scanner;

public class Main {

	static int N;

	static int max, cnt;

	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}
		for (int i = 0; i <= 100; i++) {
			visited = new boolean[N][N];
			cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c] && map[r][c] > i) {
						cnt++;
						dfs(r, c, i);
					}
				}
			}
			if (cnt == 0)
				break;
			else
				max = Math.max(cnt, max);
		}

		System.out.println(max);

	}

	private static void dfs(int r, int c, int i) {
		// 경계 밖이거나 방문한 적 있는 곳이거나 높이가 낮은 지역일 경우 돌아감.
		if (r < 0 || r >= N || c < 0 || c >= N || visited[r][c] || map[r][c] <= i) {
			return;
		}

		// 그렇지 않을 경우, 안전영역이므로 사방탐색 진행.
		visited[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			dfs(nr, nc, i);
		}

	}
}
