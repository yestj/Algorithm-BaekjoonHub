import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int a = 0;
		int b = 1;
		for(int i = 0; i < N-1; i++) {
			int temp = b;
			b = a + b;
			a = temp;
		}
		
		if(N == 0) System.out.println(a);
		else System.out.println(b);
		
		
	}
}