import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		// 무게를 저장할 배열
		int[] W = new int[N + 1];
		// 가치를 저장할 배열
		int[] V = new int[N + 1];
		// 무게별 가치 최대값을 저장할 배열
		int[] dp = new int[K + 1];

		for (int i = 1; i <= N; i++) {
			W[i] = sc.nextInt();
			V[i] = sc.nextInt();
		}

		for (int i = 1; i <= N; i++) {
			for (int j = K; j - W[i] >= 0; j--) {
				dp[j] = Math.max(dp[j], dp[j - W[i]] + V[i]);
			}
		}

		System.out.println(dp[K]);
	}

}
