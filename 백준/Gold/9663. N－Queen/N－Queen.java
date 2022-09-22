import java.util.Scanner;

public class Main {

	static int N, res;
	static int[][] board;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		res = 0;
		board = new int[N][N];

		putQueen(0);
		System.out.println(res);
	}

	static void putQueen(int row) {
		if (row == N) {
			res++;
			return;
		}

		for (int col = 0; col < N; col++) {
			if (!isOk(row, col))
				continue;
			board[row][col] = 1;
			putQueen(row + 1);
			board[row][col] = 0;
		}

	}

	static boolean isOk(int row, int col) {
		int left = col;
		int right = col;

		for (int i = row - 1; i >= 0; i--) {
			left--;
			right++;
			if (left >= 0 && board[i][left] == 1)
				return false;
			if (board[i][col] == 1)
				return false;
			if (right < N && board[i][right] == 1)
				return false;
		}

		return true;
	}
}