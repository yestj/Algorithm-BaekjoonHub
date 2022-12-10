import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// 위상정렬 이용 문제.
public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 학생의 수
		int M = sc.nextInt(); // 학생의 키 비교 횟수

		// 위상정렬 진입차수 저장 배열
		int[] indegree = new int[N + 1];
		// 위상정렬 그래프
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N + 1; i++) {
			graph.add(new ArrayList<Integer>());
		}

		// 리스트의 index: 학생번호
		// 키 정보 입력하면서 2차원 리스트 정보 및 진입차수 배열 값 변경
		for (int i = 0; i < M; i++) {
			int fr = sc.nextInt(); // 앞 학생
			int bk = sc.nextInt(); // 뒤 학생
			graph.get(fr).add(bk);
			indegree[bk]++;
		}

		// 위상정렬을 위한 큐
		Queue<Integer> q = new LinkedList<>();

		// 진입차수가 0인 값 큐에 저장
		for (int i = 1; i < N + 1; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		StringBuilder sb = new StringBuilder(); // 출력 값 저장.

		// 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			int currSt = q.poll(); // 학생 한 명 꺼내기
			sb.append(currSt + " "); // 학생을 출력 값에 저장
			List<Integer> list = graph.get(currSt); // 해당학생과 키 비교한 정보 가져오기
			for (int i = 0; i < list.size(); i++) {
				int temp = list.get(i); // 학생보다 뒤에 서야 하는 학생의 정보 가져오기
				indegree[temp]--; // 진입차수 감소시켜주기
				if (indegree[temp] == 0) {
					q.offer(temp); // 진입차수가 0이라면 q에 넣어주기.
				}
			}
		}

		System.out.println(sb.toString());

	}

}