import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int N, M, D;
    static int[][] originMap;
    static int[][] map;

    // 궁수 뽑기용
    static boolean[] picked;
    static int[] shooter;
    static int max;

    static class Enemy implements Comparable<Enemy> {
        int r, c, d;

        Enemy(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Enemy o) {
            if (this.d == o.d) {
                return this.c - o.c;
            }
            return this.d - o.d;
        }

        @Override
        public String toString() {
            return "Enemy{" +
                    "r=" + r +
                    ", c=" + c +
                    ", d=" + d +
                    '}';
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        D = sc.nextInt();

        originMap = new int[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                originMap[r][c] = sc.nextInt();
            }
        }

        picked = new boolean[M];
        pickShooter(0, 0);
        System.out.println(max);

    }

    // 적 공격하기
    static void play() {
        // 임시 맵에 원본 맵 복사해놓기.
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            map[r] = originMap[r].clone();
        }
        int tempCnt = 0; // 적을 죽인 횟수 임시로 확인.
        while (enemyCheck()) {
            // 공격하기.
            for (int i = 0; i < 3; i++) {
                PriorityQueue<Enemy> pq = new PriorityQueue<>();
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        if (map[r][c] != 0) {
                            int d = Math.abs(r - N) + Math.abs(c - shooter[i]);
                            if (d <= D) {
                                pq.add(new Enemy(r, c, d));
                            }
                        }
                    }
                }
                if (!pq.isEmpty()) {
                    Enemy enemy = pq.poll();

                    if (map[enemy.r][enemy.c] == 1) {
                        tempCnt++;
                        map[enemy.r][enemy.c] = 2;
                    }
                }
            }

            move();
        }
        max = Math.max(tempCnt, max);

    }


    // 이동하기
    static void move() {
        for (int r = N - 2; r >= 0; r--) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 2) {
                    map[r][c] = 0;
                }
                map[r + 1][c] = map[r][c];
            }
        }
        for (int c = 0; c < M; c++) {
            map[0][c] = 0;
        }
    }

    // 적이 남아 있는지 확인하기.
    static boolean enemyCheck() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) {
                    // 적이 남아 있다면.
                    return true;
                }
            }
        }
        // 적이 없다면.
        return false;
    }

    // 궁수 뽑기(조합)
    static void pickShooter(int idx, int cnt) {
        if (cnt == 3) {
            shooter = new int[3];
            int j = 0;
            for (int i = 0; i < M; i++) {
                if (picked[i]) {
                    shooter[j++] = i;
                }
            }
            play();
            return;
        }

        for (int i = idx; i < M; i++) {
            if (!picked[i]) {
                picked[i] = true;
                pickShooter(idx + 1, cnt + 1);
                picked[i] = false;
            }
        }
    }

}