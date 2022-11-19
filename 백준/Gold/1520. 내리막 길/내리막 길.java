import java.util.Scanner;

public class Main {

    static int M, N;
    static int[][] map;
    static int[][] dp;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        map = new int[M][N];
        dp = new int[M][N];

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] = sc.nextInt();
                dp[r][c] = -1;
            }
        }
        System.out.println(dfs(0, 0));

    }

    static int dfs(int r, int c) {

        if (r == M - 1 && c == N - 1) {
            return 1;
        }

        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr >= M || nc < 0 || nc >= N){
                continue;
            }
            if(map[r][c] > map[nr][nc]){
                dp[r][c] += dfs(nr, nc);
            }
        }
        return dp[r][c];


    }

}