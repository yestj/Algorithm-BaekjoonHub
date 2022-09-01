import java.util.Scanner;

public class Main {

	static int N, M;
	static int[] nums;
	static boolean[] check;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		nums = new int[M];
		check = new boolean[N + 1];

		perm(0);

	}

	static void perm(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(nums[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (!check[i]) {
				check[i] = true;
				nums[cnt] = i;
				perm(cnt + 1);
				check[i] = false;
			}
		}

	}
}