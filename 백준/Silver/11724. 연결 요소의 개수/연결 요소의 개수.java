import java.util.Scanner;

public class Main {

	static int V, E;
	static int[][] graph = new int[1001][1001];
	static boolean[] visited = new boolean[1001];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();

		for (int i = 0; i < E; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();

			graph[st][ed] = 1;
			graph[ed][st] = 1;
		}

		int result = 0;

		for (int i = 1; i <= V; i++) {
			if (!visited[i]) {
				dfs(i);
				result++;
			}
		}

		System.out.println(result);
	}

	private static void dfs(int idx) {
		if (visited[idx]) {
			return;
		}

		visited[idx] = true;
		for (int i = 1; i <= V; i++) {
			if (graph[idx][i] == 1) {
				dfs(i);
			}
		}
	}

}