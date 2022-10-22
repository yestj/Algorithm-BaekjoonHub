import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		pq.add(sc.nextInt());
		pq.add(sc.nextInt());
		pq.add(sc.nextInt());

		StringBuilder sb = new StringBuilder();
		sb.append(pq.poll()).append(" ").append(pq.poll()).append(" ").append(pq.poll());
		System.out.println(sb.toString());

	}

}