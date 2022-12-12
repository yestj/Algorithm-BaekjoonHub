import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 위상정렬 
 */
public class Main {

	static int N, tg; // 건물의 개수와 지어야 할 최종 빌딩의 번호
	static ArrayList<Integer>[] adj; // 건물간의 연결성을 저장할 리스트
	static int[] leadTime; // 각 빌딩별로 건설에 필요한 시간
	static int[] indegree; // 진입차수 저장
	static int[] dp; // 각 위치까지 빌딩을 짓는 시간 최댓값 저장

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt(); // 테스트 케이스

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 건물 개수
			int K = sc.nextInt(); // 규칙 개수

			adj = new ArrayList[N + 1]; // 각 건물을 짓기 전에 지어야 할 건물들을 저장
			for (int i = 0; i <= N; i++) {
				adj[i] = new ArrayList<>();
			}

			// 각 건물을 짓는데 걸리는 시간 저장
			leadTime = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				leadTime[i] = sc.nextInt();
			}

			indegree = new int[N + 1];

			// 연결관계 저장
			for (int i = 0; i < K; i++) {
				int before = sc.nextInt();
				int after = sc.nextInt();
				adj[before].add(after);
				indegree[after]++;
			}

			tg = sc.nextInt();
			dp = new int[N + 1];
			topologySort();
			System.out.println(dp[tg]);

		}
	}

	private static void topologySort() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < indegree.length; i++) {
			// 0차수일 경우, dp에 해당 값의 리드타임을 넣어준다.
			if (indegree[i] == 0) {
				dp[i] = leadTime[i];
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int i = 0; i < adj[curr].size(); i++) {
				int next = adj[curr].get(i);
				dp[next] = Math.max(dp[curr] + leadTime[next], dp[next]);
				indegree[next]--;
				if (indegree[next] == 0)
					q.offer(next);
			}
		}

	}

}