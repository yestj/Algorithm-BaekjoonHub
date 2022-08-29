import java.util.Scanner;

public class Main {

	// 북0, 동1, 남2, 서3 (입력 받는 방향의 순서대로 입력.
	// 탐색은 서, 남, 동, 북 순서로 진행.
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int N;
	static int M;
	static int[][] room;
	static int dIdx;
	
	static int currR;
	static int currC;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		

		N = sc.nextInt();
		M = sc.nextInt();
		room = new int[N][M];
		
		currR = sc.nextInt();
		currC = sc.nextInt();
		dIdx = sc.nextInt();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				room[r][c] = sc.nextInt();
			}
		}
		
		int cnt = 0;
		test: while(true) {
			int tempCnt = 0;
			clean();
			cnt++;
			while(tempCnt < 5) {
				changeDir();
				tempCnt++;
				if(move()) break;
				if(tempCnt == 4) {
					dirBack();
					if(moveBack()) {						
						tempCnt = 0;
					} else {
						break test;
					}
				}
			}
		}
		System.out.println(cnt);
	
	}

	static void clean() {
		if(room[currR][currC] == 0) {
			room[currR][currC] = 2;
		}
	}

	static void changeDir() {
		switch(dIdx) {
		case 0:
			dIdx = 3;
			break;
		case 1: 
			dIdx = 0;
			break;
		case 2: 
			dIdx = 1;
			break;
		case 3:
			dIdx = 2;
			break;
		}
		
	}
	
	static boolean move() {
		int nextR = currR + dr[dIdx];
		int nextC = currC + dc[dIdx];
		
		if(nextR >= 0 && nextR < N && nextC >= 0 && nextC < M && room[nextR][nextC] == 0) {
			currR = nextR;
			currC = nextC;
			return true;
		} else {
			return false;
		}
	}
	static void dirBack() {
		switch(dIdx) {
		case 0:
			dIdx = 2;
			break;
		case 1:
			dIdx = 3;
			break;
		case 2: 
			dIdx = 0;
			break;
		case 3:
			dIdx = 1;
			break;
		}
	}
	
	static boolean moveBack() {

		int nextR = currR + dr[dIdx];
		int nextC = currC + dc[dIdx];
		
		if(nextR >= 0 && nextR < N && nextC >= 0 && nextC < M && room[nextR][nextC] != 1) {
			currR = nextR;
			currC = nextC;
			dirBack();
			return true;
		} else {
			return false;
		}
		
	}
}