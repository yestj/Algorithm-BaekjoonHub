import java.util.Scanner;

public class Main {

	static int N, res;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}
		
		move(0,1,0);
		System.out.println(res);

	}

	static void move(int row, int col, int d) {
		if(row == N-1 && col == N-1) {
			res++;
			return;
		}
		
		switch(d) {
		// 1. 가로로 놓여 있을 경우, 가로 탐색.
		case 0:
			if(col+1 < N && map[row][col+1] != 1) {
				move(row, col+1, 0);
			}
			break;
		// 2. 세로로 놓여 있을 경우, 세로 탐색.
		case 1: 
			if(row+1 < N && map[row+1][col] != 1) {
				move(row+1, col, 1);
			}
			break;
		// 3. 대각선으로 놓여 있을 경우, 가로 세로 모두 탐색.
		case 2: 
			if(col+1 < N && map[row][col+1] != 1) {
				move(row, col+1, 0);
			}
			if(row+1 < N && map[row+1][col] != 1) {
				move(row+1, col, 1);
			}
			break;
		}
		
		// 어떤 방향이던지 대각선 방향은 탐색진행!
		if(col+1 < N && row +1 < N && map[row+1][col] != 1 && map[row][col+1] != 1 && map[row+1][col+1] != 1) {
			move(row+1, col+1, 2);
		}
	}
	
}