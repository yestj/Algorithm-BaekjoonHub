import java.util.Scanner;

public class Solution {

	static int N, res;
	static int[][] board;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			res = 0;
			board = new int[N][N];

			putQueen(0, 0);
			System.out.println("#" + tc + " " + res);
		}

	}

	static void putQueen(int row, int col) {
		// 모든 열이 통과되면 케이스가 존재하는 것으로, res에 더해주고 리턴.
		if (row == N) {
			res++;
			return;
		}
		if (col == N) {
			return;
		}

		boolean flag = true;

		int left = col;
		int right = col;
		for (int i = row - 1; i >= 0; i--) {
			left--;
			right++;
			if (left >= 0 && board[i][left] == 1)
				flag = false;
			if (board[i][col] == 1)
				flag = false;
			if (right < N && board[i][right] == 1)
				flag = false;
		}

		if (flag) {
			board[row][col] = 1;
			putQueen(row + 1, 0);
			board[row][col] = 0;
		}
		putQueen(row, col + 1);

	}
}