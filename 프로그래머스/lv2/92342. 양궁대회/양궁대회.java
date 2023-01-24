class Solution {
   static int[] appeachShots; // 어피치가 각 과녘에 몇 발씩 쐈는지 저장
    static int[] ryanShots = new int[11]; // 라이언이 각 과녘에 몇 발씩 쐈는지 저장
    static int maxDiff = -1; //

    static int[] answer = {-1}; // 결과값 저장

    public static int[] solution(int n, int[] info) {

        appeachShots = info.clone();
        dfs(1, n);
        return answer;

    }

    static void dfs(int cnt, int n) {

        if(cnt == n + 1){
            int appeachScore = 0;
            int ryanScore = 0;
            for(int i = 0; i < 11; i++) {
                if(appeachShots[i] != 0 || ryanShots[i] != 0) {
                    if(appeachShots[i] >= ryanShots[i]){
                        appeachScore += 10 - i;
                    } else {
                        ryanScore += 10-i;
                    }
                }
            }
            if(ryanScore > appeachScore && ryanScore - appeachScore >= maxDiff) {
                answer = ryanShots.clone();
                maxDiff = ryanScore - appeachScore;
            }
            return;
        }

        for (int i = 0; i < 11 && ryanShots[i] <= appeachShots[i]; i++) {
            ryanShots[i]++;
            dfs(cnt+1, n);
            ryanShots[i]--;
        }

    }
}