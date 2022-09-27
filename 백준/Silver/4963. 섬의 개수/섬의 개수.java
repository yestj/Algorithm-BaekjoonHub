import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int w, h;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { 0, 0, -1, 1, 1, 1, -1, -1 };
	static int[] dc = { -1, 1, 0, 0, 1, -1, 1, -1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {
			w = sc.nextInt();
			h = sc.nextInt();

			// 0 0 입력시 종료.
			if (w == 0 && h == 0)
				break;

			// map 주위를 0으로 채워놓기..!
			map = new int[h + 2][w + 2];
			visited = new boolean[h + 2][w + 2];

			// 지도에 값 입력 받기.
			// 1: 땅, 0: 바다
			for (int r = 1; r < h + 1; r++) {
				for (int c = 1; c < w + 1; c++) {
					map[r][c] = sc.nextInt();
				}
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

		int d = 0;
		visited[r][c] = true;

		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			find(nr, nc);
		}
	}

}
