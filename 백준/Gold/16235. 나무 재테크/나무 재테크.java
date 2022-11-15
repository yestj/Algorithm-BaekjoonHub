import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N, M, K;

    static int[][] map; // 지도 (초기 양분은 5)
    static int[][] nutri; // 양분의 양
    static PriorityQueue<Tree> pq = new PriorityQueue<>();
    static Queue<Tree> dead = new LinkedList<>();

    static class Tree implements Comparable<Tree> {
        int r, c, age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        // 양분의 값 입력.
        map = new int[N][N];
        nutri = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] = 5;
                nutri[r][c] = sc.nextInt();
            }
        }

        // 나무 정보 입력.
        for (int i = 0; i < M; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int age = sc.nextInt();
            pq.add(new Tree(r, c, age));
        }

        // K년 동안 반복하기.
        for (int i = 0; i < K; i++) {
            spring();
            summer();
            fall();
            winter();
        }

        // 남은 나무의 숫자 구하기.
        int cnt = 0;
        while (!pq.isEmpty()) {
            pq.poll();
            cnt++;
        }
        System.out.println(cnt);

    }

    // 1. 봄에는 나무가 자신의 나이만큼 양분을 먹고 나이가 1 증가
    // 2. 같은 땅에 여러 나무가 있으면 작은 나이의 나무부터 양분을 먹음
    // 3. 자신의 나이만큼 양분을 못 먹으면 나무는 즉시 죽음.
    static void spring() {
        // 양분을 먹은 나무를 저장할 임시 큐.
        Queue<Tree> temp = new LinkedList<>();
        while (!pq.isEmpty()) {
            Tree tree = pq.poll();
            if (map[tree.r][tree.c] >= tree.age) {
                map[tree.r][tree.c] -= tree.age;
                temp.add(new Tree(tree.r, tree.c, tree.age + 1));
            } else {
                // 죽은 나무는 큐에 저장해줌.
                dead.add(new Tree(tree.r, tree.c, tree.age));
            }
        }
        // 양분을 먹은 나무들을 다시 pq에 넣어주기.
        while (!temp.isEmpty()) {
            pq.add(temp.poll());
        }
    }


    // 죽은 나무들을 양분으로 바꿔준다.
    static void summer() {
        while (!dead.isEmpty()) {
            Tree tree = dead.poll();
            map[tree.r][tree.c] += (tree.age / 2);
        }
    }

    // 나무를 중심으로 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
    static void fall() {
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        // 나무 검사를 위해 임시 큐 생성.
        Queue<Tree> temp = new LinkedList<>();
        while (!pq.isEmpty()) {
            Tree tree = pq.poll();
            if (tree.age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nr = tree.r + dr[i];
                    int nc = tree.c + dc[i];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        temp.add(new Tree(nr, nc, 1));
                    }
                }
            }
            temp.add(tree);
        }

        // temp 에 있던 나무들을 다시 pq에 넣어주기.
        while (!temp.isEmpty()) {
            pq.add(temp.poll());
        }

    }

    // 로봇이 돌아다니면서 땅에 양분을 넣어줌.
    static void winter() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] += nutri[r][c];
            }
        }

    }

}