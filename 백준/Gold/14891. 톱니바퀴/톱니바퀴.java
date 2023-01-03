import java.util.Scanner;

public class Main {

	static int[][] wheel = new int[4][8];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 4개의 톱니바퀴의 값을 저장
		for (int r = 0; r < 4; r++) {
			String tmp = sc.next();
			for (int c = 0; c < 8; c++) {
				wheel[r][c] = tmp.charAt(c) - '0';
			}
		}

		int K = sc.nextInt(); // 회전 횟수
		for (int k = 0; k < K; k++) {
			int idx = sc.nextInt() - 1; // 몇 번째 톱니바퀴를 돌릴지
			int dir = sc.nextInt(); // 최초 돌리는 톱니의 방향
			if (dir == 1) {
				if (checkLeft(idx)) {
					if (checkLeft(idx - 1)) {
						if (checkLeft(idx - 2)) {
							moveCounterClockwise(idx - 3);
						}
						moveClockwise(idx - 2);
					}
					moveCounterClockwise(idx - 1);
				}
				if (checkRight(idx)) {
					if (checkRight(idx + 1)) {
						if (checkRight(idx + 2)) {
							moveCounterClockwise(idx + 3);
						}
						moveClockwise(idx + 2);
					}
					moveCounterClockwise(idx + 1);
				}
				moveClockwise(idx);
			} else {
				if (checkLeft(idx)) {
					if (checkLeft(idx - 1)) {
						if (checkLeft(idx - 2)) {
							moveClockwise(idx - 3);
						}
						moveCounterClockwise(idx - 2);
					}
					moveClockwise(idx - 1);
				}
				if (checkRight(idx)) {
					if (checkRight(idx + 1)) {
						if (checkRight(idx + 2)) {
							moveClockwise(idx + 3);
						}
						moveCounterClockwise(idx + 2);
					}
					moveClockwise(idx + 1);
				}
				moveCounterClockwise(idx);
			}
		}

		// 최종 톱니바퀴 기준으로 결과 값 계산
		int res = 0;
		for (int i = 0; i < 4; i++) {
			if (wheel[i][0] == 1) {
				res += Math.pow(2, i);
			}
		}

		System.out.println(res);

	}

	// 왼쪽 톱니바퀴 검사 (왼쪽바퀴가 돌아야 하면 true 반환)
	static boolean checkLeft(int idx) {
		if (idx - 1 >= 0 && wheel[idx - 1][2] != wheel[idx][6]) {
			return true;
		}
		return false;
	}

	// 오른쪽 톱니바퀴 검사
	static boolean checkRight(int idx) {
		if (idx + 1 < 4 && wheel[idx + 1][6] != wheel[idx][2]) {
			return true;
		}
		return false;
	}

	// idx번째 바퀴의 값을 시계 방향으로 움직여주는 메소드
	static void moveClockwise(int idx) {
		int temp = wheel[idx][7];
		for (int i = 6; i >= 0; i--) {
			wheel[idx][i + 1] = wheel[idx][i];
		}
		wheel[idx][0] = temp;
	}

	// idx번째 바퀴의 값을 반시계 방향으로 움직여주는 메소드
	static void moveCounterClockwise(int idx) {
		int temp = wheel[idx][0];
		for (int i = 1; i < 8; i++) {
			wheel[idx][i - 1] = wheel[idx][i];
		}
		wheel[idx][7] = temp;
	}

}