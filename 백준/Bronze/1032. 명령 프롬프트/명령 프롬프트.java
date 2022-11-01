import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		String[] arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.next();
		}

		StringBuilder sb = new StringBuilder();
		boolean isSame = true;
		for (int i = 0; i < arr[0].length(); i++) {
			char temp = arr[0].charAt(i);
			isSame = true;
			for (int j = 1; j < N; j++) {
				if (temp != arr[j].charAt(i)) {
					isSame = false;
					break;
				}
			}

			if (isSame) {
				sb.append(temp);
			} else {
				sb.append('?');
			}
		}

		System.out.println(sb.toString());

	}
}