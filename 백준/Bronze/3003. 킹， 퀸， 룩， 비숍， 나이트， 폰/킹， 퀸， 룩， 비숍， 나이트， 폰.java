import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[] chessPieces = {1, 1, 2, 2, 2, 8};
		StringBuilder res = new StringBuilder();
		
		for (int i = 0; i < 6; i++) {
			int temp = sc.nextInt();
			res.append(chessPieces[i] - temp).append(" ");
		}
		
		System.out.println(res.toString());
	}
}