import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] map;
	static boolean[][][] visited;

	static int min = Integer.MAX_VALUE;

	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class Node implements Comparable<Node> {
		int r, c, cnt, smash;

		Node(int r, int c, int cnt, int smash) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.smash = smash;
		}

		@Override
		public int compareTo(Node o) {
			return this.cnt - o.cnt;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", cnt=" + cnt + ", smash=" + smash + "]";
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			String temp = sc.next();
			for (int c = 0; c < M; c++) {
				map[r][c] = temp.charAt(c) - '0';
			}
		}

		visited = new boolean[N][M][2];
		min = Math.min(findWay(0, 0, 1, 0), min);

		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}

	public static int findWay(int r, int c, int cnt, int smash) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(r, c, cnt, smash));
		visited[r][c][0] = true;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int row = node.r;
			int col = node.c;
			// 끝 점까지 도달했다면, 현재까지 cnt된 아이들을 반환.
			if (row == N - 1 && col == M - 1) {
				return node.cnt;
			}

			// 아니라면 사방 탐색하여 벽이 아닌 곳으로 이동.
			for (int d = 0; d < 4; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					// 0을 만났을 경우
					if (map[nr][nc] == 0 && !visited[nr][nc][node.smash]) {
						pq.add(new Node(nr, nc, node.cnt + 1, node.smash));
						visited[nr][nc][node.smash] = true;
					}
					// 1을 만났고, 벽을 부술 수 있는 경우.
					if (map[nr][nc] == 1 && node.smash == 0) {
						pq.add(new Node(nr, nc, node.cnt + 1, 1));
						visited[nr][nc][1] = true;
					}
				}
			}
		}

		return Integer.MAX_VALUE;
	}

}