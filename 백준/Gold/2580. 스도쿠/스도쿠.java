import java.util.Scanner;

public class Main {

	static int[][] board = new int[9][9];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				board[r][c] = sc.nextInt();
			}
		}

		sudoku(0, 0);

	}

	static void sudoku(int row, int col) {

		if (col == 9) {
			sudoku(row + 1, 0);
			return;
		}
		if (row == 9) {
			// 메소드 종료인줄 알았더니 시스템 전체 종료..!
			// 여기 안에 안 적고 메인 안에 적으니까 실행 안됨..!
			StringBuilder res = new StringBuilder();
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					res.append(board[r][c]).append(" ");
				}
				res.append("\n");
			}
			System.out.println(res);
			System.exit(0);
		}

		if (board[row][col] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (check(row, col, i)) {
					// 체크 다 하면 다음 col으로 넘어감.
					board[row][col] = i;
					sudoku(row, col + 1);
				}
			}
			// 다음 단계에서 다 실패해서 돌아왔을 경우, 이전 단계로 돌아가기 위해
			// 0으로 값 초기화 후 리턴.
			board[row][col] = 0;
			return;
		}
		// 0이 아닐 경우 다음 col값 탐색.
		sudoku(row, col + 1);

	}

	static boolean check(int row, int col, int value) {
		for (int j = 0; j < 9; j++) {
			// 1. 가로검사.
			if (board[row][j] == value)
				return false;
			// 2. 세로검사.
			if (board[j][col] == value)
				return false;
		}
		// 3. 삼각형 검사.
		int r = (row / 3) * 3;
		int c = (col / 3) * 3;
		for (int j = r; j < r + 3; j++) {
			for (int k = c; k < c + 3; k++) {
				if (board[j][k] == value)
					return false;
			}
		}

		return true;

	}
}