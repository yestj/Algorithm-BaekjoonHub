import java.util.Arrays;

class Solution {
    
    public int solution(int alp, int cop, int[][] problems) {
        
        // 먼저 모든 문제를 풀기 위한 목표 알고력, 코딩력을 찾는다
        int tgAlp = 0;
        int tgCop = 0;
        for (int i = 0; i < problems.length; i++) {
            tgAlp = Math.max(problems[i][0], tgAlp);
            tgCop = Math.max(problems[i][1], tgCop);
        }
        
        // 이미 모든 문제를 풀 수 있는 알고/코딩력을 갖고 있으면 바로 종료
        if(alp >= tgAlp && cop >= tgCop) return 0;
        
        // 현재 알고/코딩력 중 하나가 목표보다 높을 경우 탐색 과정에서 배열 idx 초과될 수 있으므로 조정
        alp = Math.min(tgAlp, alp);
        cop = Math.min(tgCop, cop);
        
        // dp 배열 만들고 초기화
        int[][] dp = new int[tgAlp + 1][tgCop + 1];
        for (int[] costs : dp) {
            Arrays.fill(costs, Integer.MAX_VALUE);
        }
        
        dp[alp][cop] = 0; // 시작 위치
        
        for (int i = alp; i <= tgAlp; i++) {
            for (int j = cop; j <= tgCop; j++) {
                // 기본 문제를 풀 경우
                if (i + 1 <= tgAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j + 1 <= tgCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                
                // 선택 문제를 풀 경우, 모든 problems를 돌며 비교해주기
                for(int[] problem : problems) {
                    // 현재 알고/코딩력으로 풀 수 있는 경우에만 진행
                    if(i >= problem[0] && j >= problem[1]) {
                        // 다음으로 갈 수 있는 곳의 값을 갱신해줌. 단, 알고/코딩력을 초과할 경우는 조정
                        int nextAlp = Math.min(tgAlp, i + problem[2]);
                        int nextCop = Math.min(tgCop, j + problem[3]);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                    }
                }
            }
        }
        
    
        return dp[tgAlp][tgCop];
    
    }
}