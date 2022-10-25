import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int R, C, M;
	static int[][] map;

	static int cnt;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	// 상어를 저장할 pq.
	static PriorityQueue<Shark> pq;

	// 사이즈가 큰 순서로 움직여서, 작은애가 움직일 때 그 자리에 다른 상어가 있다면 리스트에 저장하지 않음.
	static class Shark implements Comparable<Shark> {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			return o.z - this.z;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();

		map = new int[R][C];
		pq = new PriorityQueue<>();

		for (int i = 0; i < M; i++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			int s = sc.nextInt();
			int d = sc.nextInt() - 1;
			int z = sc.nextInt();

			map[r][c] = z;
			pq.add(new Shark(r, c, s, d, z));
		}

		catchShark();
		System.out.println(cnt);

	}

	private static void catchShark() {

		for (int c = 0; c < C; c++) {
//			System.out.println("----" + c + "----");
//			System.out.println(Arrays.deepToString(map));
			for (int r = 0; r < R; r++) {
				if (map[r][c] != 0) {
//					System.out.println(map[r][c] + " " + r + " " + c);
					cnt += map[r][c];
					map[r][c] = 0;
					break;
				}
			}
			moveShark();
		}

	}

	private static void moveShark() {

		// 상어의 새로운 위치를 저장할 임시 리스트.
		ArrayList<Shark> temp = new ArrayList<>();
		int[][] tempMap = new int[R][C];

//		System.out.println(pq.toString());
		while (!pq.isEmpty()) {
			Shark shark = pq.poll();
			// 만약 해당 상어가 지워진 상어라면 컨티뉴.
			if (map[shark.r][shark.c] == 0)
				continue;
			int r = shark.r;
			int c = shark.c;
			int d = shark.d;

			int speed = shark.s;
			if (d == 0 || d == 1) {
				speed = speed % (R * 2 - 2);
			} else if (d == 2 || d == 3) {
				speed = speed % (C * 2 - 2);
			}

			for (int i = 0; i < speed; i++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
					// 만약 경계에 부딪히면 방향 바꿔주고 nr 재세팅.
					d = changeDir(d);
					nr = r + dr[d];
					nc = c + dc[d];
				}
				r = nr;
				c = nc;
			}
			// 이동을 끝내고 나면 해당자리로 상어 배치
			// 다른 애가 이미 있으면 pass. (사이즈가 큰순서로 배치되었으므로)
//			System.out.println(r+" "+c+" "+shark.z);
			if (tempMap[r][c] == 0) {
				temp.add(new Shark(r, c, shark.s, d, shark.z));
				tempMap[r][c] = shark.z;
			}
		}

		// 다돌고 나면 Pq에 list에 있는 상어들을 다 넣어주기.
		for (Shark shark : temp) {
			pq.add(shark);
		}

		for (int r = 0; r < R; r++) {
			map[r] = tempMap[r].clone();
		}

	}

	private static int changeDir(int dir) {
		switch (dir) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		}
		return -1;
	}

}