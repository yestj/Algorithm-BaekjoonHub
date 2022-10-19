import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] map;
	static int[][] depart;
	static Passenger[] psgArr;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Taxi implements Comparable<Taxi> {
		int r, c, usedFuel, currFuel;

		Taxi(int r, int c, int usedFuel, int currFuel) {
			this.r = r;
			this.c = c;
			this.usedFuel = usedFuel;
			this.currFuel = currFuel;
		}

		@Override
		public int compareTo(Taxi o) {
			if (this.usedFuel == o.usedFuel) {
				if (this.r == o.r) {
					return this.c - o.c;
				} else
					return this.r - o.r;
			} else {
				// 같지 않을 때는 사용한 연료가 적은 순서대로 정렬.
				return this.usedFuel - o.usedFuel;
			}
		}

		@Override
		public String toString() {
			return "Taxi [r=" + r + ", c=" + c + ", currFuel=" + currFuel + "]";
		}

	}

	static class Passenger {
		int stR, stC, edR, edC;

		Passenger(int stR, int stC, int edR, int edC) {
			this.stR = stR;
			this.stC = stC;
			this.edR = edR;
			this.edC = edC;
		}

		@Override
		public String toString() {
			return "Passenger [stR=" + stR + ", stC=" + stC + ", edR=" + edR + ", edC=" + edC + "]";
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		int fuel = sc.nextInt();

		map = new int[N][N];
		depart = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		int taxiR = sc.nextInt() - 1;
		int taxiC = sc.nextInt() - 1;

		psgArr = new Passenger[M];

		for (int i = 0; i < M; i++) {
			int stR = sc.nextInt() - 1;
			int stC = sc.nextInt() - 1;
			int edR = sc.nextInt() - 1;
			int edC = sc.nextInt() - 1;
			psgArr[i] = new Passenger(stR, stC, edR, edC);
			depart[stR][stC] = 1;
		}

		System.out.println(pickup(taxiR, taxiC, 0, fuel));

	}

	private static int pickup(int taxiR, int taxiC, int usedFuel, int currFuel) {
		PriorityQueue<Taxi> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];

		pq.add(new Taxi(taxiR, taxiC, usedFuel, currFuel));
		visited[taxiR][taxiC] = true;

		while (!pq.isEmpty()) {
			// System.out.println(pq.toString());
			Taxi taxi = pq.poll();
			// 승객이 있다면 태우고 도착지점까지 데려다주러감.
			if (depart[taxi.r][taxi.c] == 1) {
				Taxi finish = move(taxi.r, taxi.c, 0, taxi.currFuel);
				depart[taxi.r][taxi.c] = 0;
				// 도착을 잘하고 왔다면, 해당 택시를 기준으로 초기화.
				if (finish != null) {
					pq.clear();
					visited = new boolean[N][N];
					pq.add(finish);
					visited[finish.r][finish.c] = true;
					// 남은 승객이 없는지 확인
					boolean flag = true;
					check: for (int r = 0; r < N; r++) {
						for (int c = 0; c < N; c++) {
							if (depart[r][c] != 0) {
								flag = false;
								break check;
							}
						}
					}
					// 더이상 승객이 없다면, 마지막 연료 리턴.
					if (flag) {
						return finish.currFuel;
					} else
						continue;
					// 도착하는 중 문제가 발생했다면.. 실
				} else {
					return -1;
				}

			}
			// 승객이 없다면, 4방 탐색하여 승객을 마주칠 때까지 탐색.
			for (int d = 0; d < 4; d++) {
				int nr = taxi.r + dr[d];
				int nc = taxi.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] != 1) {
					// 움직일 수 있는 연료가 없으면 종료.
					if (taxi.currFuel - 1 < 0)
						return -1;
					// 아니면 움직여줌.
					visited[nr][nc] = true;
					pq.add(new Taxi(nr, nc, taxi.usedFuel + 1, taxi.currFuel - 1));
				}
			}
		}

		// 벽에 가로막혀서 끝나버린 경우.
		return -1;

	}

	private static Taxi move(int r, int c, int usedFuel, int currFuel) {
		PriorityQueue<Taxi> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];
		int tgR = -1;
		int tgC = -1;

		for (int i = 0; i < M; i++) {
			if (psgArr[i].stR == r && psgArr[i].stC == c) {
				tgR = psgArr[i].edR;
				tgC = psgArr[i].edC;
			}
		}

		pq.add(new Taxi(r, c, usedFuel, currFuel));
		visited[r][c] = true;

		while (!pq.isEmpty()) {
			Taxi taxi = pq.poll();
			if (taxi.r == tgR && taxi.c == tgC) {
				return new Taxi(taxi.r, taxi.c, 0, taxi.usedFuel * 2 + taxi.currFuel);
			}

			for (int d = 0; d < 4; d++) {
				int nr = taxi.r + dr[d];
				int nc = taxi.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] != 1) {
					// 움직일 수 있는 연료가 없으면 종료.
					if (taxi.currFuel - 1 < 0)
						continue;
					// 아니면 움직여줌.
					visited[nr][nc] = true;
					pq.add(new Taxi(nr, nc, taxi.usedFuel + 1, taxi.currFuel - 1));
				}
			}
		}

		return null;
	}
}