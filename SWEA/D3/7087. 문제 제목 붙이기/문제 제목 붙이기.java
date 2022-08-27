import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			char[] title = new char[N];
			for (int i = 0; i < N; i++) {
				title[i] = sc.next().charAt(0);
			}

			boolean flag = true;

			int cnt = 0;
			int alphabet = 65;
			while (flag) {
				for (int i = 0; i < N; i++) {
					if (alphabet == (int) title[i]) {
						cnt++;
						alphabet++;
						break;
					}
					if (i == N - 1) {
						flag = false;
					}
				}
			}
			
			System.out.println("#"+tc+" "+cnt);

		}
	}

}