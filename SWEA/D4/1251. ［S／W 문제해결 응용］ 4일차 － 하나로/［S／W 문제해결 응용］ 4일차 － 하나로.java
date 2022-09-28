import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {

	static int[] p;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			int V = sc.nextInt();
			// 정점의 좌표 입력 받기
			int[] vx = new int[V];
			int[] vy = new int[V];

			for (int i = 0; i < V; i++) {
				vx[i] = sc.nextInt();
			}
			for (int i = 0; i < V; i++) {
				vy[i] = sc.nextInt();
			}
			// 세율 저장
			double tax = sc.nextDouble();

			// 간선의 배열 저장.
			int E = (V * (V - 1)) / 2;
			double[][] bridges = new double[E][3];
			int idx = 0;
			for (int i = 0; i < V - 1; i++) {
				for (int j = i + 1; j < V; j++) {
					bridges[idx][0] = i; // 시작 정점
					bridges[idx][1] = j; // 도착 정점
					bridges[idx][2] = (Math.pow(Math.abs(vx[i] - vx[j]), 2) + Math.pow(Math.abs(vy[i] - vy[j]), 2));
					idx++;
				}
			}
			// 1. 간선들을 거리가 짧은 순으로 정렬해줌.
			Arrays.sort(bridges, new Comparator<double[]>() {

				@Override
				public int compare(double[] o1, double[] o2) {
					return (int) (o1[2] - o2[2]);
				}

			});

			// 2. 대표를 저장할 배열 생성.
			p = new int[V];

			// 3. make-set(나 자신을 대표로 초기화)
			for (int i = 0; i < V; i++) {
				p[i] = i;
			}

			// 4. 간선을 선택
			double ans = 0;
			int pick = 0;
			for (int i = 0; i < E; i++) {
				// i번째 간선을 뽑아서 두 정점의 대표를 확인
				// 대표가 같다면 pass, 다르다면 union (ans 추가, pick 추가)
				int px = findSet((int) bridges[i][0]);
				int py = findSet((int) bridges[i][1]);

				if (px != py) {
					union(px, py);
					ans += bridges[i][2];
					pick++;
				}

				if (pick == (V - 1))
					break;
			}

			String res = String.format("%.0f", ans * tax);
			System.out.println("#" + tc + " " + res);
		}

	}

	private static void union(int px, int py) {
		p[findSet(py)] = findSet(px);
		p[py] = px;
	}

	private static int findSet(int i) {
		if (i != p[i]) {
			p[i] = findSet(p[i]);
		}
		return p[i];
	}

}