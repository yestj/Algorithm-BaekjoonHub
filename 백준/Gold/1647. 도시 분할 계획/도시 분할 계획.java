import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

// prim 이용. (간선의 수가 더 많으므로) 
public class Main {

	static int res; // 결과값 저장.

	static boolean[] visited; // 방문체크
	static ArrayList<Node>[] adj; // 인접리스트

	// 연결 정보를 저장할 노드 생성
	static class Node implements Comparable<Node> {
		int to;
		int cost;

		public Node(int to, int cost) {
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

		int N = sc.nextInt();
		int M = sc.nextInt();

		visited = new boolean[N + 1];
		adj = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();
			int cost = sc.nextInt();
			adj[st].add(new Node(ed, cost));
			adj[ed].add(new Node(st, cost));
		}

		prim();
		System.out.println(res);

	}

	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.add(new Node(1, 0));
		int max = 0; // 간선 중 가장 cost값이 큰 간선 값 저장.

		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (!visited[curr.to]) {
				visited[curr.to] = true;
			} else {
				continue;
			}

			max = Math.max(max, curr.cost);
			res += curr.cost;

			for (int i = 0; i < adj[curr.to].size(); i++) {
				Node next = adj[curr.to].get(i);
				if (!visited[next.to]) {
					pq.add(next);
				}
			}
		}

		res -= max;

	}
}