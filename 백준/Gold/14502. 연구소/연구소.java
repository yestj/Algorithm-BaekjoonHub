import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] map;
	static int[][] copyMap;

	static int maxRes;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		makeWall(0);

		System.out.println(maxRes);

	}

	static void makeWall(int cnt) {
		if (cnt == 3) {
			spreadVirus();
			return;
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0) {
					map[r][c] = 1;
					makeWall(cnt + 1);
					map[r][c] = 0;
				}
			}
		}

	}

	static void spreadVirus() {

		// 바이러스의 위치 좌표 저장.
		Queue<Node> virus = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 2) {
					virus.add(new Node(r, c));
				}
			}
		}

		// 맵 카피하기.
		copyMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			copyMap[i] = map[i].clone();
		}

		while (!virus.isEmpty()) {
			Node curr = virus.poll();
			int row = curr.r;
			int col = curr.c;

			for (int d = 0; d < 4; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (copyMap[nr][nc] == 0) {
						virus.add(new Node(nr, nc));
						copyMap[nr][nc] = 2;
					}
				}
			}
		}

		// 안전영역의 개수 확인하기.
		int res = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (copyMap[r][c] == 0) {
					res++;
				}
			}
		}
		maxRes = Math.max(res, maxRes);
	}

}