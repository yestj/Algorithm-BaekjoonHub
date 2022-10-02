import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	// 상어와 물고기의 좌표, 사이즈를 기록할 클래스.
	static class Pos {
		int r, c, size, time;

		public Pos(int r, int c, int size, int time) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", size=" + size + ", time=" + time + "]";
		}
	}

	// 상어를 저장할 객체 생성.
	static Pos shark;

	static int M, N, res; // M: 물고기의 마리수 N: map의 크기, res: 물고기를 잡아먹을 수 있는 시간.
	static int[][] map; // 물고기의 위치와 상어의 위치를 입력 받을 배열.
	static int[][] dist; // 거리를 저장할 배열.

	// 4방 탐색(단, 위쪽, 왼쪽, 아래, 오른쪽 순서대로 방문)
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];
		dist = new int[N][N];

		// 2차원 배열 입력 받으면서, 상어 클래스 만들어주고 물고기 개수 세어주기.
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
				if (map[r][c] == 9) {
					shark = new Pos(r, c, 2, 0);
					map[r][c] = 0;
				} else if (map[r][c] != 0) {
					M++;
				}
			}
		}
		eatFish();
		System.out.println(res);

	}

	public static void eatFish() {
		PriorityQueue<Pos> move = new PriorityQueue<>(new Comparator<Pos>() {

			@Override
			public int compare(Pos o1, Pos o2) {
				if (o1.time == o2.time) {
					if (o1.r == o2.r) {
						return o1.c - o2.c;
					}
					return o1.r - o2.r;
				}
				return o1.time - o2.time;
			}

		});

		boolean[][] visited = new boolean[N][N];

		// 현재까지 먹은 물고기의 개수를 카운트할 변수 생성.
		int cnt = 0;

		move.add(shark);
		while (!move.isEmpty()) {
			Pos curr = move.poll();
			visited[curr.r][curr.c] = true;

			// 먹을 수 있는 물고기 자리라면,
			if (map[curr.r][curr.c] != 0 && map[curr.r][curr.c] < shark.size) {

				map[curr.r][curr.c] = 0;

				cnt++; // 현재까지 먹은 물고기 카운트 증가.
				res += curr.time; // 현재까지 오기 위해 이동한 시간 결과값에 더해줌.
				if (cnt == shark.size) {
					shark.size++; // 상어의 크기만큼 물고기를 먹으면 사이즈 업 시켜줌.
					cnt = 0;
				}
				// 상어 좌표 초기화 및 큐 초기화학 다시 현재의 상어 위치부터 탐색 시작..
				shark.r = curr.r;
				shark.c = curr.c;

				move.clear();
				move.add(shark);

				visited = new boolean[N][N];
				continue;
			}

			// 현재 있는 곳을 기준으로 이동할 곳의 좌표들 넣어줌.
			for (int d = 0; d < 4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					// 현재 상어의 크기보다 클 경우 이동 불가.
					if (map[nr][nc] > shark.size)
						continue;
					// 자리가 0이거나 사이즈가 같으면 이동 가능.
					if (map[nr][nc] == 0 || map[nr][nc] <= shark.size) {
						visited[nr][nc] = true;
						move.add(new Pos(nr, nc, 0, curr.time + 1));
					}
				}
			}
		}
	}

}