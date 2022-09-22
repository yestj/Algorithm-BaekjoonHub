import java.util.Arrays;
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
				dp[temp] = 1;
				for (int j = temp + 1; j <= K; j++) {
					// 1. j가 temp의 배수일 경우
					if (j % temp == 0) {
						// 1-1. dp[j]가 0일 경우
						if (dp[j] == 0) {
							dp[j] = j / temp;
						}
						// 1-2. dp[j]가 0이 아닐 경우
						else {
							dp[j] = Math.min(dp[j], j / temp);
						}
						dp[j] = Math.min(dp[j], dp[temp] + dp[j - temp]);
					} else {
						// 2. j가 temp의 배수가 아닐 경우
						// dp[temp], dp[j-temp]하나라도 0이면 가능한 숫자가 없는 것이므로 실행x
						if (dp[temp] != 0 && dp[j - temp] != 0) {
							// 2-1. dp[j]가 0이 아닐 경우
							if (dp[j] != 0) {
								dp[j] = Math.min(dp[j], dp[temp] + dp[j - temp]);
								// 2-2. dp[j]가 0일 경우
							} else {
								dp[j] = dp[temp] + dp[j - temp];
							}
						}
					}
				}
			}
		}

		if (dp[K] == 0)
			System.out.println("-1");
		else
			System.out.println(dp[K]);

	}
}