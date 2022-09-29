import java.util.Scanner;

public class Main {

	static int R, C, T;
	static int[][] map;
	static int[][] newMap;

	// 공기정기 윗 방향 순환 순서(역순)
	static int[] ur = { -1, 0, 1, 0 };
	static int[] uc = { 0, 1, 0, -1 };

	// 공기청정기 아랫 방향 순환 순서(역순)
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();

		map = new int[R][C];

		int pr = -1;
		int pc = -1;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				map[r][c] = sc.nextInt();
				// 복사할 배열에도 미리 공기청정기의 위치 넣어주기.
				if (map[r][c] == -1) {
					pr = r;
					pc = c;
				}
			}
		}

		// T초 지남에 따라,
		// 1. 미세먼지가 퍼지는 것을 newMap에다가 적으면서 계산후에, 기존 맵에다가 다시 복사해주기.
		// 2. 공기청정기 작동 시키기.

		for (int t = 0; t < T; t++) {

			// 0. 새로운 맵 만들어주기.
			newMap = new int[R][C];
			newMap[pr - 1][pc] = -1;
			newMap[pr][pc] = -1;

			// 1. 미세먼지 찾아서 확산시키기.
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] > 0) {
						spread(r, c);
					}
				}
			}
			// 2. 확산 시킨 맵을 기존 맵에 복사해주기. (단, 공기청정기에는 확산되지 않으므로 제외.)
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == -1)
						continue;
					map[r][c] = newMap[r][c];
				}
			}

			// 3. 공기청정기 가동 - 공기청정기 아래 부분이 저장되어 있음! 위에서 부터 돌리기 위해 pr-1 해줌.
			purify(pr - 1, pc);
		}

		// 남은 미세먼지 출력하기.
		int ans = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] != -1) {
					ans += map[r][c];
				}
			}
		}

		System.out.println(ans);

	}

	private static void spread(int r, int c) {
		int temp = map[r][c] / 5;
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			// 경계 안에 있고, 공기청정기 위치가 아니라면 확산 가능.
			if (nr >= 0 && nr < R && nc >= 0 && nc < C && newMap[nr][nc] != -1) {
				cnt++;
				newMap[nr][nc] += temp;
			}
		}
		newMap[r][c] += map[r][c] - (temp * cnt);
	}

	private static void purify(int r, int c) {
		// 1. 윗쪽 - 공기청정기 순환 방향의 반대로 돌면서 한 칸씩 밀어줌.
		int row = r;
		int col = c;
		int d = -1;
		while (true) {
			if ((row == 0 && (col == 0 || col == C - 1)) || (row == r && (col == 0 || col == C - 1)))
				d++;
			if (d == 4)
				break;
			int nr = row + ur[d];
			int nc = col + uc[d];
			if (nr == r && nc == c) {
				map[row][col] = 0;
				break;
			}
			if (map[row][col] != -1) {
				map[row][col] = map[nr][nc];
			}
			row = nr;
			col = nc;
		}

		// 2. 아랫쪽 - 마찬가지로 순환 방향의 반대로 돌면서 한 칸씩 밀어줌.
		row = r + 1;
		col = c;
		d = -1;
		while (true) {
			if ((row == r + 1 && (col == 0 || col == C - 1)) || (row == R - 1 && (col == 0 || col == C - 1)))
				d++;
			if (d == 4)
				break;
			int nr = row + dr[d];
			int nc = col + dc[d];
			if (nr == r + 1 && nc == c) {
				map[row][col] = 0;
				break;
			}
			if (map[row][col] != -1) {
				map[row][col] = map[nr][nc];
			}
			row = nr;
			col = nc;
		}
	}
}
