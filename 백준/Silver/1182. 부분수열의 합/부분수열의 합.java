import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N, S;
	static int sumCnt;

	static int[] arr;
	static boolean[] check;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		S = sc.nextInt();

		arr = new int[N];
		check = new boolean[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		powerSet(0);
		if(S==0) {
			System.out.println(sumCnt-1);
		} else {
			System.out.println(sumCnt);
		}
		
	}

	static void powerSet(int cnt) {
		if (cnt == N) {
			int tempS = 0;
			for (int i = 0; i < N; i++) {
				if (check[i]) {
					tempS += arr[i];
				}
			}
			if (tempS == S) {
				sumCnt++;
			}
			return;
		}
		
		check[cnt] = true;
		powerSet(cnt+1);
		check[cnt] = false;
		powerSet(cnt+1);

	}
}