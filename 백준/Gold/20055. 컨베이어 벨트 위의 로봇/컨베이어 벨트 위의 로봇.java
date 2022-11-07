import java.util.Scanner;

public class Main {

	static int N, K;
	static int[] belt;
	static boolean[] robot;

	static int cnt; // 몇 번 반복되었는지 저장.

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		belt = new int[2 * N];
		for (int i = 0; i < 2 * N; i++) {
			belt[i] = sc.nextInt();
		}

		robot = new boolean[2 * N]; // 로봇이 있는 곳은 true 표시.
		cnt = 1;

		while (true) {
			moveBelt();
			moveRobot();
			if (belt[0] > 0) {
				robot[0] = true;
				belt[0]--;
			}
			// 내구도가 0이상인 칸이 K개 이상이라면 종료.
			if (!durability()) {
				break;
			} else {
				cnt++;
			}
		}

		System.out.println(cnt);

	}

	// 벨트를 한 칸 회전시키는 메소드.
	static void moveBelt() {
		int tempDrb = belt[2 * N - 1];
		boolean tempRobot = robot[2 * N - 1];
		for (int i = 2 * N - 2; i >= 0; i--) {
			belt[i + 1] = belt[i];
			robot[i + 1] = robot[i];
		}
		belt[0] = tempDrb;
		robot[0] = tempRobot;

		// 내리는 위치에 오면 무조건 내리므로 false로 바꿔줌.
		robot[N - 1] = false;
	}

	// 로봇만 이동시키는 메소드.
	// 가장먼저 벨트에 올라간 로봇부터 이기 때문에 끝 로봇부터 이동해야함!
	static void moveRobot() {
		boolean[] check = new boolean[N];

		for (int i = N - 2; i >= 0; i--) {
			// 로봇을 만났고, 이동할 수 있다면(칸이 비어 있고, 내구도가 0이상) 이동.
			if (robot[i] && !robot[i + 1] && belt[i + 1] > 0 && !check[i]) {
				robot[i + 1] = true;
				belt[i + 1]--;
				robot[i] = false;
				check[i + 1] = true;
			}
		}
		// 내리는 위치에 오면 무조건 내리므로 false로 바꿔줌.
		robot[N - 1] = false;
	}

	// 내구도 체크용 메소드.
	static boolean durability() {

		int cnt = 0;
		for (int i = 0; i < 2 * N; i++) {
			if (belt[i] == 0) {
				cnt++;
				// 내구도가 0인 칸이 K개 이상이 되면 멈추기.
				if (cnt >= K) {
					return false;
				}
			}
		}
		return true;
	}

}
