import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	// F: 건물의 총 높이, S: 현재 나의 위치 G: 목표 위치 U: 위로 몇 층? D: 아래로 몇 층?
	static int F, S, G, U, D;

	// 결과값을 저장할 변수.
	static int res;

	// 연산 과정에서 현재 위치와 버튼을 누른 횟수를 저장할 클래스 생성.
	static class Move {

		int curr, cnt; // curr: 현재 위치 cnt: 버튼을 누른 횟수.

		Move(int curr, int cnt) {
			this.curr = curr;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Move [curr=" + curr + ", cnt=" + cnt + "]";
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		F = sc.nextInt();
		S = sc.nextInt();
		G = sc.nextInt();
		U = sc.nextInt();
		D = sc.nextInt();

		res = Integer.MAX_VALUE;
		bfs(S);

		if (res == Integer.MAX_VALUE) {
			System.out.println("use the stairs");
		} else {
			System.out.println(res);
		}

	}

	private static void bfs(int n) {

		Queue<Move> moveList = new LinkedList<>();
		// 현재 층 방문 처리.
		boolean[] visited = new boolean[F + 1];

		moveList.add(new Move(n, 0));
		visited[n] = true;

		while (!moveList.isEmpty()) {
			// System.out.println(moveList.toString());
			Move move = moveList.poll();
			if (move.curr == G) {
				res = Math.min(move.cnt, res);
				break;
			} else {
				if (U != 0 && move.curr + U <= F && !visited[move.curr + U]) {
					visited[move.curr + U] = true;
					moveList.add(new Move(move.curr + U, move.cnt + 1));
				}
				if (D != 0 && move.curr - D > 0 && !visited[move.curr - D]) {
					visited[move.curr - D] = true;
					moveList.add(new Move(move.curr - D, move.cnt + 1));
				}
			}

		}

	}

}