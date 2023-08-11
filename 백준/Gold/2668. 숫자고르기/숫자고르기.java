import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static int N;
    static int[] arr;
    static boolean[] visited;
    static ArrayList<Integer> answer;

    // dfs 탐색을 하여 시작한 지점으로 돌아오게 된다면 같은 집합
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        answer = new ArrayList<>();
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for(int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }

    private static void dfs(int now, int tg) {

        if(!visited[arr[now]]) {
            visited[arr[now]] = true;
            dfs(arr[now], tg);
            visited[arr[now]] = false;
        }

        if(arr[now] == tg) answer.add(tg);
    }

}