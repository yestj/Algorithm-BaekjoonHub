import java.util.*;

// 다익스트라로 모든 gates에 대한 각 

class Solution {
    
    // 등산로 연결 정보를 저장할 클래스
    class Node implements Comparable<Node> {
        
        int to;
        int intensity;
        
        public Node(int to, int intensity) {
            this. to = to;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(Node o) {
            if (this.intensity == o.intensity) {
                return this.to - o.to;
            }
            return this.intensity - o.intensity;
        }
        
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        
        // 인접 행렬 초기화
        ArrayList<Node>[] adj = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        // 등산로 정보 저장
        for (int i = 0; i < paths.length; i++) {
            adj[paths[i][0]].add(new Node(paths[i][1], paths[i][2]));
            adj[paths[i][1]].add(new Node(paths[i][0], paths[i][2]));
        }
        
        // 출입구와 산봉우리 지점 체크. 출입구는 다익스트라 탐색시에도 방문하지 않도록 방문체크 해버림
        boolean[] isGate = new boolean[n + 1];
        for (int i = 0; i < gates.length; i++) {
            isGate[gates[i]] = true;
        }
        boolean[] isSummit = new boolean[n + 1];
        for (int i = 0; i < summits.length; i++) {
            isSummit[summits[i]] = true;
        }
        
        // 각 출입구부터 다익스트라. 다른 출입구는 방문하지 않도록 하고, 산봉우리 방문할 경우 answer과 비교하여 값 갱신하고 continue.
        out: for (int i = 0; i < gates.length; i++) {
            boolean[] visited = new boolean[n + 1];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            
            int start = gates[i];
            int maxIntensity = 0;
            isGate[gates[i]] = false;
            
            pq.offer(new Node(start, 0));
            
            while(!pq.isEmpty()) {
                Node curr = pq.poll();
                
                if(visited[curr.to] || isGate[curr.to]) continue;
                
                maxIntensity = Math.max(maxIntensity, curr.intensity);
                
                // 다음이 산봉우리일 경우, 갱신해주고 out
                if(isSummit[curr.to]) {
                    if (answer[1] > maxIntensity) {
                        answer[0] = curr.to;
                        answer[1] = maxIntensity;
                    } else if (answer[1] == maxIntensity) {
                        if(answer[0] > curr.to) {
                            answer[0] = curr.to;
                        }
                    }
                    continue out;
                }
                
                // 다음이 그냥 쉼터일 경우에는 intensity만 비교해주고 pq에 이어진 등산로 추가해주기
                visited[curr.to] = true;
                for (Node next : adj[curr.to]) {
                    pq.offer(next);
                }
                
            }
            
        }
        
        return answer;
    }
}