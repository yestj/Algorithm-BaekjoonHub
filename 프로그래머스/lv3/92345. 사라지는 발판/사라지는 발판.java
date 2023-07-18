class Solution {
    
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    int[][] board;
    int N, M;
    
    public class Result {
        boolean win;
        int cnt;
        
        public Result(boolean win, int cnt) {
            this.win = win;
            this.cnt = cnt; 
        }
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        this.N = board.length;
        this.M = board[0].length;
    
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0, 0).cnt;
    }
    
    public Result dfs(int Ar, int Ac, int Br, int Bc, int Adepth, int Bdepth) {
        
        boolean win = false;
        int winCnt = 5*5; // 이기는 애는 최소한 움직여야 하기 때문에 최댓값으로 초기화
        int loseCnt = Adepth + Bdepth; // 지는 애는 최대한 움직여야 하기 때문에 최소값으로 초기화
        
        if (Adepth == Bdepth && board[Ar][Ac] == 1) { // A가 움직일 차례일 때
            for (int d = 0; d < 4; d++) {
                int nr = Ar + dr[d];
                int nc = Ac + dc[d];
                if (checkValid(nr, nc)) {
                    board[Ar][Ac] = 0;
                    Result res = dfs(nr, nc, Br, Bc, Adepth + 1, Bdepth);
                    win |= !res.win; // 상대방이 false면 true, 상대방이 true면 변경 없음
                    if (res.win) {
                        loseCnt = Math.max(res.cnt, loseCnt);
                    } else {
                        winCnt = Math.min(res.cnt, winCnt);
                    }
                    board[Ar][Ac] = 1;
                }
            }
        } else if (Adepth > Bdepth && board[Br][Bc] == 1) { // B가 움직일 차례일 때
            for (int d = 0; d < 4; d++) {
                int nr = Br + dr[d];
                int nc = Bc + dc[d];
                if (checkValid(nr, nc)) {
                    board[Br][Bc] = 0;
                    Result res = dfs(Ar, Ac, nr, nc, Adepth, Bdepth + 1);
                    win |= !res.win;
                    if (res.win) {
                        loseCnt = Math.max(res.cnt, loseCnt);
                    } else {
                        winCnt = Math.min(res.cnt, winCnt);
                    }
                    board[Br][Bc] = 1;
                }
            }
        }
        
        return new Result(win, win ? winCnt : loseCnt);
        
        
    }
    
    public boolean checkValid(int r, int c) {
        if(r < 0 || r >= N || c < 0 || c >= M || board[r][c] == 0) return false;
        return true;
    }   
    
}