import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static class Node {
        int idx, similarity;

        public Node(int idx, int similarity) {
            this.idx = idx;
            this.similarity = similarity;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int Q = sc.nextInt();

        ArrayList<Node>[] adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int st = sc.nextInt();
            int ed = sc.nextInt();
            int similarity = sc.nextInt();

            adj[st].add(new Node(ed, similarity));
            adj[ed].add(new Node(st, similarity));
        }

        StringBuilder sb = new StringBuilder(); // 답 저장

        for (int i = 0; i < Q; i++) {
            int k = sc.nextInt(); // 유사도 몇 이상이어야 하는지
            int v = sc.nextInt(); // 시작 동영상

            int cnt = 0;

            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[N + 1];

            visited[v] = true;
            q.add(v);

            while (!q.isEmpty()) {
                int curr = q.poll();

                // k보다 작은 애와 연결되면 그 이후에 있는 애들은 모두 k이하로 갱신되므로 탐색할 필요 없음.
                for (int j = 0; j < adj[curr].size(); j++) {
                    if (!visited[adj[curr].get(j).idx] && adj[curr].get(j).similarity >= k) {
                        cnt++;
                        visited[adj[curr].get(j).idx] = true;
                        q.add(adj[curr].get(j).idx);
                    }
                }
            }

            sb.append(cnt + "\n");
        }

        System.out.println(sb);
    }

}