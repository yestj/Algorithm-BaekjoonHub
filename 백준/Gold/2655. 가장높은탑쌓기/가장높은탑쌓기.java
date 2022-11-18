import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    // 벽돌 넓이 오름차순으로 넣어주기 위해 Comparable 선언.
    static class Brick implements Comparable<Brick> {
        int num;
        int area;
        int height;
        int weight;

        public Brick(int num, int area, int height, int weight) {
            this.num = num;
            this.area = area;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Brick o) {
            return this.area - o.area;
        }

        @Override
        public String toString() {
            return "Brick{" +
                    "num=" + num +
                    ", area=" + area +
                    ", height=" + height +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        ArrayList<Brick> bricks = new ArrayList<>();
        bricks.add(new Brick(0, 0, 0, 0));
        for (int i = 1; i <= N; i++) {
            int a = sc.nextInt();
            int h = sc.nextInt();
            int w = sc.nextInt();
            bricks.add(new Brick(i, a, h, w));
        }

        // 정렬하기
        Collections.sort(bricks);

        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (bricks.get(i).weight > bricks.get(j).weight) {
                    dp[i] = Math.max(dp[i], dp[j] + bricks.get(i).height);
                }
            }
        }
        int max = 0;
        for (int i = 0; i <= N; i++) {
            max = Math.max(max, dp[i]);
        }

        int idx = N;
        ArrayList<Integer> res = new ArrayList<>();

        while (idx > 0) {
            if (max == dp[idx]) {
                res.add(bricks.get(idx).num);
                // 높이를 빼가면서 같은 값 구하기.
                max -= bricks.get(idx).height;
            }
            idx--;
        }

        System.out.println(res.size());
        for (int i = res.size() - 1; i >= 0; i--) {
            System.out.println(res.get(i));
        }

    }

}