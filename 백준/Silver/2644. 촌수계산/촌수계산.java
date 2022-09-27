import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static ArrayList<Integer>[] people;
	static boolean[] visited;

	static int st, tg;
	static int cnt, res;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 사람의 수 입력 받기.
		int N = sc.nextInt();
		people = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i < N + 1; i++) {
			people[i] = new ArrayList<>();
		}

		// 시작 값과 목표 값 입력 받기.
		st = sc.nextInt();
		tg = sc.nextInt();

		// 관계 저장하기.
		int M = sc.nextInt();
		for (int m = 0; m < M; m++) {
			int p = sc.nextInt();
			int c = sc.nextInt();
			people[p].add(c);
			people[c].add(p);
		}

		// dfs탐색
		res = -1;
		dfs(st);
		System.out.println(res);

	}

	static void dfs(int N) {
		if (N == tg) {
			res = cnt;
			return;
		}

		if (people[N] == null) {
			return;
		}

		visited[N] = true;
		cnt++;
		for (int i = 0; i < people[N].size(); i++) {
			if (!visited[people[N].get(i)]) {
				dfs(people[N].get(i));
				cnt--;
			}
		}
	}

}