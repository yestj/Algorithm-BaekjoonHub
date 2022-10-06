import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		/*
		 * maxHeap에 중앙값을 기준으로 앞의 숫자를 담고, minHeap에 중앙값을 기준으로 뒤의 숫자를 담을 수 있도록 변경 [규칙] 1.
		 * maxHeap.size == minHeap.size 일 경우, maxHeap에 다음 숫자를 넣음. 2. maxHeap의 peek가
		 * minHeap의 피크보다 크면 둘을 swap 3. maxHeap의 peek는 항상 중앙값임.
		 */
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

		// 결과 값을 저장.
		StringBuilder res = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			int input = sc.nextInt();
			// 1. 숫자를 힙에 넣어 주기.
			if (maxHeap.size() == minHeap.size()) {
				maxHeap.add(input);
			} else {
				minHeap.add(input);
			}
			// 2. peek 비교 하여 바꿔주기.
			if (minHeap.size() != 0 && maxHeap.size() != 0) {
				while (minHeap.peek() < maxHeap.peek()) {
					int temp = minHeap.poll();
					minHeap.add(maxHeap.poll());
					maxHeap.add(temp);
				}
			}

			// 3. peek 값 결과에 저장해주기.
			res.append(maxHeap.peek()).append("\n");

		}

		System.out.println(res.toString());

	}

}
