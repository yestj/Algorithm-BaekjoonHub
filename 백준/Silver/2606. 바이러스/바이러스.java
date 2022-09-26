import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int V, E;
	static int res;

	static List<Integer>[] computer;
	static boolean[] check;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();

		computer = new ArrayList[V + 1];
		check = new boolean[V + 1];

		for (int i = 0; i < V + 1; i++) {
			computer[i] = new ArrayList<>();
		}

		// 연결 입력 받기.
		for (int i = 0; i < E; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();

			computer[st].add(ed);
			computer[ed].add(st);
		}

		dfs(1);
		System.out.println(res);

	}

	static void dfs(int node) {
		if (check[node] || computer[node] == null) {
			return;
		}

		for (int i = 0; i < computer[node].size(); i++) {
			check[node] = true;
			if (!check[computer[node].get(i)]) {
				res++;
				dfs(computer[node].get(i));
			}
		}

	}

}
