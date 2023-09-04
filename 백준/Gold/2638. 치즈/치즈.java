import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] map;
    static int cheeseCnt;
    static Queue<Cheese> cheeseList;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static class Cheese {
        int r, c;

        public Cheese(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        cheeseCnt = 0;
        cheeseList = new LinkedList<>();


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    cheeseList.add(new Cheese(i, j));
                    cheeseCnt++;
                }
            }
        }

        int time = 0;

        while (cheeseCnt != 0) {
            time++;
            visited = new boolean[N][M];
            checkAir(0, 0);
            meltCheese();
        }

        System.out.println(time);


    }

    // 외부공기만을 체크 (DFS)
    private static void checkAir(int r, int c) {
        visited[r][c] = true;
        map[r][c] = -1;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 1) continue;
            checkAir(nr, nc);
        }
    }

    // 치즈 녹이기
    private static void meltCheese() {
        Queue<Cheese> notMelted = new LinkedList<>();
        while (!cheeseList.isEmpty()) {
            Cheese cheese = cheeseList.poll();
            int cnt = 0;
            for (int d = 0; d < 4; d++) {
                int nr = cheese.r + dr[d];
                int nc = cheese.c + dc[d];
                if (map[nr][nc] == -1) cnt++;
            }
            if (cnt >= 2) {
                map[cheese.r][cheese.c] = 0;
                cheeseCnt--;
            } else {
                notMelted.add(cheese);
            }
        }
        while (!notMelted.isEmpty()) {
            cheeseList.add(notMelted.poll());
        }

    }

}