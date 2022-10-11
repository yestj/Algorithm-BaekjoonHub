import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static PriorityQueue<Node> mst;
	static int[] parent;

	static class Node implements Comparable<Node> {
		int st, ed, cost;

		public Node(int st, int ed, int cost) {
			this.st = st;
			this.ed = ed;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			return "Node [st=" + st + ", ed=" + ed + ", cost=" + cost + "]";
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt();
		int E = sc.nextInt();
		int K = sc.nextInt();

		for (int i = 1; i <= E; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();
			pq.offer(new Node(st, ed, i));
		}

		StringBuilder sb = new StringBuilder();

		for (int k = 0; k < K; k++) {
			// parent 생성 및 초기화.
			parent = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				parent[i] = i;
			}

			mst = new PriorityQueue<Node>(pq);

			int ans = 0;
			int pick = 0;

			for (int i = 0; i < pq.size(); i++) {
				Node node = mst.poll();
				int px = findSet(node.st);
				int py = findSet(node.ed);

				if (px != py) {
					union(px, py);
					ans += node.cost;
					pick++;
				}

				if (pick == (V - 1)) {
					pq.poll();
					sb.append(ans).append(" ");
					break;
				}
			}

			if (pick != (V - 1)) {
				sb.append(0).append(" ");
			}
		}

		System.out.println(sb.toString());

	}

	public static int findSet(int x) {
		if (x != parent[x]) {
			parent[x] = findSet(parent[x]);
		}
		return parent[x];
	}

	public static void union(int x, int y) {
		parent[y] = x;
	}

}