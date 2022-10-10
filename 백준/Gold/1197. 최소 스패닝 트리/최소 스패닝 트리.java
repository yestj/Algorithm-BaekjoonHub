import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int V, E;
	static ArrayList<Node>[] adj;

	static class Node implements Comparable<Node> {
		int st;
		int ed;
		int cost;

		public Node(int st, int ed, int cost) {
			this.st = st;
			this.ed = ed;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node [st=" + st + ", ed=" + ed + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();

		adj = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		// Node정보 입력.
		for (int i = 0; i < E; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();
			int cost = sc.nextInt();
			adj[st].add(new Node(st, ed, cost));
			adj[ed].add(new Node(ed, st, cost));

		}
		System.out.println(prim());

	}

	private static long prim() {

		boolean[] visited = new boolean[V + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 1, 0));

		long res = 0;
		int cnt = 0;

		while (!pq.isEmpty()) {

			Node node = pq.poll();
			if (visited[node.ed])
				continue;

			res += node.cost;
			visited[node.ed] = true;
			if (++cnt == V)
				return res;

			for (int i = 0; i < adj[node.ed].size(); i++) {
				Node next = adj[node.ed].get(i);
				if (visited[next.ed])
					continue;
				pq.add(next);
			}

		}

		return res;

	}

}