import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;

	static Queue<Balls> ballPos = new LinkedList<>();
	static int res = -1;

	static class Balls {
		int rr, rc; // 빨간공의 위치.
		int br, bc; // 파란공의 위치.
		int cnt;

		public Balls(int rr, int rc, int br, int bc, int cnt) {
			this.rr = rr;
			this.rc = rc;
			this.br = br;
			this.bc = bc;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Balls [rr=" + rr + ", rc=" + rc + ", br=" + br + ", bc=" + bc + ", cnt=" + cnt + "]";
		}
		
		
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		visited = new boolean[N][M][N][M];

		int rr = 0;
		int rc = 0;
		int br = 0;
		int bc = 0;

		for (int r = 0; r < N; r++) {
			String temp = sc.next();
			for (int c = 0; c < M; c++) {
				map[r][c] = temp.charAt(c);
				if (map[r][c] == 'R') {
					rr = r;
					rc = c;
				} else if (map[r][c] == 'B') {
					br = r;
					bc = c;
				}
			}
		}

		ballPos.add(new Balls(rr, rc, br, bc, 0));
		visited[rr][rc][br][bc] = true;
		move();
		System.out.println(res);

	}

	static void move() {

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		int temp = Integer.MAX_VALUE;

		// 공들의 위치를 뽑아서 이동시켜주기.
		while (!ballPos.isEmpty()) {
			Balls curr = ballPos.poll();

			// 10 이상이면 돌려보내고, res를 -1로 바꾸기.
			if (curr.cnt > 10) {
				res = -1;
				return;
			}

			// 파란색 공이 빠져나간 경우, 계속 진행. (다른 경우의 수가 있을 수 있으므로.)
			if (map[curr.br][curr.bc] == 'O') {
				continue;
			}

			// 빨색이 빠져나가고 파란색은 빠져나가지 않았을 경우.
			if (map[curr.rr][curr.rc] == 'O') {
				res = curr.cnt;
				return;
			}

			for (int d = 0; d < 4; d++) {

				// 빨간 돌부터 움직일 수 있을만큼 굴려주기.
				int rr = curr.rr;
				int rc = curr.rc;

				while (true) {
					rr += dr[d];
					rc += dc[d];
					// 빠나간 경우
					if (map[rr][rc] == 'O')
						break;
					// 벽일 경우에는 한 칸 뒤로 물러나주기.
					if (map[rr][rc] == '#') {
						rr -= dr[d];
						rc -= dc[d];
						break;
					}
				}

				// 파란돌도 마찬가지로 돌려주기.
				int br = curr.br;
				int bc = curr.bc;

				while (true) {
					br += dr[d];
					bc += dc[d];
					if (map[br][bc] == 'O') {
						break;
					}
					if (map[br][bc] == '#') {
						br -= dr[d];
						bc -= dc[d];
						break;
					}
				}

				// 파란돌과 빨간돌이 같은 위치이고 구멍이 아닐 경, 더 많 이동한애를 한 칸 뒤로 옮김.
				if (br == rr && bc == rc && map[rr][rc] != 'O') {
					int rd = Math.abs(curr.rr - rr) + Math.abs(curr.rc - rc);
					int bd = Math.abs(curr.br - br) + Math.abs(curr.bc - bc);

					// 빨간 구슬이 더 이동했을 때
					if (rd > bd) {
						rr -= dr[d];
						rc -= dc[d];
					} else {
						br -= dr[d];
						bc -= dc[d];
					}
				}

				// 최종 점을 방문처리 후 큐에 추가.
				if (!visited[rr][rc][br][bc]) {
					visited[rr][rc][br][bc] = true;
					ballPos.add(new Balls(rr, rc, br, bc, curr.cnt + 1));
				}

			}
		}

	}

}