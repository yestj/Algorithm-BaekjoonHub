import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] arr1 = new int[N][M];
		int[][] arr2 = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				arr1[r][c] = sc.nextInt();
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				arr2[r][c] = sc.nextInt();
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(arr1[r][c] + arr2[r][c]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
}