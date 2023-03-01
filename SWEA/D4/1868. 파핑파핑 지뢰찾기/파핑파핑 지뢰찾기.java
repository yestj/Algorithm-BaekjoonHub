import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    // 8방 탐색
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    static char[][] map;
    static int N;

    static boolean[][] visited;

    static public class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 1. 각 좌표별로 주변에 지뢰가 몇 개 있는지 체크
    // 2. 지뢰가 없는
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            visited = new boolean[N][N];

            for (int r = 0; r < N; r++) {
                String temp = br.readLine();
                for (int c = 0; c < N; c++) {
                    map[r][c] = temp.charAt(c);
                }
            }

            // 좌표별로 지뢰가 몇 개 있는지 체크
            int[][] mineCnt = new int[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == '.') {
                        mineCnt[r][c] = findMine(r, c);
                    } else {
                        mineCnt[r][c] = -1;
                        visited[r][c] = true;
                    }
                }
            }

            // 방문한 적 없고, 주위에 지뢰가 0개인애들 부터 눌러서 Queue에 넣고 방문처리 진행
            int cnt = 0;
            Queue<Pos> queue = new LinkedList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c] && mineCnt[r][c] == 0) {
                        queue.add(new Pos(r, c));
                        cnt++;
                        visited[r][c] = true;
                        while (!queue.isEmpty()) {
                            Pos currPos = queue.poll();
                            for (int i = 0; i < 8; i++) {
                                int nr = currPos.r + dr[i];
                                int nc = currPos.c + dc[i];
                                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                                    if (mineCnt[nr][nc] == 0) {
                                        queue.add(new Pos(nr, nc));
                                    }
                                    visited[nr][nc] = true;
                                }
                            }
                        }
                    }
                }
            }

            // 남은애들 눌러주기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c]) {
                        cnt++;
                    }
                }
            }

            System.out.println("#" + tc + " " + cnt);


        }
    }

    // 8방 탐색해서 지뢰의 개수를 반환해주는 메소드
    public static int findMine(int r, int c) {
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            // 경계선 안에 있고, 해당 위치에 지뢰가 있을 경우
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == '*') {
                cnt++;
            }
        }
        return cnt;
    }

}