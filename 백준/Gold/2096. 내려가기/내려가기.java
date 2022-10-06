import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] map = new int[N][3];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < 3; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		int[][] minDP = new int[N][3];
		int[][] maxDP = new int[N][3];

		int[] dc = { -1, 0, 1 };

		// 첫 번째 행의 값 초기화.
		for (int c = 0; c < 3; c++) {
			minDP[0][c] = map[0][c];
			maxDP[0][c] = map[0][c];
		}

		for (int r = 1; r < N; r++) {
			for (int c = 0; c < 3; c++) {
				int min = Integer.MAX_VALUE;
				int max = Integer.MIN_VALUE;
				for (int d = 0; d < 3; d++) {
					int pc = c + dc[d];
					if (pc >= 0 && pc < 3) {
						min = Math.min(min, minDP[r - 1][pc]);
						max = Math.max(max, maxDP[r - 1][pc]);
					}
				}
				minDP[r][c] = map[r][c] + min;
				maxDP[r][c] = map[r][c] + max;
			}
		}

		// 최대값 최소값 찾기
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int c = 0; c < 3; c++) {
			max = Math.max(maxDP[N - 1][c], max);
			min = Math.min(minDP[N - 1][c], min);
		}

		System.out.println(max + " " + min);

	}

}
