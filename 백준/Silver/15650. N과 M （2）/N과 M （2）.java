import java.util.Scanner;

public class Main {

	static int N, M;
	static int[] nums;
	static boolean[] check;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = i + 1;
		}
		check = new boolean[N];
		combi(0, 0);
		

	}

	static void combi(int cnt, int idx) {
		if(cnt == M) {
			for(int i = 0; i < N; i++) {
				if(check[i]) {
					System.out.print(nums[i] + " ");
				}
			}
			System.out.println();
			return;
		}
		for (int i = idx; i < N; i++) {
			if(!check[i]) {
				check[i] = true;
				combi(cnt+1, i);
				check[i] = false;
			}
		}

	}
}