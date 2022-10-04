import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class Node {
		int curr, save, time;

		public Node(int curr, int save, int time) {
			this.curr = curr;
			this.save = save;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [curr=" + curr + ", save=" + save + ", time=" + time + "]";
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		// 1차원 배열로 방문처리를 할 경우, 클립보드에 다른 숫자가 저장되어 있는 경우를 고려하지 못함.
		// 따라서 2차원 배열로, 현재 화면에 있는 이모티콘의 개수와 클립보드에 저장되어 있는 이모티콘 개수를 함께 체크해야함.
		boolean[][] visited = new boolean[2001][2001];

		// 숫자들의 현 상태와 걸린 시간을 저장할 큐 우선순위 큐 만들어주기.
		// 시간이 짧은 것부터 먼저 실행될 수 있도록 Comparator 생성.
//		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
//
//			public int compare(Node o1, Node o2) {
//				return o1.time - o2.time;
//			}
//		});

		Queue<Node> pq = new LinkedList<>();

		// 1부터 시작하여 진행하기.
		pq.add(new Node(1, 0, 0));

		while (!pq.isEmpty()) {
			// 노드 하나 빼서 연산 진행하기.
			Node node = pq.poll();
			// 뺀 노드가 원하는 값일 경우 시간 출력하기.
			if (node.curr == N) {
				System.out.println(node.time);
				break;
			}

			if (node.curr > 0 && node.curr < 2000) {
				if (!visited[node.curr][node.curr]) {
					visited[node.curr][node.curr] = true;
					pq.add(new Node(node.curr, node.curr, node.time + 1));
				}

				if (!visited[node.curr - 1][node.save]) {
					visited[node.curr - 1][node.save] = true;
					pq.add(new Node(node.curr - 1, node.save, node.time + 1));
				}
			}

			if (node.save > 0 && node.curr + node.save < 2000) {
				if (!visited[node.curr + node.save][node.save]) {
					visited[node.curr + node.save][node.save] = true;
					pq.add(new Node(node.curr + node.save, node.save, node.time + 1));
				}
			}

//			// 1. 현재 숫자 복사하기. (+1s)
//			if (node.curr < 1000) {
//				visited[node.curr][node.curr] = true; 
//				pq.add(new Node(node.curr, node.curr, node.time + 1));
//			}
//
//			// 2. 기존에 갖고 있는 save값을 바로 더해주기. (+1s)
//			if (node.save != 0 && node.curr + node.save < 2000 && !visited[node.curr + node.save][node.save]) {
//				visited[node.curr + node.save][node.save] = true;
//				pq.add(new Node(node.curr + node.save, node.save, node.time + 1));
//			}
//			// 3. 이모티콘 하나 삭제(+1s)
//			if (node.curr > 0 && !visited[node.curr - 1][node.save]) {
//				visited[node.curr - 1][node.save] = true;
//				pq.add(new Node(node.curr - 1, node.save, node.time + 1));
//			}
		}

	}
}