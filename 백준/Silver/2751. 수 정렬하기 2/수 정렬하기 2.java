import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		ArrayList<Integer> nums = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			nums.add(sc.nextInt());
		}

		Collections.sort(nums);

		StringBuilder sb = new StringBuilder();
		for (int x : nums) {
			sb.append(x).append("\n");
		}

		System.out.println(sb);
	}

}