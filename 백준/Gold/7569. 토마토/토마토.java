import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int M, N, H;
	static int[][][] box;
	static boolean[][][] visited;
	static Queue<Tomato> grow = new LinkedList<>();

	static int res;

	static int[] dr = { 1, -1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, 1, -1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();

		box = new int[N][M][H];
		visited = new boolean[N][M][H];

		for (int h = 0; h < H; h++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					box[r][c][h] = sc.nextInt();
					if (box[r][c][h] == 1) {
						grow.add(new Tomato(r, c, h, 0));
						visited[r][c][h] = true;
					}
				}
			}
		}

		res = 0;
		bfs();
		boolean flag = true;
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (box[r][c][h] == 0) {
						flag = false;
						break;
					}
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

			for (int d = 0; d < 6; d++) {
				int nr = tomato.r + dr[d];
				int nc = tomato.c + dc[d];
				int nh = tomato.h + dh[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && nh >= 0 && nh < H && !visited[nr][nc][nh]
						&& box[nr][nc][nh] == 0) {
					grow.offer(new Tomato(nr, nc, nh, tomato.day + 1));
					box[nr][nc][nh] = 1;
					visited[nr][nc][nh] = true;
				}
			}
			res = tomato.day;

		}

	}
}

class Tomato {
	int r;
	int c;
	int h;
	int day;

	Tomato(int r, int c, int h, int day) {
		this.r = r;
		this.c = c;
		this.h = h;
		this.day = day;
	}
}