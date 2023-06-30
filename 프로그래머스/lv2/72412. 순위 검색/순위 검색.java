import java.util.*;

class Solution {
    
    // info가 만들 수 있는 모든 조건에 대한 경우의 수를 key값으로 HashMap 만들기
    static HashMap<String, ArrayList<Integer>> infoMap;
    
    public int[] solution(String[] info, String[] query) {
        // 초기화
        int[] answer = new int[query.length];
        infoMap = new HashMap<>();
        
        // 1. info 정리하기 
        // 1-1. 조건 및 점수 infoMap에 저장하기
        for (String str : info) {
            createCond(0, str.split(" "), "");
        }
        
        // 1-2. infoMap에 있는 점수들 오름차순으로 정렬
        for (ArrayList<Integer> score : infoMap.values()) {
            Collections.sort(score);
        }
        
        // 2. query 돌면서 answer 찾아주기
        for (int i = 0; i < query.length; i++) {
            // 2-1. query 정리해주기
            String str = query[i].replace(" and ", "");
            String[] cvtQuery = str.split(" ");
            
            // 2-2. 이분탐색으로 정답 확인 후 추가
            answer[i] = (binarySearch(cvtQuery[0], Integer.parseInt(cvtQuery[1])));
        }
        
        return answer;
    }
    
    // dfs로 info 조건에 대한 HashMap 만들어주는 메소드.
    static void createCond(int depth, String[] info, String condition) {
        if(depth == 4) {
            if (infoMap.containsKey(condition)) {
                infoMap.get(condition).add(Integer.parseInt(info[4]));
            } else {
                ArrayList<Integer> score = new ArrayList<>();
                score.add(Integer.parseInt(info[4]));
                infoMap.put(condition, score);
            }
            return;
        }
        
        createCond(depth + 1, info, condition + info[depth]);
        createCond(depth + 1, info, condition + "-");
    }
    
    // 이분 탐색
    static int binarySearch(String query, int targetScore) {
        
        if(!infoMap.containsKey(query)) return 0;
        
        ArrayList<Integer> scoreList = infoMap.get(query);
        int start = 0;
        int end = scoreList.size() - 1;
        
        while(start <= end) {
            int mid = (start + end) / 2;
            if (targetScore > scoreList.get(mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return scoreList.size() - start;
        
        
        
    }
    
}