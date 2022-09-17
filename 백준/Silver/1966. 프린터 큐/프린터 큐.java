import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			Queue<Integer> docs = new LinkedList<>();
			Queue<Integer> prior = new LinkedList<>();

			int N = sc.nextInt();
			int idx = sc.nextInt();
			int cnt = 0;

			for (int i = 0; i < N; i++) {
				prior.add(sc.nextInt());
				docs.add(i);
			}
			int nextPeek = 0;

			test: for (int i = 9; i > 0; i--) {

				while (docs.peek() != nextPeek) {
					prior.add(prior.poll());
					docs.add(docs.poll());
				}
				
				while(prior.peek()== i) {
					prior.poll();
					cnt++;
					if (docs.poll() == idx) {
						break test;
					}
					nextPeek = docs.peek();
				}
				
				int tempPeek = docs.peek();
				do {
					if (prior.peek() == i) {
						prior.poll();
						cnt++;
						if (docs.poll() == idx) {
							break test;
						}
						nextPeek = docs.peek();
					} else {
						prior.add(prior.poll());
						docs.add(docs.poll());
					}
				} while (tempPeek != docs.peek());

			}
			System.out.println(cnt);

		}

	}

}