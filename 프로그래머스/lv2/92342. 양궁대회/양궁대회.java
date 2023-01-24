public class Solution {

    
    static int[] appeachShots; // 어피치가 각 과녘에 몇 발씩 쐈는지 저장
    static int[] ryanShots = new int[11]; // 라이언이 각 과녘에 몇 발씩 쐈는지 저장
    static int maxDiff = -1; // 가장 큰 차이 

    static int[] answer = {-1}; // 결과값 저장

    public static int[] solution(int n, int[] info) {

        appeachShots = info.clone();
        dfs(1, n);
        return answer;

    }

    static void dfs(int cnt, int n) {

				// 더이상 쏠 화살이 없으면 현재까지 점수를 계산해줌.
        if (cnt == n + 1) {
            int appeachScore = 0;
            int ryanScore = 0;
            for (int i = 0; i < 11; i++) {
                if (appeachShots[i] != 0 || ryanShots[i] != 0) {
                    if (appeachShots[i] >= ryanShots[i]) {
                        appeachScore += 10 - i;
                    } else {
                        ryanScore += 10 - i;
                    }
                }
            }
						// 계산한 결과 라이언이 우승인 경우에 answer배열을 업데이트 해 줌
            if (ryanScore > appeachScore && ryanScore - appeachScore >= maxDiff) {
                answer = ryanShots.clone();
                maxDiff = ryanScore - appeachScore;
            }
            return;
        }
				
				// 완탐으로 하나씩 늘려가면서 검사하게 함
        for (int i = 0; i < 11 && ryanShots[i] <= appeachShots[i]; i++) {
            ryanShots[i]++;
            dfs(cnt + 1, n);
            ryanShots[i]--;
        }

    }

}