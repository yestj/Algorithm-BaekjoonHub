import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 치즈 좌표를 가지고 있을 클래스
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] map = new int[N][M];

		int time = 0; // 녹아서 없어지는 데 걸리는 시간
		int leftCheese = 0; // 현재 남아 있는 치즈 조각 수
		int lastCheese = 0; // 1시간 전 치즈의 수

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();
				if (map[r][c] == 1) {
					leftCheese++;
					lastCheese++;
				}
			}
		}

		// 치즈 모서리를 돌며 치즈를 녹여줌
		while (leftCheese != 0) {
			Queue<Pos> noCheese = new LinkedList<>();
			boolean[][] visited = new boolean[N][M];
			noCheese.add(new Pos(0, 0));
			visited[0][0] = true;
			while (!noCheese.isEmpty()) {
				Pos curr = noCheese.poll();
				for (int d = 0; d < 4; d++) {
					int nr = curr.r + dr[d];
					int nc = curr.c + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
						continue;
					}
					if (map[nr][nc] == 0) {
						noCheese.add(new Pos(nr, nc));
						visited[nr][nc] = true;
						continue;
					}
					if (map[nr][nc] == 1) {
						map[nr][nc] = 2;
						leftCheese--;
					}
				}
			}
			if (leftCheese != 0) {
				lastCheese = leftCheese;
			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 2) {
						map[r][c] = 0;
					}
				}
			}
			time++;
		}

		System.out.println(time);
		System.out.println(lastCheese);

	}

}