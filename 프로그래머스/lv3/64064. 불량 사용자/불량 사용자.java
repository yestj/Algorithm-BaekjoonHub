import java.util.*;

class Solution {
    
    String[] userIds;
    String[] bannedIds;
    boolean[] visited;
    
    // 제제 아이디 목록을 중복 없이 구하기 위해 이중 HashSet 사용
    HashSet<HashSet<String>> result = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        
        userIds = user_id;
        bannedIds = banned_id;
        visited = new boolean[userIds.length];
        
        comb(new HashSet<>(), 0);
        
        return result.size();
        
    }
    
    // bannedIds 크기 만큼 탐색을 했으면 결과값에 더해줌
    private void comb(HashSet<String> set, int cnt) {
        
        // bannedIds 크기 만큼 탐색을 했으면 결과값에 더해줌
        if(cnt == bannedIds.length) {
            result.add(new HashSet<>(set));
            return;
        }
        
        // userId를 돌면서 현재에 cnt idx에 해당하는 bannedId와 일치하는지 체크
        for (int i = 0; i < userIds.length; i++) {
            // 만약 이미 set에 포함되어 있는 애라면 pass
            if(set.contains(userIds[i])) continue;
            
            // 같은지 여부를 체크하고 같을 경우에만 set에 추가하고 cnt+1하고 다음으로 진행
            if(check(userIds[i], bannedIds[cnt])) {
                set.add(userIds[i]);
                comb(set, cnt + 1);
                // 돌아왔으면 제거해줌
                set.remove(userIds[i]);
            }
        }
    }
    
    private boolean check(String userId, String bannedId) {
        // 글자 길이가 다르면 바로 다르다고 return
        if(userId.length() != bannedId.length()) return false;

        for (int i = 0; i < userId.length(); i++) {
            if(bannedId.charAt(i) != '*' && (bannedId.charAt(i) != userId.charAt(i))) {
                return false;
            }
        }
        
        // 모두 다 통과
        return true;
    }
    
}