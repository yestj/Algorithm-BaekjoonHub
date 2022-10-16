import java.util.Scanner;

public class Solution {

	static int N; // 숫자의 개수.
	static int[] opNums; // 연산자 개수를 입력받을 배열.
	static int[] nums; // 숫자를 입력받을 배열.

	static int[] opPick; // 선택된 연산자를 나열할 배열.

	static int max; // 연산 최댓값
	static int min; // 연산 최솟값

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			opNums = new int[4];
			nums = new int[N];
			opPick = new int[N - 1];

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			// 연산 횟수 입력 받기.
			for (int i = 0; i < 4; i++) {
				opNums[i] = sc.nextInt();
			}

			// 숫자 입력 받기.
			for (int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();
			}

			perm(0);
			System.out.println("#" + tc + " " + (max - min));

		}

	}

	// 연산의 경우의 수 구하기. (순열)
	static void perm(int cnt) {
		if (cnt == N - 1) {
			// 다 뽑았으면 계산하기.
			cal();
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (opNums[i] > 0) {
				opPick[cnt] = i;
				opNums[i]--;
				perm(cnt + 1);
				opNums[i]++;
			}
		}

	}

	// 현재까지 뽑은 연산으로 계산 진행하기.
	static void cal() {

		int res = nums[0];
		for (int i = 0; i < N - 1; i++) {
			int op = opPick[i];
			switch (op) {
			case 0:
				res += nums[i + 1];
				break;
			case 1:
				res -= nums[i + 1];
				break;
			case 2:
				res *= nums[i + 1];
				break;
			case 3:
				res /= nums[i + 1];
				break;
			}
		}

		min = Math.min(min, res);
		max = Math.max(max, res);

	}

}