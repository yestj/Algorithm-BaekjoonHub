import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] dp = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int max = arr[0];
		dp[0] = arr[0];

		for (int i = 1; i < N; i++) {
			dp[i] = arr[i];
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], arr[i] + dp[j]);
				}
			}
			max = Math.max(max, dp[i]);
		}

		System.out.println(max);
	}

}
