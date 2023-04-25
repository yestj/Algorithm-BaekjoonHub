import java.util.*;

class Edge implements Comparable<Edge> {
    int idx;
    int cost;
    
    Edge(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}

class Solution {
    
    static final int MAX = Integer.MAX_VALUE;
    static ArrayList<Edge>[] adj;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 다익스트라 돌기 전에 답은 Max값으로 채워줌 (최소값으로 갱신 예정)
        int answer = Integer.MAX_VALUE;
        
        // 지점 간 연결, 비용 정보 저장
        adj = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int[] fare : fares) {
            adj[fare[0]].add(new Edge(fare[1], fare[2]));
            adj[fare[1]].add(new Edge(fare[0], fare[2]));
        }
        
        // 다익스트라를 3지점에서 모두 계산해줌
        // s->x, b->x, a->x 결국 어느 한 지점에서 만나야하기 때문에
        // 세 지점에서 x지점으로 가는 비용을 모두 더했을 때 최솟값이 답
        
        int[] costFromA = new int[n+1];
        int[] costFromB = new int[n+1];
        int[] costFromS = new int[n+1];
        
        Arrays.fill(costFromA, MAX);
        Arrays.fill(costFromB, MAX);
        Arrays.fill(costFromS, MAX);
        
        costFromA = dijkstra(a, costFromA);
        costFromB = dijkstra(b, costFromB);
        costFromS = dijkstra(s, costFromS);
        
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, costFromA[i]+costFromB[i]+costFromS[i]);
        }
        
        return answer;
    }
    
    static int[] dijkstra (int st, int[] costs) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(st, 0));
        costs[st] = 0;
        
        while(!pq.isEmpty()) {
            Edge curr = pq.poll();
            int currIdx = curr.idx;
            int currCost = curr.cost;
            
            // 현재 값이 최소값이 아니라면 그냥 넘어감.
            if(currCost > costs[currIdx]) {
                continue;
            }
            
            for(int i = 0; i < adj[currIdx].size(); i++) {
                Edge nextEdge = adj[currIdx].get(i);
                int cost = costs[currIdx] + nextEdge.cost;
                
                if(cost < costs[nextEdge.idx]) {
                    costs[nextEdge.idx] = cost;
                    pq.offer(new Edge(nextEdge.idx, cost));
                }
            }
        }
        
        return costs;
    }
    
}