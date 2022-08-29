import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String input = sc.next();
		String bomb = sc.next();
		// 비교를 끝문자 부터 할 예정.
		char bombEnd = bomb.charAt(bomb.length() - 1);

		Stack<Character> str = new Stack<>();
		Stack<Character> bombTest = new Stack<>();
		Stack<Character> result = new Stack<>();
		boolean flag = true;

		int bombLen = bomb.length() - 1;
		for (int i = 0; i < input.length(); i++) {
			str.push(input.charAt(i));
			if (input.charAt(i) == bombEnd && str.size() >= bomb.length()) {
				test: for (int j = bombLen; j >= 0; j--) {
					char temp = str.pop();
					bombTest.push(temp);
					if (temp == bomb.charAt(j)) {
						flag = true;
					} else {
						flag = false;
						while (!bombTest.isEmpty()) {
							str.push(bombTest.pop());
						}
						break test;
					}
				}
				if (flag)
					bombTest.clear();
			}

		}

		while (!str.isEmpty()) {
			result.push(str.pop());
		}
		StringBuilder res = new StringBuilder();
		while (!result.isEmpty()) {
			res.append(result.pop());
		}

		if (res.length() == 0)
			System.out.println("FRULA");
		else
			System.out.println(res.toString());

	}
}