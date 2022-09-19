import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		StringBuilder result = new StringBuilder();
		int cnt = 1;

		Stack<Integer> seq = new Stack<>();
		boolean flag = true;

		test: for (int i = 0; i < N; i++) {
			int temp = sc.nextInt();
			while (seq.isEmpty() || temp != seq.peek()) {
				if (cnt > N) {
					flag = false;
					break test;
				}
				seq.add(cnt++);
				result.append("+").append("\n");
			}

			if (temp == seq.peek()) {
				seq.pop();
				result.append("-").append("\n");
			}

		}

		if (flag)
			System.out.println(result);
		else
			System.out.println("NO");

	}

}