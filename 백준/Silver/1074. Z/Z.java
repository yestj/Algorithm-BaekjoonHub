import java.util.Scanner;

public class Main {

	static int N;
	static int cnt;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		int r = sc.nextInt();
		int c = sc.nextInt();
		int size = (int) Math.pow(2, N);

		find(r, c, size);
		System.out.println(cnt);
	}

	private static void find(int r, int c, int size) {
		//
		if (size == 1) {
			return;
		}

		// 2사분면일 때
		if (r < size / 2 && c < size / 2) {
			// cnt시작점 바꿀 필요 없이 바로 다음으로 넘어감
			find(r, c, size / 2);
			// 1사분면일 때
		} else if (r < size / 2 && c >= size / 2) {
			cnt += (size * size) / 4;
			find(r, c - size / 2, size / 2);
			// 3사분면일 때
		} else if (r >= size / 2 && c < size / 2) {
			cnt += (size * size) / 4 * 2;
			find(r - size / 2, c, size / 2);
		} else {
			cnt += (size * size) / 4 * 3;
			find(r - size / 2, c - size / 2, size / 2);
		}

	}

}