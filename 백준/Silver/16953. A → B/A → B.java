import java.util.Scanner;

public class Main {

	static long st, tg;
	// 연산 횟수를 카운트할 변수 생성.
	static int cnt, min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		st = sc.nextLong();
		tg = sc.nextLong();

		min = Integer.MAX_VALUE;
		dfs(st);
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}

	}

	private static void dfs(long n) {
		cnt++;
		// tg숫자에 도달하면 현재까지 카운트 한 횟수와 min값 비교하여 최솟값을 넣어줌.
		if (n == tg) {
			min = Math.min(cnt, min);
			return;
		}
		// tg숫자를 넘어가면 종료.
		if (n > tg) {
			return;
		}

		// n이 tg보다 작으면 두 가지 연산을 해 줌.
		dfs(2 * n);
		cnt--;
		dfs(10 * n + 1);
		cnt--;
	}
}