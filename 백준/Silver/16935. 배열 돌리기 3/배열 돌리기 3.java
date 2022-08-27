import java.util.Scanner;

public class Main {
	
	static int N;
	static int M;
	static int[][] arr;
	static int[][] result;
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		int R = sc.nextInt();
		
		arr = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				arr[r][c]= sc.nextInt();
			}
		}
		
		for (int i = 0; i < R; i++) {
			int cmd = sc.nextInt();
			arrChanger(cmd);
			// 한 번 끝날 때마다 arr 리셋..
			N = result.length;
			M = result[0].length;
			arr = new int[N][M];
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					arr[r][c] = result[r][c];
				}
			}
		}
		
		for(int r = 0; r < arr.length; r++) {
			for(int c= 0; c < arr[0].length; c++) {
				System.out.print(arr[r][c]+" ");
			}
			System.out.println();
		}
		
	}
	
	static void arrChanger(int cmd) {
		
		switch(cmd) {
		case 1:
			result = new int[N][M];
			for(int r = 0; r < N; r++) {
				for(int c = 0; c< M; c++) {
					result[N-1-r][c] = arr[r][c];
				}
			}
			break;
		case 2: 
			result = new int[N][M];
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					result[r][M-1-c] = arr[r][c];
				}
			}
			break;
		case 3:
			result = new int[M][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					result[j][N-1-i] = arr[i][j];
				}
			}
			break;
		case 4: 
			result = new int[M][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					result[M-1-j][i] = arr[i][j];
				}
			}
			break;
		case 5: 
			result = new int[N][M];
			for(int r = 0; r < N/2; r++) {
				for(int c = 0; c < M/2; c++) {
					result[r][M/2+c] = arr[r][c];
				}
			}
			for(int r= 0; r < N/2; r++) {
				for(int c = M/2; c < M; c++) {
					result[r+N/2][c] = arr[r][c];
				}
			}
			for(int r = N/2; r < N; r++) {
				for(int c = M/2; c< M; c++) {
					result[r][c-M/2] = arr[r][c];
				}
			}
			for(int r = N/2 ; r < N ; r++) {
				for(int c = 0; c < M/2; c++) {
					result[r-N/2][c] = arr[r][c];
				}
			}
			break;
		case 6: 
			result = new int[N][M];
			for(int r = 0; r < N/2; r++) {
				for(int c = 0; c < M/2; c++) {
					result[r+N/2][c] = arr[r][c];
				}
			}
			for(int r= 0; r < N/2; r++) {
				for(int c = M/2; c < M; c++) {
					result[r][c-M/2] = arr[r][c];
				}
			}
			for(int r = N/2; r < N; r++) {
				for(int c = M/2; c< M; c++) {
					result[r-N/2][c] = arr[r][c];
				}
			}
			for(int r = N/2 ; r < N ; r++) {
				for(int c = 0; c < M/2; c++) {
					result[r][c+M/2] = arr[r][c];
				}
			}
			break;
		}
		
		
		
	}
}