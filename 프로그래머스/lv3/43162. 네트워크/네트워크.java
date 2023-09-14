import java.util.*;

class Solution {
    
    int N;
    ArrayList<Integer>[] computerList;
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        N = n;
        computerList = new ArrayList[N];
        visited = new boolean[N];
        
        for (int i = 0; i < N; i++) {
            computerList[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                if(computers[i][j] == 1) {
                    computerList[i].add(j);
                }
            }
        }
        
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                answer++;
                dfs(i);
            }
        }
        
        return answer;
    }
    
    private void dfs(int idx) {
        visited[idx] = true;
        for (int i = 0; i < computerList[idx].size(); i++) {
            if(!visited[computerList[idx].get(i)]) {
                dfs(computerList[idx].get(i));
            }
        }
    }
}