import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		int[] dp = new int[K + 1];

		for (int i = 0; i < N; i++) {
			int temp = sc.nextInt();
			if (temp <= K) {
				dp[temp] += 1;
				for (int j = temp + 1; j <= K; j++) {
					dp[j] += dp[j - temp];
				}
			}
		}

		System.out.println(dp[K]);
	}

}