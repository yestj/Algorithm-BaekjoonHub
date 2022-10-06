import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String str1 = sc.next();
		String str2 = sc.next();

		// 각 문자열의 길이 저장.
		int A = str1.length();
		int B = str2.length();

		// 문자열의 각 문자를 비교하기 위한 dp 생성.
		int[][] dp = new int[A + 1][B + 1];

		int max = 0;
		StringBuilder sb = new StringBuilder();

		// for문을 돌면서, 같은 문자열을 발견할 경우 해당 자리에 1을 더해줌.
		for (int a = 1; a <= A; a++) {
			for (int b = 1; b <= B; b++) {
				if (str1.charAt(a - 1) == str2.charAt(b - 1)) {
					dp[a][b] = dp[a - 1][b - 1] + 1;
				} else {
					dp[a][b] = Math.max(dp[a - 1][b], dp[a][b - 1]);
				}
				max = Math.max(max, dp[a][b]);
			}
		}

		System.out.println(max);

//		for (int a = 0; a <= A; a++) {
//			for (int b = 0; b <= B; b++) {
//				System.out.print(dp[a][b]);
//			}
//			System.out.println();
//		}

		if (max != 0) {
			int idx = max;
			int r = A;
			int c = B;

			while (r > 0 && c > 0) {
				if (dp[r][c] == dp[r][c - 1]) {
					c--;
				} else {
					if (dp[r - 1][c] == dp[r][c]) {
						r--;
					} else {
						sb.append(str2.charAt(c - 1));
						r--;
					}
				}
			}

			System.out.println(sb.reverse().toString());
		}
	}

}