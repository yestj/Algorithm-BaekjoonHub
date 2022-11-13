import java.util.*;

public class Main {

    static int N;
    static int[] ppl;
    static boolean[] group;
    static List<Integer>[] adj;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 구역의 개수 저장.
        N = sc.nextInt();

        // 인구 수 저장.
        ppl = new int[N + 1];
        group = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            ppl[i] = sc.nextInt();
        }

        // 연결된 구역들의 정보 저장
        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            int conn = sc.nextInt();
            for (int c = 0; c < conn; c++) {
                int to = sc.nextInt();
                adj[i].add(to);
            }
        }

        setGroup(1);
        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);

    }

    static boolean checkConn() {
        boolean[] visited = new boolean[N + 1];

        // A구역(true)인 곳과 B구역(false)인 곳의 시작정점 아무 곳이나 설정.
        int aSt = 0;
        int bSt = 0;
        for (int i = 1; i <= N; i++) {
            if (group[i]) aSt = i;
            else bSt = i;

        }

        // 한쪽이 0이라면 구역이 두 개로 나눠지지 않은 것이므로 false 리턴.
        if (aSt == 0 || bSt == 0) return false;

        // 각각 구역에 연결된 애들끼리만 BFS 탐색 진행.
        Queue<Integer> groupA = new LinkedList<>();
        groupA.add(aSt);
        visited[aSt] = true;
        while (!groupA.isEmpty()) {
            int st = groupA.poll();
            for (int i = 0; i < adj[st].size(); i++) {
                int ed = adj[st].get(i);
                if (group[ed] && !visited[ed]) {
                    groupA.add(ed);
                    visited[ed] = true;
                }
            }
        }

        Queue<Integer> groupB = new LinkedList<>();
        groupB.add(bSt);
        visited[bSt] = true;
        while (!groupB.isEmpty()) {
            int st = groupB.poll();
            for (int i = 0; i < adj[st].size(); i++) {
                int ed = adj[st].get(i);
                if (!group[ed] && !visited[ed]) {
                    groupB.add(ed);
                    visited[ed]= true;
                }
            }
        }

        // 방문이 모두 되었다면 연결이 된 것이므로 true 반환
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) return false;
        }
        return true;


    }

    static void setGroup(int cnt) {
        if (cnt == N + 1) {
            // 모두 연결이 되어 있다면,
            if (checkConn()) {
                int scoreA = 0;
                int scoreB = 0;
                for (int i = 1; i <= N; i++) {
                    if (group[i]) scoreA += ppl[i];
                    else scoreB += ppl[i];
                }
                min = Math.min(min, (Math.abs(scoreA - scoreB)));

            }
            return;
        }

        group[cnt] = true;
        setGroup(cnt + 1);
        group[cnt] = false;
        setGroup(cnt + 1);
    }
}