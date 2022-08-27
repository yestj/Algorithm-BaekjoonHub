import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 4; tc++) {
			char result = '\0';

			int AX1 = sc.nextInt();
			int AY1 = sc.nextInt();
			int AX2 = sc.nextInt();
			int AY2 = sc.nextInt();

			int BX1 = sc.nextInt();
			int BY1 = sc.nextInt();
			int BX2 = sc.nextInt();
			int BY2 = sc.nextInt();

			// 1. 모서리끼리 만날 때.
			if ((AX1==BX2 && AY1==BY2) || (AX2==BX1 && AY1==BY2) || (AX2==BX1 && AY2==BY1) || (AX1 ==BX2 && AY2==BY1)) {
				result = 'c';
			// 2. 겹치지 않을 때.
			} else if (AX2 < BX1 || BX2 < AX1 || AY2 < BY1 || BY2 < AY1) {
				result = 'd';
			} else if (AY1 == BY2 || AX2 == BX1 || AX1 == BX2 || AY2 == BY1) {
				result = 'b';
			} else {
				result = 'a';
			}
			
			System.out.println(result);

		}

	}

}