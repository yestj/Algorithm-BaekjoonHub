import java.util.*;

// index를 두 개를 두고(start, end) 비교하는 형태로 진행
// 보석 개수를 미리 저장해놓고, 해당 개수를 채울 때까지 확인

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2]; // 정답 저장
        
        int gemCnt = new HashSet<>(Arrays.asList(gems)).size(); // 순수 보석들의 개수만 카운트해서 저장
        
        int len = Integer.MAX_VALUE; // 구간 길이를 비교하기 위해 선언
        int start = 0; // 시작점 설정
        
        Map<String, Integer> gemMap = new HashMap<>(); // 보석의 이름(key), 현재까지 나온 개수(value)
        
        for (int end = 0; end < gems.length; end++) {
            // 하나씩 뒤로가면서 새로 나온애면 1로 저장, 원래있던 애면 + 1 해서 저장
            gemMap.put(gems[end], gemMap.getOrDefault(gems[end], 0) +  1);
            
            // 만약 start에 있는 애가 또 나온다면(1 이상이라면) 뒤에 중복으로 등장한 것이기 때문에
            // start index를 하나씩 늘려주고 gem의 카운트 개수도 하나 줄여줘야 함
            while (gemMap.get(gems[start]) > 1) {
                gemMap.put(gems[start], gemMap.get(gems[start]) - 1);
                start++;
            }
            
            // 만약에 모든 보석을 다 찾았다면, 현재까지를 기준으로 길이 비교를 해서 갱신해줌 (이전에 저장한 길이가 더 길 때만!)
            if (gemMap.size() == gemCnt && len > (end - start)) {
                len = end - start;
                answer[0] = start + 1; // index 기준이기 때문에 + 1씩해줌
                answer[1] = end + 1;
            }
            
        }
        
        
        return answer;
    }
}