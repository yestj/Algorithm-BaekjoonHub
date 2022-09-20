import java.util.Scanner;

public class Solution {

	static int N;
	static int[][] synergy;
	static boolean[] check;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			synergy = new int[N][N];
			check = new boolean[N];
			min = Integer.MAX_VALUE;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					synergy[r][c] = sc.nextInt();
				}
			}
			
			combi(0,0);
			System.out.println("#"+tc+" "+min);

		}

	}

	static void combi(int idx, int cnt) {
		if (cnt == N / 2) {
			diff();
			return;
		}

		for (int i = idx; i < N; i++) {
			check[i] = true;
			combi(i + 1, cnt + 1);
			check[i] = false;

		}
	}

	static void diff() {

		int A = 0;
		int B = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (check[r] && check[c]) {
					A += synergy[r][c];
				} else if (!check[r] && !check[c]) {
					B += synergy[r][c];
				}
			}
		}

		min = Math.min(Math.abs(A - B), min);
	}

}