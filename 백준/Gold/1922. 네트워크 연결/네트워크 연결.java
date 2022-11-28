import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int N;

	static ArrayList<Node>[] adj;

	static class Node implements Comparable<Node> {
		int to;
		int cost;

		Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			return "Node [to=" + to + ", cost=" + cost + "]";
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();

		adj = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int st = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
			adj[st].add(new Node(to, cost));
			adj[to].add(new Node(st, cost));
		}

		// prim 시작.
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		int total = 0;

		pq.add(new Node(1, 0));
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (!visited[node.to]) {
				total += node.cost;
				visited[node.to] = true;
				for (int i = 0; i < adj[node.to].size(); i++) {
					int next = adj[node.to].get(i).to;
					if (!visited[next]) {
						pq.add(adj[node.to].get(i));
					}
				}
			}
		}
		System.out.println(total);

	}

}