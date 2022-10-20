import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] map;

	static int max; // 가장 큰 블록 저장.

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		play(0);
		System.out.println(max);

	}

	static void play(int cnt) {

		// 다섯번 이동 끝났으면 최댓값 찾아주기.
		if (cnt == 5) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					max = Math.max(max, map[r][c]);
				}
			}
			return;
		}
		// 맵을 카피에 먼저 복사해주기.
		int[][] copyMap = new int[N][N];
		for (int r = 0; r < N; r++) {
			copyMap[r] = map[r].clone();
		}
		// 4방으로 이동하기.
		for (int d = 0; d < 4; d++) {
			// 해당방향으로 움직이는 메소드 실행. (맵을 바꿔줌)
			move(d);
			// 재귀로 다음으로 넘어감.
			play(cnt + 1);
			// 돌아왔으면 맵 복원하고 다른 방향으로 진행하기.
			for (int r = 0; r < N; r++) {
				map[r] = copyMap[r].clone();
			}
		}
	}

	static void move(int dir) {
		switch (dir) {
		// 위로 가기.
		case 0:
			for (int c = 0; c < N; c++) {
				int value = 0; // 현재 합칠 수 있는 대상의 값.
				int idx = 0; // 해당 대상의 r위치.
				for (int r = 0; r < N; r++) {
					if (map[r][c] == 0)
						continue;
					if (value == 0) {
						// 처음 숫자를 만나면, value의 값을 해당숫자로 바꿔주고 idx는 그대로. 해당 자리는 이동했으므로 0으로 바꿔줌.
						value = map[r][c];
						map[r][c] = 0;
						map[idx][c] = value;
					} else {
						// 같은 value를 만나면 합쳐주고 idx+1
						if (value == map[r][c]) {
							map[idx++][c] *= 2;
							map[r][c] = 0;
							value = 0;
						} else {
							value = map[r][c];
							map[r][c] = 0;
							map[++idx][c] = value;
						}
					}
				}
			}
			break;
		// 아래로 가기.
		case 1:
			for (int c = 0; c < N; c++) {
				int value = 0; // 현재 합칠 수 있는 대상의 값.
				int idx = N - 1; // 해당 대상의 r위치.
				for (int r = N - 1; r >= 0; r--) {
					if (map[r][c] == 0)
						continue;
					if (value == 0) {
						// 처음 숫자를 만나면, value의 값을 해당숫자로 바꿔주고 idx는 그대로. 해당 자리는 이동했으므로 0으로 바꿔줌.

						value = map[r][c];
						map[r][c] = 0;
						map[idx][c] = value;

					} else {
						// 같은 value를 만나면 합쳐주고 idx-1
						if (value == map[r][c]) {
							map[idx--][c] *= 2;
							map[r][c] = 0;
							value = 0;
						} else {
							value = map[r][c];
							map[r][c] = 0;
							map[--idx][c] = value;
						}
					}
				}
			}
			break;
		// 왼쪽으로 가기.
		case 2:
			for (int r = 0; r < N; r++) {
				int value = 0; // 현재 합칠 수 있는 대상의 값.
				int idx = 0; // 해당 대상의 c위치.
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 0)
						continue;
					if (value == 0) {
						// 처음 숫자를 만나면, value의 값을 해당숫자로 바꿔주고 idx는 그대로. 해당 자리는 이동했으므로 0으로 바꿔줌.

						value = map[r][c];
						map[r][c] = 0;
						map[r][idx] = value;

					} else {
						// 같은 value를 만나면 합쳐주고 idx+1
						if (value == map[r][c]) {
							map[r][idx++] *= 2;
							map[r][c] = 0;
							value = 0;
						} else {
							value = map[r][c];
							map[r][c] = 0;
							map[r][++idx] = value;
						}
					}
				}
			}
			break;
		// 오른쪽으로 가기.
		case 3:
			for (int r = 0; r < N; r++) {
				int value = 0; // 현재 합칠 수 있는 대상의 값.
				int idx = N - 1; // 해당 대상의 c위치.
				for (int c = N - 1; c >= 0; c--) {
					if (map[r][c] == 0)
						continue;
					if (value == 0) {
						// 처음 숫자를 만나면, value의 값을 해당숫자로 바꿔주고 idx는 그대로. 해당 자리는 이동했으므로 0으로 바꿔줌.

						value = map[r][c];
						map[r][c] = 0;
						map[r][idx] = value;

					} else {
						// 같은 value를 만나면 합쳐주고 idx-1
						if (value == map[r][c]) {
							map[r][idx--] *= 2;
							map[r][c] = 0;
							value = 0;
						} else {
							value = map[r][c];
							map[r][c] = 0;
							map[r][--idx] = value;
						}
					}
				}
			}
			break;
		}
	}
}