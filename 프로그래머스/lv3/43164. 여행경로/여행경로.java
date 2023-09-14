import java.util.*;

class Solution {
    List<String> routes;
    boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        routes = new ArrayList<>();
        visited = new boolean[tickets.length];
        
        dfs("ICN", "ICN", tickets, 0);
        Collections.sort(routes);        
        
        return routes.get(0).split(" ");
    }
    
    private void dfs(String from, String route, String[][] tickets, int cnt) {
        if (cnt == tickets.length) {
            routes.add(route);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && from.equals(tickets[i][0])) {
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, cnt + 1);
                visited[i] = false;
            }
        }
    }
    
}