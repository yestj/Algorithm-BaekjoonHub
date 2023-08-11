import java.util.*;

public class Main {

    static int N, M;
    static int[] dist;
    static ArrayList<Node>[] adj;
    static boolean[] visited;

    static class Node implements Comparable<Node>{
        int to, cost;

        public Node(int to , int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // 초기화
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            int st = sc.nextInt();
            int ed = sc.nextInt();
            int cost = sc.nextInt();
            adj[st].add(new Node(ed, cost));
            adj[ed].add(new Node(st, cost));
        }

        //  1에서 N까지 가는 최소 비용 거리 다익스트라로 계산.
        dijkstra();
        System.out.println(dist[N]);
    }

    private static void dijkstra() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if(!visited[curr.to]) {
                visited[curr.to] = true;
            } else continue;

            for (int i = 0; i < adj[curr.to].size(); i++) {
                Node next = adj[curr.to].get(i);
                if(dist[next.to] > dist[curr.to] + next.cost) {
                    dist[next.to] = dist[curr.to] + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

    }

}