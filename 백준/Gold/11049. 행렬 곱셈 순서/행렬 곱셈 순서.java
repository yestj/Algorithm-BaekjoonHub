import java.util.Scanner;

public class Main {

	static int N;
	static int[][] matrix;
	static int[][] dp;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		// 행렬을 저장할 배열 생성 및 입력.
		matrix = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			matrix[i][0] = sc.nextInt();
			matrix[i][1] = sc.nextInt();
		}

		/*
		 * 분할 정복 방법 사용. left, right로 가장 작은 상태로 쪼개어 최솟값인 경우를 저장하여 올라올 수 있도록 하기.
		 */

		// dp생성 후 무한대로 값 초기화.
		dp = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				dp[r][c] = Integer.MAX_VALUE;
			}
		}

		findBest(1, N);
		System.out.println(dp[1][N]);

	}

	static int findBest(int start, int end) {

		// 자기 자신일 때는 0 값을 집어 넣음.
		if (start == end) {
			return 0;
		}

		// 갱신된 적 있으면 그냥 지나감.
		if (dp[start][end] != Integer.MAX_VALUE) {
			return dp[start][end];
		}

		int left, right;
		for (int i = start; i < end; i++) {
			left = findBest(start, i);
			right = findBest(i + 1, end);
			dp[start][end] = Math.min(dp[start][end], left + right + matrix[start][0] * matrix[i][1] * matrix[end][1]);
		}

		return dp[start][end];

	}

}