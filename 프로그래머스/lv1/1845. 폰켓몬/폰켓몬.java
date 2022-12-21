class Solution {
    public int solution(int[] nums) {
        // 1. 폰켓몬 종류별로 visited[] 배열을 만들어줌
        // 2. nums를 돌며, 방문한 적 없는 숫자이면 answer++
        // 3. answer이 nums/2의 길이에 도달하면 for문 종료

        boolean[] visited = new boolean[200001];
        int K = nums.length/2; // 가져갈 수 있는 최대 폰켓몬
        int answer = 0;

        for(int i = 0; i < nums.length; i++) {
            if(!visited[nums[i]]) {
                visited[nums[i]] = true;
                answer++;
                if(answer >= K) break;
            }
        }
        return answer;
    }
}