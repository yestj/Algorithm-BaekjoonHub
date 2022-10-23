import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 기관차가 끌고가던 객실수
		int[] passenger = new int[N + 1]; // 각 칸의 승객수
		for (int i = 1; i <= N; i++) {
			passenger[i] = sc.nextInt();
		}
		int S = sc.nextInt(); // 소형 기관차가 끌고갈 수 있는 객실 수

		int[][] dp = new int[4][N + 1];

		// 누적합을 구해 객차들 사이의 승객합을 쉽게 구하게 함.
		// ex. 2번째부터 3번째까지의 승객합을 구할 때 => sum[3] - sum[1]
		int[] sum = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			sum[i] += sum[i - 1] + passenger[i];
		}

		// 열차 하나씩 돌며, 태울 수 있는 최대운송인원을 구해 저장
		// 1. 해당 열차에서 이전 칸까지 태울 수 있는 승객의 최댓값
		// 2. 해당 열차에서 직전 칸에 사람을 태우지 않고 이번 칸에서 사람을 태운다고 할때의 최댓값
		// 1, 2를 비교하여 최댓값을 DP에 저장.
		for (int i = 1; i <= 3; i++) {
			for (int j = i * S; j <= N; j++) {
				dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - S] + (sum[j] - sum[j - S]));
			}
		}

		System.out.println(dp[3][N]);

	}
}