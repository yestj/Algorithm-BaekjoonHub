import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    private static final int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] list;
    static ArrayList<Node>[] reverseList;
    static int N, M, X;

    static class Node implements Comparable<Node> {
        int to;
        int time;

        public Node(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return time - o.time;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        X = sc.nextInt();

        list = new ArrayList[N + 1];
        reverseList = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
            reverseList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int time = sc.nextInt();

            list[from].add(new Node(to, time));
            reverseList[to].add(new Node(from, time));
        }

        int res = Integer.MIN_VALUE;

        int goResult[] = dijkstra(list);
        int backResult[] = dijkstra(reverseList);

        for (int i = 1; i <= N; i++) {
            res = Math.max(res, goResult[i] + backResult[i]);
        }

        System.out.println(res);
    }

    static int[] dijkstra(ArrayList<Node>[] list) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;

        pq.offer(new Node(X, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int next = curr.to;

            if(visited[next]) continue;

            visited[next] = true;
            for (Node node : list[next]) {
                if (!visited[node.to] && dist[node.to] > (dist[next] + node.time)) {
                    dist[node.to] = dist[next] + node.time;
                    pq.offer(new Node(node.to, dist[node.to]));
                }
            }
        }

        return dist;

    }

}