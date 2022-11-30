import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}

	}

	// BFS로 진행.
	// 1. 맵을 돌면서 1이상인 숫자를 만나면 큐에 넣어줌. 방문처리 해줌.
	// 2. 큐가 빌 때까지 아래 반복.
	// 3. 새로운 맵에 숫자가 있는 빙산을 기준으로 4방탐색하여 0의 개수만큼 숫자 차감. (단, 0 이하로는 내려갈 수 없음.)
	// 4. 4방탐색할 때 새로운 숫자를 만나면, 큐에 넣어줌. 넣으면서 방문처리.
	// 5. 큐가 비었을 경우, 방문한 적 없는데 숫자가 1이상인 칸이 있으면 섬이 쪼개진거 이므로 cnt 반환.
	// 6. 숫자가 1이상인 칸을 모두 반복했다면 cnt++해주고 새로운 맵을 원본맵에다가 복사.

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

		int year = 0; // 몇 년 이 지났는지 카운트.

		// 년 주기로 반복.
		out: while (true) {
			int[][] newMap = new int[N][M]; // n년 후 빙산의 모습을 임시 저장.
			boolean[][] visited = new boolean[N][M];
			boolean flag = true;

			Queue<Pos> ice = new LinkedList<>();
			ice: for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] >= 1) {
						ice.add(new Pos(r, c));
						visited[r][c] = true;
						flag = false;
						break ice;
					}
				}
			}

			// 발견된 섬이 없으면 종료.
			if (flag) {
				year = 0;
				break out;
			}

			// 발견된 섬이 있다면 bfs 탐색 진행.

			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, 1, -1 };

			while (!ice.isEmpty()) {
				Pos curr = ice.poll();
				int r = curr.r;
				int c = curr.c;
				int cnt = 0; // 빙산을 둘러싼 물의 영역 카운트.
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (map[nr][nc] == 0) {
						cnt++;
					} else if (map[nr][nc] >= 1 && !visited[nr][nc]) {
						ice.add(new Pos(nr, nc));
						visited[nr][nc] = true;
					}
				}
				int newValue = map[r][c] - cnt;
				if (newValue < 0) {
					newMap[r][c] = 0;
				} else {
					newMap[r][c] = newValue;
				}
			}

			// 방문안했는데 섬이 발견된다면 섬이 분리된 것이므로 while문 종료.
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (!visited[r][c] && map[r][c] >= 1) {
						break out;
					}
				}
			}

			// 분리된 섬이 발견되지 않았으면 뉴맵을 맵에 복사해주고 year +1해줌.
			for (int r = 0; r < N; r++) {
				map[r] = newMap[r].clone();
			}
			year++;

		}

		System.out.println(year);
	}
}