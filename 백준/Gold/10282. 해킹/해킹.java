import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int N; // 컴퓨터 개수(정점)
	static int D; // 의존성 개수(간선)
	static int C; // 감염된 컴퓨터 번호(시작 정점)

	// 의존성을 저장할 리스트 생성.
	static ArrayList<Computer>[] comList;

	// 방문체크 할 배열 생성
	static boolean[] visited;

	static int resCnt; // 최종 감염된 컴퓨터의 개수
	static int resTime; // 감염되는데 걸린 최종 시간

	// 컴퓨터의 의존성과, 감염되는데 걸리는 시간, 감염이 끝나고 난 후의 시간을 저장할 클래스 생성.
	static class Computer implements Comparable<Computer> {
		int to; // 어느 컴퓨터로 퍼지는지.
		int time; // 얼마나 걸리는지.
		int gotVirus; // 감염이 다되었을 때의 시간.

		Computer(int to, int time, int gotVirus) {
			this.to = to;
			this.time = time;
			this.gotVirus = gotVirus;
		}

		@Override
		public int compareTo(Computer o) {
			return this.gotVirus - o.gotVirus;
		}

		@Override
		public String toString() {
			return "Computer [to=" + to + ", time=" + time + ", gotVirus=" + gotVirus + "]";
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			D = sc.nextInt();
			C = sc.nextInt();

			// 리스트 초기화.
			comList = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				comList[i] = new ArrayList<>();
			}

			visited = new boolean[N + 1];
			resCnt = 0;
			resTime = 0;

			// 의존성 넣기. 이 때, 예상 소요시간은 바이러스가 퍼지는 시간으로 함께 초기화 해 줌.
			for (int i = 0; i < D; i++) {
				int ed = sc.nextInt();
				int st = sc.nextInt();
				int time = sc.nextInt();
				comList[st].add(new Computer(ed, time, time));
			}

			// 시작점을 기준으로 탐색 진행.
			spreadVirus(C);
			System.out.println(resCnt + " " + resTime);
		}

	}

	private static void spreadVirus(int st) {

		PriorityQueue<Computer> pq = new PriorityQueue<>();

		for (int i = 0; i < comList[st].size(); i++) {
			pq.add(comList[st].get(i));
		}
		visited[st] = true;
		resCnt++;

		while (!pq.isEmpty()) {
			Computer computer = pq.poll();
			// 아직 바이러스가 퍼지지 않은 컴퓨터라면.
			if (!visited[computer.to]) {
				// 방문처리.
				visited[computer.to] = true;
				resCnt++;
				resTime = computer.gotVirus;
				// 연결된 컴퓨터 pq에 넣어주기.
				for (int i = 0; i < comList[computer.to].size(); i++) {
					Computer temp = comList[computer.to].get(i);
					pq.add(new Computer(temp.to, temp.time, computer.gotVirus + temp.time));
				}
			} else {
				continue;
			}

		}

	}

}