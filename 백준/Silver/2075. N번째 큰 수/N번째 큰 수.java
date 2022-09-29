import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] map = new int[N][N];

		PriorityQueue<Integer> num = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < N * N; i++) {
			num.add(sc.nextInt());
		}

		for (int i = 0; i < N - 1; i++) {
			num.poll();
		}

		System.out.println(num.poll());

	}
}
