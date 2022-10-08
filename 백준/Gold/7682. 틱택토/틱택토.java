import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		char[][] map = new char[3][3];

		while (true) {
			String temp = sc.next();
			if (temp.equals("end")) {
				break;
			}
			int xCnt = 0;
			int oCnt = 0;
			int bCnt = 0; // 빈칸 카운트.

			// 배열에 값 입력하기.
			// 값 입력 받으면서 x, o, . 의 개수 입력 받기.
			int idx = 0;
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					map[r][c] = temp.charAt(idx++);
					if (map[r][c] == 'X')
						xCnt++;
					else if (map[r][c] == 'O')
						oCnt++;
					else
						bCnt++;
				}
			}

			boolean flag = true;

			int xWin = 0;
			int oWin = 0;

			// 가로 검사.
			for (int i = 0; i < 3; i++) {
				int tmpX = 0;
				int tmpO = 0;
				for (int j = 0; j < 3; j++) {
					if (map[i][j] == 'X')
						tmpX++;
					else if (map[i][j] == 'O')
						tmpO++;
				}
				if (tmpX == 3)
					xWin++;
				if (tmpO == 3)
					oWin++;
			}

			// 세로 검사.
			for (int i = 0; i < 3; i++) {
				int tmpX = 0;
				int tmpO = 0;
				for (int j = 0; j < 3; j++) {
					if (map[j][i] == 'X')
						tmpX++;
					else if (map[j][i] == 'O')
						tmpO++;
				}
				if (tmpX == 3)
					xWin++;
				if (tmpO == 3)
					oWin++;
			}

			// 대각선 검사.

			for (int i = 0; i < 2; i++) {
				int tmpX = 0;
				int tmpO = 0;
				if (i == 0) {
					for (int j = 0; j < 3; j++) {
						if (map[j][j] == 'X')
							tmpX++;
						else if (map[j][j] == 'O')
							tmpO++;
					}
				} else {
					for (int j = 0; j < 3; j++) {
						if (map[2 - j][j] == 'X')
							tmpX++;
						else if (map[2 - j][j] == 'O')
							tmpO++;
					}
				}
				if (tmpX == 3)
					xWin++;
				if (tmpO == 3)
					oWin++;
			}
			if (xWin == 0 && oWin == 0) {
				if (bCnt != 0)
					flag = false;
				if (xCnt - 1 != oCnt)
					flag = false;
			} else if ((xWin == 1 || xWin == 2) && oWin == 0) {
				if (xCnt - 1 != oCnt)
					flag = false;
			} else if (xWin == 0 && oWin == 1) {
				if (xCnt != oCnt)
					flag = false;
			} else {
				flag = false;
			}

			if (flag)
				System.out.println("valid");
			else
				System.out.println("invalid");

		}
	}

}