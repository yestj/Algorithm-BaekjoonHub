import java.util.*;

class Solution {
    
    int[][] map;
    int N;
    int[][] dp;
    boolean[][][] visited;
    
    int[] dr = {1, 0, -1, 0};
    int[] dc = {0, 1, 0, -1};
    
    class Pos {
        int r, c, dir, cost;
        
        public Pos(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] board) {
        
        map = board;
        N = board.length;
        dp = new int[N][N];
        for (int a[] : dp) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }
        visited = new boolean[N][N][4];
        
        return bfs(0, 0);
    }
    
    public int bfs(int r, int c) {
        
        Queue<Pos> q = new LinkedList<>();

        q.add(new Pos(r, c, -1, 0));
        dp[r][c] = 0;
        
        int minCost = Integer.MAX_VALUE;

        while(!q.isEmpty()) {

            Pos currPos = q.poll();

            if (currPos.r == N-1 && currPos.c == N-1) {
                minCost = Math.min(minCost, currPos.cost);
            }

            for (int d = 0; d < 4; d++) {
                int nr = currPos.r + dr[d];
                int nc = currPos.c + dc[d];
                if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1) continue;


                int nextCost = currPos.cost;
                if(currPos.dir == -1 || currPos.dir == d) {
                    nextCost += 100;
                } else {
                    nextCost += 600;
                }

                if(!visited[nr][nc][d] || nextCost <= dp[nr][nc]) {
                    q.add(new Pos(nr, nc, d, nextCost));
                    visited[nr][nc][d] = true;
                    dp[nr][nc] = nextCost;
                }
            }
        }
        
        return minCost;
    }
    
}