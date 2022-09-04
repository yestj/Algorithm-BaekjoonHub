import java.util.Scanner;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	static boolean[] check;
	static int N;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {
			N = sc.nextInt();
			// 0 이 입력되면 while문 종료.
			if (N == 0) {
				break;
			}
			// N의 크기에 맞는 배열 생성.
			arr = new int[N];
			check = new boolean[N];

			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}

			lotto(0, 0);
			System.out.println(sb.toString());
			sb.setLength(0);
		}
	}

	public static void lotto(int idx, int depth) {
		if (depth == 6) {
			for (int i = 0; i < N; i++) {
				if (check[i]) {
					sb.append(arr[i] + " ");
				}
			}
			sb.append("\n");
			return;
		}

		for (int i = idx; i < N; i++) {
			check[i] = true;
			lotto(i + 1, depth + 1);
			check[i] = false;
		}
	}

}