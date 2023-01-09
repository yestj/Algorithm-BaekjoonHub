import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int L = sc.nextInt();

		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N]; // 경사로를 설치한 곳 체크
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		int cnt = 0; // 최종 길의 개수

		// 가로 탐색
		test: for (int r = 0; r < N; r++) {
			int curr = map[r][0]; // 최초 값 저장
			int currLength = 1; // 현재 curr값이 얼마나 지속되고 있는지 저장
			for (int c = 1; c < N; c++) {
				if (!visited[r][c]) {
					// 1. 같은 값을 만나면 length값 증가시켜줌.
					if (map[r][c] == curr) {
						currLength++;
					}
					// 2. 1 이상 차이나는 값을 만날 경우 경사로를 놓을 수 없으므로 다음으로 넘어.
					else if (map[r][c] > curr + 1 || map[r][c] < curr - 1) {
						continue test;
					}
					// 3. 1만큼 크면 현재 length 값과 L값과 비교하여 경사로 놓을 수 있는지 확인.
					else if (map[r][c] == curr + 1) {
						// 경사로 설치 가능하므로 값 초기화
						if (currLength >= L) {
							curr = map[r][c];
							currLength = 1;
						} else {
							continue test;
						}
					}
					// 4. 1보다 작으면 현재 length 값과
					else {
						boolean flag = false;
						for (int i = 0; i < L; i++) {
							if (c + i < N && map[r][c + i] == map[r][c]) {
								visited[r][c + i] = true;
								flag = true;
							} else {
								flag = false;
							}
						}
						if (flag) {
							curr = map[r][c];
							currLength = 0;
						} else {
							continue test;
						}
					}
				}
			}
			// continue되지 않고 끝까지 왔으면 검증이 완료된 것이므로 cnt 증가
			cnt++;

		}

		visited = new boolean[N][N]; // 방문 초기화.
		// 세로 탐색
		test: for (int c = 0; c < N; c++) {
			int curr = map[0][c]; // 최초 값 저장
			int currLength = 1; // 현재 curr값이 얼마나 지속되고 있는지 저장
			for (int r = 1; r < N; r++) {
				if (!visited[r][c]) {
					// 1. 같은 값을 만나면 length값 증가시켜줌.
					if (map[r][c] == curr) {
						currLength++;
					}
					// 2. 1 이상 차이나는 값을 만날 경우 경사로를 놓을 수 없으므로 다음으로 넘어.
					else if (map[r][c] > curr + 1 || map[r][c] < curr - 1) {
						continue test;
					}
					// 3. 1만큼 크면 현재 length 값과 L값과 비교하여 경사로 놓을 수 있는지 확인.
					else if (map[r][c] == curr + 1) {
						// 경사로 설치 가능하므로 값 초기화
						if (currLength >= L) {
							curr = map[r][c];
							currLength = 1;
						} else {
							continue test;
						}
					}
					// 4. 1보다 작으면 현재 length 값과
					else {
						boolean flag = false;
						for (int i = 0; i < L; i++) {
							if (r + i < N && map[r + i][c] == map[r][c]) {
								visited[r + i][c] = true;
								flag = true;
							} else {
								flag = false;
							}
						}
						if (flag) {
							curr = map[r][c];
							currLength = 0;
						} else {
							continue test;
						}
					}
				}
			}
			// continue되지 않고 끝까지 왔으면 검증이 완료된 것이므로 cnt 증가
			cnt++;
		}

		System.out.println(cnt);

	}

}