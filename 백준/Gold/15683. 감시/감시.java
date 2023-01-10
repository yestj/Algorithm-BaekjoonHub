import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static int N, M; // 사무실의 크기
	public static int[][] map; // 사무실 벽, CCTV 위치 저장
	public static int[][] copyMap; // 브루트포스 진행위해 사용할 임시 맵
	public static ArrayList<CCTV> cctvList; // CCTV를 저장할 리스트
	public static int[] dirCases; // CCTV 방향 경우의 수를 저장하기 위한 배열
	public static int[] dr = { -1, 0, 1, 0 }; // 상, 우, 좌, 하 방향
	public static int[] dc = { 0, 1, 0, -1 };
	public static int ans = Integer.MAX_VALUE; // 사각지대 최솟값을 구할 변수

	// CCTV의 번호와 좌표를 저장할 클래스
	public static class CCTV {
		int num;
		int r;
		int c;

		public CCTV(int num, int r, int c) {
			this.num = num;
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "CCTV [num=" + num + ", r=" + r + ", c=" + c + "]";
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		cctvList = new ArrayList<>();

		// 입력 값 받으며, cctv를 만날 경우 리스트에 더해줌
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();
				if (map[r][c] != 0 && map[r][c] != 6) {
					cctvList.add(new CCTV(map[r][c], r, c));
				}
			}
		}
		// 각 CCTV의 방향 경우의 수를 저장하기 위한 배열 생성.
		dirCases = new int[cctvList.size()];
		blindSpotTest(0, cctvList.size());

		System.out.println(ans); // 정답 출력.

	}

	/**
	 * CCTV의 방향 경우의 수에 따라 최소 사각지대를 계산해주는 메소드
	 * 
	 * @Param depth 현재 깊이
	 * @Param cctvNums cctv의 개수
	 */
	private static void blindSpotTest(int depth, int cctvNums) {

		if (depth == cctvNums) {
			// 모든 CCTV에 대해 방향이 정해지면, 카피맵에 원본맵을 복사함
			copyMap = new int[N][M];
			for (int r = 0; r < N; r++) {
				copyMap[r] = map[r].clone();
			}

			// 각 CCTV와 해당하는 방향에 따라 감시 구역을 지정할 수 있는 메소드를 호출
			for (int i = 0; i < cctvNums; i++) {
				setCctv(cctvList.get(i), dirCases[i]);
			}

			// 사각지대를 구할 수 있는 메소드를 호출
			getBlindSpot();
			return;
		}

		for (int i = 0; i < 4; i++) {
			dirCases[depth] = i;
			blindSpotTest(depth + 1, cctvNums);
		}

	}

	/**
	 * cctv의 번호에 따라 감시 방향을 설정해주는 메소드
	 * 
	 * @param cctv cctvList의 cctv idx
	 * @param d    cctv의 경우의 수
	 */
	private static void setCctv(CCTV cctv, int d) {
		int cctvNum = cctv.num;

		if (cctvNum == 1) {
			if (d == 0) {
				watch(cctv, 0);
			} // 상
			else if (d == 1)
				watch(cctv, 1); // 우
			else if (d == 2)
				watch(cctv, 2); // 하
			else if (d == 3)
				watch(cctv, 3); // 좌
		} else if (cctvNum == 2) {
			if (d == 0 || d == 2) {
				watch(cctv, 0);
				watch(cctv, 2);
			} else {
				watch(cctv, 1);
				watch(cctv, 3);
			}
		} else if (cctvNum == 3) {
			if (d == 0) {
				watch(cctv, 0);
				watch(cctv, 1);
			} else if (d == 1) {
				watch(cctv, 1);
				watch(cctv, 2);
			} else if (d == 2) {
				watch(cctv, 2);
				watch(cctv, 3);
			} else {
				watch(cctv, 3);
				watch(cctv, 0);
			}
		} else if (cctvNum == 4) {
			if (d == 0) {
				watch(cctv, 0);
				watch(cctv, 1);
				watch(cctv, 3);
			} else if (d == 1) {
				watch(cctv, 0);
				watch(cctv, 1);
				watch(cctv, 2);
			} else if (d == 2) {
				watch(cctv, 1);
				watch(cctv, 2);
				watch(cctv, 3);
			} else {
				watch(cctv, 0);
				watch(cctv, 2);
				watch(cctv, 3);
			}
		} else {
			watch(cctv, 0);
			watch(cctv, 1);
			watch(cctv, 2);
			watch(cctv, 3);
		}

	}

	/**
	 * 4방 탐색 방향으로 감시영역을 표시하는 메소드
	 * 
	 * @param cctv cctvList의 cctvIdx
	 * @param d    4방 탐색 방향
	 */
	private static void watch(CCTV cctv, int d) {
		int r = cctv.r;
		int c = cctv.c;

		while (true) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || copyMap[nr][nc] == 6) {
				break;
			}

			if (copyMap[nr][nc] == 0) {
				copyMap[nr][nc] = -1;
			}
			r = nr;
			c = nc;
		}

	}

	/**
	 * BlindSpot 개수를 카운팅할 메소드
	 */
	private static void getBlindSpot() {

		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (copyMap[r][c] == 0) {
					cnt++;
				}
			}
		}
		ans = Math.min(cnt, ans);

	}

}