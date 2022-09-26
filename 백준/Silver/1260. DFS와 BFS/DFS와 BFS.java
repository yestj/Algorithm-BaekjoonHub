import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M, V;
	static List<Integer>[] numList;
	static boolean[] dfsCheck;
	static boolean[] bfsCheck;

	static Queue<Integer> task = new LinkedList<>();

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();

		numList = new ArrayList[N + 1];
		dfsCheck = new boolean[N + 1];
		bfsCheck = new boolean[N + 1];

		for (int i = 0; i < N + 1; i++) {
			numList[i] = new ArrayList<>();
		}

		// 연결 입력 받기.
		for (int i = 0; i < M; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();

			numList[st].add(ed);
			numList[ed].add(st);
		}

		// 방문순서 오름차순 출력 위해 정점 정렬.
		for (int i = 1; i < N + 1; i++) {
			Collections.sort(numList[i]);
		}

		// 각 메소드 실행 후 출력.
		dfs(V);
		sb.append("\n");
		bfs(V);

		System.out.println(sb);
	}

	static void dfs(int node) {
		if (dfsCheck[node] || numList[node] == null) {
			return;
		}

		sb.append(node).append(" ");
		for (int i = 0; i < numList[node].size(); i++) {
			dfsCheck[node] = true;
			if (!dfsCheck[numList[node].get(i)]) {
				dfs(numList[node].get(i));
			}
		}

	}

	static void bfs(int node) {
		task.add(node);
		bfsCheck[node] = true;

		while (!task.isEmpty()) {
			int idx = task.poll();
			sb.append(idx).append(" ");
			for (int i = 0; i < numList[idx].size(); i++) {
				int temp = numList[idx].get(i);
				if (!bfsCheck[temp]) {
					bfsCheck[temp] = true;
					task.offer(temp);
				}
			}

		}

	}

}
