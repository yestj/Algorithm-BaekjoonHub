import java.util.Scanner;

public class Main {

	static int w, h;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			w = sc.nextInt();
			h = sc.nextInt();

			// map 주위를 0으로 채워놓기..!
			map = new int[h + 2][w + 2];
			visited = new boolean[h + 2][w + 2];

			// 지도에 값 입력 받기.
			// 1: 배추, 0: 땅
			int K = sc.nextInt();
			for (int j = 0; j < K; j++) {
				int c = sc.nextInt();
				int r = sc.nextInt();
				map[r + 1][c + 1] = 1;
			}

			int cnt = 0;

			for (int r = 1; r < h + 1; r++) {
				for (int c = 1; c < w + 1; c++) {
					if (map[r][c] == 1 && !visited[r][c]) {
						cnt++;
						find(r, c);
					}
				}
			}

			System.out.println(cnt);
		}
	}

	static void find(int r, int c) {

		if (map[r][c] == 0 || visited[r][c]) {
			return;
		}

		visited[r][c] = true;

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			find(nr, nc);
		}
	}

}
