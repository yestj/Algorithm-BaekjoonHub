import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	// DFS로 리프노드까지 찾은 뒤에 다시 위로 올라오면서 카운팅.
	// 카운팅하면서 D와 비교하여 몇 번째 노드까지 제할 수 있는지 확인.

	static int D; // 힘의 값(하나의 노드에서 자동으로 전단지가 배송되는 노드의 개수.)
	static ArrayList<Integer>[] adj; // 연결된 노드들을 저장할 인접 리스트.
	static boolean[] visited; // 방문체크.
	static int res = 0; // 최종 결과 저장.

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 노드의 개수.
		int S = Integer.parseInt(st.nextToken()); // 첫 번째 시작 노드.

		D = Integer.parseInt(st.nextToken());

		// 인접리스트 초기화.
		adj = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		visited = new boolean[N + 1];

		// 연결된 노드 정보 저장하기.
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			adj[s].add(e);
			adj[e].add(s);
		}

		// 시작 노드부터 DFS 탐색진행.
		visited[S] = true;
		dfs(S);

		System.out.println(Math.max(0, (res - 1) * 2)); // 결과 출력.
	}

	private static int dfs(int node) {
		int depth = 0; // 현재 노드에서 리프노드까지의 depth를 측정.
		for (int next : adj[node]) {
			if (visited[next])
				continue; // 방문한적 있으면 지나가기.
			visited[next] = true; // 방문체크.
			depth = Math.max(depth, dfs(next)); // dfs탐색진행.
		}
		if (depth >= D)
			res++; // 만약 depth가 D보다 클 경우에는 정답에 더해줌.
		return depth + 1; // 리프노드에서부터 1, 2, 3, ... 순서대로 던지게 됨.

	}
}