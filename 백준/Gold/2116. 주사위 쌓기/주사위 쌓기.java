import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] dice = new int[N][6];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < 6; c++) {
				dice[r][c] = sc.nextInt();
			}
		}
		
		int maxSum = 0;
		
		for(int i = 0; i < 6; i++) {
			int row = 0;
			int topIdx = i;
			int bottomIdx = findBottom(topIdx);
			int topValue = dice[row][topIdx];
			int bottomValue = dice[row][bottomIdx];
			int sum = findMax(topValue, bottomValue);
			
			while(row < N-1) {
				for(int r = 0; r < 6; r++) {
					if(dice[row+1][r] == bottomValue) {
						topIdx = r;
						bottomIdx = findBottom(topIdx);
						topValue = dice[row+1][topIdx];
						bottomValue = dice[row+1][bottomIdx];
						sum += findMax(topValue, bottomValue);
						row++;
						break;
					}
				}
			}
			maxSum = Math.max(sum, maxSum);
		}
		
		System.out.println(maxSum);
		
		
	}
	
	static int findBottom(int idx) {
		
		switch(idx) {
		case 0:
			return 5;
		case 1: 
			return 3;
		case 2:
			return 4;
		case 3: 
			return 1;
		case 4: 
			return 2;
		case 5: 
			return 0;
		default:
			return -1;
		}
	}

	static int findMax(int top, int bottom) {
		
		if(top + bottom == 11) {
			return 4;
		} else if ((top == 6 || bottom == 6) && (top + bottom != 11)){
			return 5;
		} else {
			return 6;
		}
	}
}