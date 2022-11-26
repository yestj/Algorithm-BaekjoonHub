import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] map;
	static int numIsl; // 섬의 개수

	static int[] parents;

	static PriorityQueue<Island> pq = new PriorityQueue<>();

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Island implements Comparable<Island> {
		int from, to, d;

		public Island(int from, int to, int d) {
			this.from = from;
			this.to = to;
			this.d = d;
		}

		@Override
		public int compareTo(Island o) {
			return this.d - o.d;
		}

		@Override
		public String toString() {
			return "Island [from=" + from + ", to=" + to + ", d=" + d + "]";
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

		islandNum();
		findConn();

		// 크루스갈
		// 1. 부모배열을 자기 자신으로 초기화.
		parents = new int[numIsl];
		for (int i = 0; i < numIsl; i++) {
			parents[i] = i;
		}

		int res = 0;
		int bridge = 0;
		int size = pq.size();
		for (int i = 0; i < size; i++) {
			Island curr = pq.poll();
			if (find(curr.from) != find(curr.to)) {
				union(curr.from, curr.to);
				res += curr.d;
				bridge++;
			}
		}

		if (bridge == 0 || bridge != numIsl - 2) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}
	}

	// 섬에 번호 붙여주기.
	// 섬들의 넘버링해주기.
	private static void islandNum() {

		int cnt = 1;
		boolean[][] visited = new boolean[N][M];

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, 1, -1 };

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 1 && !visited[r][c]) {
					Queue<Pos> temp = new LinkedList<>();
					map[r][c] = cnt;
					temp.add(new Pos(r, c));
					visited[r][c] = true;
					while (!temp.isEmpty()) {
						Pos pos = temp.poll();
						for (int d = 0; d < 4; d++) {
							int nr = pos.r + dr[d];
							int nc = pos.c + dc[d];
							if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] == 1) {
								map[nr][nc] = cnt;
								temp.add(new Pos(nr, nc));
								visited[nr][nc] = true;
							}
						}
					}
					cnt++;
				}
			}
		}

		numIsl = cnt;
	}

	// 연결 가능한 다리 연결하여 bridge PQ에 넣어주기.
	private static void findConn() {

		int[] dr = { 1, -1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				// 섬을 만나면,
				if (map[r][c] > 0 && map[r][c] <= 6) {
					int curr = map[r][c]; // 현재 섬의 번호
					for (int d = 0; d < 4; d++) {
						int cnt = 0;
						int tempR = r;
						int tempC = c;
						while (true) {
							int nr = tempR + dr[d];
							int nc = tempC + dc[d];
							if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == curr)
								break;

							if (map[nr][nc] == 0) {
								cnt++;
								tempR = nr;
								tempC = nc;
								continue;
							}

							if (map[nr][nc] > 0 && map[nr][nc] <= 6) {
								// 연결 정보 넣어주기
								if (cnt >= 2) {
									pq.add(new Island(curr, map[nr][nc], cnt));
								}
								break;

							}
						}
					}
				}
			}
		}
	}

	// 크루스갈, 부모 노드 찾기
	private static int find(int n) {
		if (parents[n] == n) {
			return n;
		}
		return parents[n] = find(parents[n]);
	}

	// 크루스갈 합치기.
	public static void union(int i, int j) {
		int pi = find(i);
		int pj = find(j);
		if (pi < pj) {
			parents[pj] = pi;
		} else {
			parents[pj] = pi;
		}
	}

}