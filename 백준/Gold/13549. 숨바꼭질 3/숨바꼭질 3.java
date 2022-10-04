import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int N, K;
	static boolean[] visited;

	static class Pos {
		int curr;
		int time;

		public Pos(int curr, int time) {
			this.curr = curr;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Pos [curr=" + curr + ", time=" + time + "]";
		}
	}
	
	static int res = Integer.MAX_VALUE;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		
		bfs(N);
		System.out.println(res);

	}

	public static void bfs(int n) {

		PriorityQueue<Pos> move = new PriorityQueue<>(new Comparator<Pos>() {

			@Override
			public int compare(Pos o1, Pos o2) {
				return o1.time - o2.time;
			}

		});
		visited = new boolean[100001];

		move.add(new Pos(n, 0));

		while (!move.isEmpty()) {
			//System.out.println(move.toString());
			Pos temp = move.poll();
			visited[temp.curr] = true; 
			if (temp.curr == K) {
				res = Math.min(res, temp.time);
			}

			if (2 * (temp.curr) <= 100000 && !visited[2 * temp.curr]) {
				move.add(new Pos(2 * temp.curr, temp.time));
			}
			if (temp.curr -1 >= 0 && !visited[temp.curr - 1]) {
				move.add(new Pos(temp.curr - 1, temp.time + 1));
			}
			if (temp.curr +1 <= 100000 && !visited[temp.curr + 1]) {
				move.add(new Pos(temp.curr + 1, temp.time + 1));
			}

		}

	}

}