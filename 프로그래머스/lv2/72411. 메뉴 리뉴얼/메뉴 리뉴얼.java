import java.util.*;

class Solution {
    
    static HashMap<String, Integer> combiMap; // 각 조합이 몇 번 등장했는지를 카운팅
    
    public ArrayList<String> solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();
        
        // 1. order들을 모두 오름차순 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] orderArr = orders[i].toCharArray();
            Arrays.sort(orderArr);
            orders[i] = String.valueOf(orderArr);
        }
        
        for (int i = 0; i < course.length; i++) {
            combiMap = new HashMap<>();
            int max = Integer.MIN_VALUE;
            
            //2-1. 각 order에서 course의 size만큼 만들 수 있는 조합이 몇 개 인지 계산하여 combiMap에 저장
            for (int j = 0; j < orders.length; j++) {
                StringBuilder sb = new StringBuilder();
                if(course[i] <= orders[j].length()) {
                    combi(orders[j], sb, 0, 0, course[i]);
                }
            }
            
            //2-2. 가장 많이 주문된 횟수를 combiMap에서 꺼내 max값으로 저장
            for (Map.Entry<String, Integer> entry : combiMap.entrySet()) {
                max = Math.max(max, entry.getValue());
            }
            
            //2-3. 다시 combiMap을 돌며 max 값에 해당하는 key값을 찾아 2이상일 경우 ArrayList에 추가해줌
            for (Map.Entry<String, Integer> entry : combiMap.entrySet()) {
                if(max >= 2 && entry.getValue() == max) {
                    answer.add(entry.getKey());
                }
            }
        }
        
        // 3. answer 오름차순으로 재정렬
        Collections.sort(answer);
        
        return answer;
    }
    
    // 조합을 구하는 메소드
    // order를 기준으로 sb에 조합을 붙여서 검증 예정. courseSize에 해당하는만큼 조합 만들어서 combiMap에 넣어줌.
    public static void combi(String order, StringBuilder sb, int idx, int cnt, int courseSize) {
        
        if(cnt == courseSize) {
            combiMap.put(sb.toString(), combiMap.getOrDefault(sb.toString(), 0) + 1);
            return;
        }
        
        for (int i = idx; i < order.length(); i++) {
            sb.append(order.charAt(i));
            combi(order, sb, i + 1, cnt + 1, courseSize);
            sb.delete(cnt, cnt + 1);
        }        
    }
    
}