import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 회전 방향의 반대로 가면서 값을 바꿔 줄 예정으로,
		// 반대 방향인 우 -> 하 -> 좌 -> 상 순으로 이동.
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		int dIdx = 0;

		int N = sc.nextInt();
		int M = sc.nextInt();
		int R = sc.nextInt();
		int[][] arr = new int[N][M];

		// 배열의 값 입력 받기.
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				arr[r][c] = sc.nextInt();
			}
		}

		// 1 그룹 : 가장 바깥의 테두리 ... 2그룹: 1그룹을 제외 했을 때 가장 바깥의 테두리 그룹
		// 그룹의 수 구하기.
		int group = Math.min(N, M) / 2;

		// 몇 번 회전 할 지.
		for (int j = 0; j < group; j++) {
			int cycle = R % ((((N-2*j)+(M-2*j))*2)-4);
			for (int i = 0; i < cycle; i++) {
				int temp = arr[j][j];
				// 그룹별로 [0,0] [1,1].. 이 시작점이 됨.
				// 맨 처음 값은 사라지므로 임시로 저장해놨다가 마지막에 추가해주기.
				int row = j;
				int col = j;
				int nextRow = row;
				int nextCol = col;
				// 초기화 안하면 오류.
				dIdx = 0;
				// dIdx 가 4 이상이 되면 한 바퀴를 다 돈 상태이므로, dIdx가 4이하일 때까지만 반복.
				while (dIdx < 4) {
					nextRow = row + dr[dIdx];
					nextCol = col + dc[dIdx];
					if (nextRow < j || nextRow >= N - j || nextCol < j || nextCol >= M - j) {
						dIdx++;
					} else {
						arr[row][col] = arr[nextRow][nextCol];
						row = nextRow;
						col = nextCol;
					}
				}
				arr[j + 1][j] = temp;
			}
		}

		StringBuilder result = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				result.append(arr[r][c]).append(" ");
			}
			result.deleteCharAt(result.length() - 1);
			result.append("\n");
		}
		result.deleteCharAt(result.length() - 1);
		System.out.println(result.toString());

	}
}