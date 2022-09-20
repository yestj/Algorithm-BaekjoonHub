import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String temp = sc.next();
		StringBuilder sb = new StringBuilder(temp);
		Stack<String> tempStr = new Stack<>();

		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			String cmd = sc.next();
			switch (cmd) {
			case "L":
				// 스트링빌더의 맨 끝에 있는 문자 스택에 저장.
				if (sb.length() != 0) {
					tempStr.push(sb.substring(sb.length() - 1, sb.length()));
					sb.delete(sb.length() - 1, sb.length());
				}
				break;
			case "D":
				// 스택에 있는 것 POP해서 스트링빌더에 어펜드하기.
				if (!tempStr.isEmpty()) {
					sb.append(tempStr.pop());
				}
				break;
			case "B":
				// 스트링빌더에 있는 거 뒤에서부터 삭제.
				if (sb.length() != 0) {
					sb.deleteCharAt(sb.length() - 1);
				}
				break;
			case "P":
				// 스트링빌더 뒤에 추가.
				String add = sc.next();
				sb.append(add);
				break;
			}
		}

		while (!tempStr.isEmpty()) {
			sb.append(tempStr.pop());
		}

		System.out.println(sb);

	}
}