import java.util.Scanner;

public class Main {

	static int N, M, cnt;
	static char[][] map;
	static boolean[][] visited;

	// 4방 탐색을 위한 방향 설정.
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 내 병사의 위력과 적국의 병사 위력의 합 저장
	static int white;
	static int blue;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new char[M][N];
		for (int r = 0; r < M; r++) {
			String temp = sc.next();
			for (int c = 0; c < N; c++) {
				map[r][c] = temp.charAt(c);
			}
		}

		visited = new boolean[M][N];
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c] && map[r][c] == 'W') {
					// 카운트 초기화.
					cnt = 0;
					dfs(r, c, 'W');
					white += cnt * cnt;
				}
			}
		}
		visited = new boolean[M][N];
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c] && map[r][c] == 'B') {
					// 카운트 초기화.
					cnt = 0;
					dfs(r, c, 'B');
					blue += cnt * cnt;
				}
			}
		}

		System.out.println(white + " " + blue);
	}

	private static void dfs(int r, int c, char team) {

		if (r < 0 || r >= M || c < 0 || c >= N || visited[r][c] || map[r][c] != team) {
			return;
		}

		cnt++;
		visited[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			dfs(nr, nc, team);
		}

	}

}