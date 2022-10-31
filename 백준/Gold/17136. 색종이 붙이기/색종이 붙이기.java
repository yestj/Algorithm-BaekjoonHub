import java.util.Scanner;

public class Main {

	static int[][] map;
	static int[] paper = { 0, 5, 5, 5, 5, 5 };
	static int res = Integer.MAX_VALUE;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		map = new int[10][10];

		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		// dfs를 통해 색종이를 다 붙여봄..
		dfs(0, 0, 0);

		if (res == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}

	}

	private static void dfs(int r, int c, int cnt) {
		// 끝 점에 도달하면 종료.
		if (r >= 9 && c > 9) {
			res = Math.min(cnt, res);
			return;
		}

		// cnt가 res보다 크면 바로 종료.
		if (cnt >= res) {
			return;
		}

		// 한 줄을 다 탐색했으면 다음줄로 넘어가기.
		if (c > 9) {
			dfs(r + 1, 0, cnt);
			return;
		}

		// 1일 경우, 가장 큰 색종이부터 붙여봄.
		if (map[r][c] == 1) {
			for (int i = 5; i > 0; i--) {
				if (paper[i] > 0 && attachable(r, c, i)) {
					attach(r, c, i, 0); // 색종이 붙이기
					paper[i]--;
					dfs(r, c + 1, cnt + 1);
					// 다시 돌아왔다면.. 색종이 떼주기!
					attach(r, c, i, 1);
					paper[i]++;
				}
			}
			// 색종이를 붙일 수 없는 경우, 다음으로 넘어감.
		} else {
			dfs(r, c + 1, cnt);
		}

	}

	private static void attach(int r, int c, int length, int value) {
		for (int i = r; i < r + length; i++) {
			for (int j = c; j < c + length; j++) {
				map[i][j] = value;
			}
		}
	}

	private static boolean attachable(int r, int c, int length) {
		for (int i = r; i < r + length; i++) {
			for (int j = c; j < c + length; j++) {
				if (i < 0 || i >= 10 || j < 0 || j >= 10 || map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

}