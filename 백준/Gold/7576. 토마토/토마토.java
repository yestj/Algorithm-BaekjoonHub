import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int M, N;
	static int[][] box;
	static boolean[][] visited;
	static Queue<Tomato> grow = new LinkedList<>();

	static int res;

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		M = sc.nextInt();
		N = sc.nextInt();

		box = new int[N][M];
		visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				box[r][c] = sc.nextInt();
				if (box[r][c] == 1) {
					grow.add(new Tomato(r, c, 0));
					visited[r][c] = true;
				}
			}
		}

		res = 0;
		bfs();
		boolean flag = true;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (box[r][c] == 0) {
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			System.out.println(res);
		} else {
			System.out.println(-1);
		}

	}

	static void bfs() {
		while (!grow.isEmpty()) {
			Tomato tomato = grow.poll();

			for (int d = 0; d < 4; d++) {
				int nr = tomato.r + dr[d];
				int nc = tomato.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && box[nr][nc] == 0) {
					grow.offer(new Tomato(nr, nc, tomato.day + 1));
					box[nr][nc] = 1;
					visited[nr][nc] = true;
				}
			}
			res = tomato.day;

		}

	}
}

class Tomato {
	int r;
	int c;
	int day;

	Tomato(int r, int c, int day) {
		this.r = r;
		this.c = c;
		this.day = day;
	}
}