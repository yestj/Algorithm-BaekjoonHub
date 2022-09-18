import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();

		int gcd = 0;
		int lcm = 0;

		for (int i = Math.min(a, b); i > 0; i--) {
			if (a % i == 0 && b % i == 0) {
				gcd = i;
				break;
			}
		}

		lcm = gcd * (a / gcd) * (b / gcd);

		System.out.println(gcd);
		System.out.println(lcm);

	}

}