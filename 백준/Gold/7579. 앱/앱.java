import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] dp = new int[10001]; // 비용 c를 썼을 때 최대 확보할 수 있는 메모리크기를 저장

        int[] memory = new int[N];
        int[] cost = new int[N];

        for (int i = 0; i < N; i++) {
            memory[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            cost[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 10000; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j-cost[i]] + memory[i], dp[j]);
            }
        }

        for (int i = 0; i < 10001; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }


    }

}