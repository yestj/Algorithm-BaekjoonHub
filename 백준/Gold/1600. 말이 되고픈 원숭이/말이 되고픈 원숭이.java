import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int K; // 원숭이가 말처럼 움직일 수 있는 횟수.
	static int W, H; // 가로, 세로 길이.
	static int[][] map;
	static boolean[][][] visited;

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 체스말처럼 이동할 경우.
	static int[] hr = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] hc = { -2, -1, 1, 2, -2, -1, 1, 2 };

	static int min; // 동작의 최솟값.

	// 현재 원숭이의 위치와 체스말처럼 이동한 횟수, 원숭이가 총 이동한 거리를 저장할 클래스.
	static class Pos implements Comparable<Pos> {
		int r, c, cnt, len;

		Pos(int r, int c, int cnt, int len) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.len = len;
		}

		@Override
		public int compareTo(Pos o) {
			return this.len - o.len;
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 변수 초기화.
		K = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];
		min = Integer.MAX_VALUE;

		// 맵의 평지와 장애물 정보 입력 받기.
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		findWay();
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}

	}

	private static void findWay() {

		PriorityQueue<Pos> pq = new PriorityQueue<>();
		// 출발지 넣어주기.
		pq.add(new Pos(0, 0, 0, 0));
		visited[0][0][0] = true;

		while (!pq.isEmpty()) {
			Pos pos = pq.poll();
			// 끝에 닿으면 break.
			if (pos.r == H - 1 && pos.c == W - 1) {
				min = Math.min(min, pos.len);
				break;
			}

			if (pos.cnt < K) {
				for (int d = 0; d < 8; d++) {
					int nr = pos.r + hr[d];
					int nc = pos.c + hc[d];
					if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == 0 && !visited[nr][nc][pos.cnt + 1]) {
						pq.add(new Pos(nr, nc, pos.cnt + 1, pos.len + 1));
						visited[nr][nc][pos.cnt + 1] = true;
					}
				}
			}
			for (int d = 0; d < 4; d++) {
				int nr = pos.r + dr[d];
				int nc = pos.c + dc[d];
				if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == 0 && !visited[nr][nc][pos.cnt]) {
					pq.add(new Pos(nr, nc, pos.cnt, pos.len + 1));
					visited[nr][nc][pos.cnt] = true;
				}
			}

		}

	}

}