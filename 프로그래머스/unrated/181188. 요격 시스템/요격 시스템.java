import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        // start - end 지점이 있을 때 end를 기준으로 오름차순
        Arrays.sort(targets, (o1, o2) -> {
            return o1[1] - o2[1];
        });
        
        int last = -1; 
        
        for (int[] target : targets) {
            // 처음이라면 일단 쏴 줌
            if (last == -1) {
                last = target[1] - 1;
                answer++;
                continue;
            }
            
            // last가 현재 target 길이 안에 있으면 그냥 진행
            if (last >= target[0] && last <= target[1]) continue;
            
            // 그게 아니라면 한 번 더 쏴주고 last값 갱신
            last = target[1] - 1;
            answer++;
        }
        return answer;
    }
}