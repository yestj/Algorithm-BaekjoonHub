import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int curr = 0;
		int max = 0;
		for (int i = 0; i < 10; i++) {
			curr -= sc.nextInt();
			curr += sc.nextInt();
			max = Math.max(curr, max);
		}
		System.out.println(max);

	}
}