import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	// 우, 하, 좌, 상 순서.
	static int d;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

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
		int K = sc.nextInt();

		int[][] map = new int[N][N];

		// 사과의 값(2) 입력 받기.
		for (int i = 0; i < K; i++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			map[r][c] = 2;
		}

		Deque<Pos> dq = new LinkedList<>();

		dq.add(new Pos(0, 0));
		// 맵에 뱀의 현재 위치 표시(1)
		map[0][0] = 1;

		int cnt = 1; // 이동횟수.

		int L = sc.nextInt();
		for (int i = 0; i < L; i++) {
			int X = sc.nextInt();
			String dir = sc.next();
			boolean flag = true;
			while (cnt <= X) {
				// System.out.println(Arrays.deepToString(map));
				cnt++;
				// dq의 앞부분을 머리, 뒷 부분을 꼬리라고 가정.
				// 현재 머리 부분을 기준으로 다음 이동할 방향으로 머리를 늘려줌.
				int nr = dq.peekFirst().r + dr[d];
				int nc = dq.peekFirst().c + dc[d];
				// 머리를 늘리려는 방향이 벽이거나 자신이 있는 곳이면 종료.
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1) {
					flag = false;
					cnt--;
					break;
				}
				// 머리를 늘렸는데 사과가 있다면, 사과를 없애고 꼬리는 그대로.
				if (map[nr][nc] == 2) {
					map[nr][nc] = 1;
					dq.addFirst(new Pos(nr, nc));
				} else {
					map[nr][nc] = 1;
					dq.addFirst(new Pos(nr, nc));
					map[dq.peekLast().r][dq.peekLast().c] = 0;
					dq.removeLast();
				}
			}
			if (!flag)
				break;
			else
				changeDir(dir);
		}
		while (true) {
			// System.out.println(Arrays.deepToString(map));
			cnt++;
			// dq의 앞부분을 머리, 뒷 부분을 꼬리라고 가정.
			// 현재 머리 부분을 기준으로 다음 이동할 방향으로 머리를 늘려줌.
			int nr = dq.peekFirst().r + dr[d];
			int nc = dq.peekFirst().c + dc[d];
			// 머리를 늘리려는 방향이 벽이거나 자신이 있는 곳이면 종료.
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1) {
				cnt--;
				break;
			}
			// 머리를 늘렸는데 사과가 있다면, 사과를 없애고 꼬리는 그대로.
			if (map[nr][nc] == 2) {
				map[nr][nc] = 1;
				dq.addFirst(new Pos(nr, nc));
			} else {
				map[nr][nc] = 1;
				dq.addFirst(new Pos(nr, nc));
				map[dq.peekLast().r][dq.peekLast().c] = 0;
				dq.removeLast();
			}
		}

		System.out.println(cnt);

	}

	private static void changeDir(String dir) {
		switch (dir) {
		case "D":
			if (d + 1 > 3)
				d = 0;
			else
				d += 1;
			break;
		case "L":
			if (d - 1 < 0)
				d = 3;
			else
				d -= 1;
			break;
		}

	}

}