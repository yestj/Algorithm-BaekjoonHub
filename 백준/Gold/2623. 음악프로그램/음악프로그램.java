import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        // 해당 가수의 다음 가수 정보를 저장할 arrayList 생성
        ArrayList<ArrayList<Integer>> nextSingerInfo = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            nextSingerInfo.add(new ArrayList<>());
        }
        // 위상정렬용 차수 배열 생성
        int[] indegree = new int[N + 1];

        // 연결 정보 저장하기
        for (int i = 0; i < M; i++) {
            int num = sc.nextInt();
            int before = sc.nextInt();

            for (int j = 1; j < num; j++) {
                int next = sc.nextInt();
                nextSingerInfo.get(before).add(next);
                indegree[next]++;
                before = next;
            }
        }

        // 위상정렬
        ArrayList<Integer> result = topologicalSort(nextSingerInfo, indegree, N);

        // 결과값 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i) + "\n");
        }
        System.out.println(sb.toString());

    }

    private static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> nextSingerInfo, int[] indegree, int N) {

        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        // 차수가 0인 애들부터 담아주기
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int curr = q.poll();
            result.add(curr);

            for (int next : nextSingerInfo.get(curr)) {
                indegree[next]--;
                if(indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        // 사이클이 발생했다면 result의 사이즈가 N이 되기 전에 while문이 종료됨.
        if (result.size() != N) {
            result.clear();
            result.add(0);
        }

        return result;

    }

}