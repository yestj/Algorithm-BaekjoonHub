class Solution {
    public static int solution(int[][] board, int[][] skill) {

        int N = board.length;
        int M = board[0].length;

        int[][] sum = new int[N + 1][M + 1];

        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            if (type == 1) {
                degree *= -1;
            }
            sum[r1][c1] += degree;
            sum[r2 + 1][c2 + 1] += degree;
            sum[r1][c2 + 1] += -degree;
            sum[r2 + 1][c1] += -degree;

        }

        for(int r = 1; r < N+1; r++) {
            for(int c = 0; c < M+1; c++) {
                sum[r][c] += sum[r-1][c];
            }
        }
        
        for(int c = 1; c < M+1; c++) {
            for(int r = 0; r < N+1; r++) {
                sum[r][c] += sum[r][c-1];
            }
        }

        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                board[r][c] += sum[r][c];
                if (board[r][c] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}