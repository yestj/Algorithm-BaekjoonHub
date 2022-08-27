import java.util.HashSet;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			HashSet<Integer> nums = new HashSet<>();

			int N = sc.nextInt();
			int cycle = 1;
			int ans = 0;

			while (nums.size() < 10) {
				ans = N * cycle;
				String num = Integer.toString(ans);
				for (int i = 0; i < num.length(); i++) {
					nums.add(num.charAt(i) - '0');
				}
				cycle++;
			}
			System.out.println("#" + tc + " " + ans);
		}

	}
}