import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int sum = sc.nextInt();
		int itemSum = 0;
		int items = sc.nextInt();
		for(int i = 0; i < items; i++) {
			int price = sc.nextInt();
			int nums = sc.nextInt();
			itemSum += (price*nums);
		}
		if(sum == itemSum) System.out.println("Yes");
		else System.out.println("No");
	}

}