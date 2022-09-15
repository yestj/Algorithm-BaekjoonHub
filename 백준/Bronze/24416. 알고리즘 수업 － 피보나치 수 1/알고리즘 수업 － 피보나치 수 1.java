import java.util.Scanner;

public class Main {
	
	static int cnt1 = 1;
	static int cnt2;
	static int[] f;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		f = new int[N+1];
		f[1] = 1;
		f[2] = 1;
		
		fib(N);
		fibonacci(N);
		
		System.out.println(cnt1+" "+cnt2);
	}
	
	static int fib(int n) {
		if(n == 1 || n == 2) {
			return 1; 
		} else {
			cnt1++;
			return(fib(n-1) + fib(n-2));
		}
		
	}
	
	static int fibonacci(int n) {
		if(n >= 3) {
			for(int i = 3; i <= n; i++) {
				f[i] = f[i-1] + f[i-2];
				cnt2++;
			}
		}
		return f[n];
	}
	
}