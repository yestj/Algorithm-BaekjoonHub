import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] map;
	static ArrayList<Point> house;
	static ArrayList<Point> chicken;
	static int minD = Integer.MAX_VALUE;

	static boolean[] chickenCheck;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];

		house = new ArrayList<>();
		chicken = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
				if (map[r][c] == 1) {
					house.add(new Point(r, c));
				} else if (map[r][c] == 2) {
					chicken.add(new Point(r, c));
				}
			}
		}

		chickenCheck = new boolean[chicken.size()];
		chickenDistance(0, 0);
		System.out.println(minD);

	}

	public static void chickenDistance(int cnt, int idx) {
		if (cnt == M) {
			int total = 0;
			for (int i = 0; i < house.size(); i++) {
				int minTemp = Integer.MAX_VALUE;
				for (int j = 0; j < chicken.size(); j++) {
					if (chickenCheck[j] == true) {
						int disTemp = Math.abs(house.get(i).r - chicken.get(j).r)
								+ Math.abs(house.get(i).c - chicken.get(j).c);
						minTemp = Math.min(minTemp, disTemp);
					}
				}
				total += minTemp;
			}
			minD = Math.min(total, minD);
			return;
		}

		for (int i = idx; i < chicken.size(); i++) {
			chickenCheck[i] = true;
			chickenDistance(cnt + 1, i + 1);
			chickenCheck[i] = false;

		}
	}

}

class Point {
	int r;
	int c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}