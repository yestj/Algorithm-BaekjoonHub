import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int[] arr = new int[10];
			for (int i = 0; i < 10; i++) {
				arr[i] = sc.nextInt();
			}
			Arrays.sort(arr);
			System.out.println(arr[7]);
		}

	}
}