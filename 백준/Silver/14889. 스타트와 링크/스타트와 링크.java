import java.util.Scanner;

public class Main {

	static int N;
	static int[][] map;
	static boolean[] check;
	static int minDiff = 100;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];
		check = new boolean[N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		// 조합을 이용해 팀 나눠주기.
		combi(0, 0);
		System.out.println(minDiff);

	}

	static void combi(int idx, int cnt) {
		if (cnt == N / 2) {
			// 팀별 점수의 최소를 구할 메소드 실행.
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
		int teamStart = 0;
		int teamLink = 0;

		// check가 true인 경우에만 스타트팀에 더해줌
		// check가 false인 경우에만 링크팀에 더해줌
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (check[r] && check[c]) {
					teamStart += map[r][c];
				}
				if (!check[r] && !check[c]) {
					teamLink += map[r][c];
				}
			}
		}
		minDiff = Math.min(minDiff, (Math.abs(teamStart - teamLink)));

	}
}