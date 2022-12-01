import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int S = sc.nextInt();

		int[] nums = new int[N + 1];
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}

		int min = Integer.MAX_VALUE; // 길이의 최솟값을 저장할 변수.
		int st = 0; // 스타트 포인터.
		int ed = 0; // 엔드 포인터.
		int sum = 0; // 합을 저장할 변수.

		// 포인터가 끝 지점에 도달할 때까지 돌기.
		while (st <= N && ed <= N) {
			// sum이 목표한 수보다 커졌고, min이 현재 포인터간의 거리보다 클 경우 갱신.
			if (sum >= S && min > (ed - st)) {
				min = ed - st;
			}

			// sum이 목표한 수보다 작으면, end값을 차차 늘려줌.
			if (sum < S) {
				sum += nums[ed++];
			} else {
				sum -= nums[st++];
			}

		}

		if (min == Integer.MAX_VALUE)
			System.out.println("0");
		else
			System.out.println(min);

	}

}