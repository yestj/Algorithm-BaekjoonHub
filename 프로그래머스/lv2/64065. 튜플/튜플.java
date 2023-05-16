import java.util.*;

class Solution {
    
    // 1. HashMap에 key = 숫자, value = 나타난 횟수 기준으로 저장
    // 2. Map.Entry를 사용하여 key, value 값 저장
    // 3. value 값 높은 순서대로 정렬하여 answer에 담기
    
    public int[] solution(String s) {
        
        HashMap<String, Integer> numMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '}' || c == ',') {
                if(sb.length() == 0) continue;
                if(numMap.containsKey(sb.toString())) {
                    numMap.replace(sb.toString(), numMap.get(sb.toString()) + 1);
                } else {
                    numMap.put(sb.toString(), 1);
                }
                sb = new StringBuilder();
            } else if (c != '{') {
                sb.append(c);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(numMap.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        int[] answer = new int[list.size()];

        for(int i = 0; i < answer.length; i++) {
            answer[i] = Integer.parseInt(list.get(i).getKey());
        }

        return answer;
    }
}