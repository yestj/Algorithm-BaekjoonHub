import java.util.*;

class Solution {
    // 1. HashMap에 노드간 연결 정보를 저장해준다. (자식, 부모)
    // 2. seller[]를 돌며 HashMap<> 에 판매자와 amount*100 판매금액을 저장해준다
    // 3. enroll[]을 돌며 2의 HashMap에서 돈을 꺼내 1의 HashMap을 이용해 DFS 탐색 진행
    // 3-1. 들어가며 HashMap에 실제 이익금 저장
    // 4. enroll[] 돌며 3-1에 저장한 최종 이익을 answer[]에 저장
    
    static String[] Enroll;
    static String[] Referral;
    static HashMap<String, String> connection;
    static HashMap<String, Integer> profit;
    static HashMap<String, Integer> netProfit;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Enroll = enroll;
        Referral = referral;
        
        // 1. HashMap에 노드간 연결 정보를 저장해준다. (자식, 부모)
        connection = new HashMap<>();
        for (int i = 0; i < Enroll.length; i++) {
            connection.put(Enroll[i], Referral[i]);
        }
        
        // 2. seller[]를 돌며 HashMap<> 에 판매자와 amount*100 이익금액을 저장해준다.
        profit = new HashMap<>();
        netProfit = new HashMap<>();
        for (int i = 0; i < seller.length; i++) {
            dfs(seller[i], amount[i] * 100);
        }
        
        int[] answer = new int[Enroll.length];
        for (int i = 0; i < Enroll.length; i++) {
            if (netProfit.containsKey(Enroll[i])) {
                answer[i] = netProfit.get(Enroll[i]);
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }
    
    static public void dfs(String seller, Integer profit) {
        if(profit == null) return; // Integer 타입 null처리..
        // 만약 현재 profit의 10%가 1원 미만이면 해당 seller에 귀속하고 종료
        if (profit * 0.1 < 1) {
            addProfit(seller, profit);
            return;
        }
        // 나눌 수 있다면 이익의 90%만 자신의 이익으로 더해주고, 부모 노드에 10%의 이익과 함께 dfs
        Integer parentProfit = profit / 10;
        addProfit(seller, profit - parentProfit);
        // 만약 부모 노드가 center("-")일 경우 dfs 진행하지 않음
        if(connection.get(seller).equals("-")) {
            return; 
        }
        dfs(connection.get(seller), parentProfit);
    }
    
    static public void addProfit(String seller, Integer profit) {
        if (netProfit.containsKey(seller)) {
            Integer currProfit = netProfit.get(seller);
            netProfit.put(seller, currProfit + profit);
        } else {
            netProfit.put(seller, profit);
        }
    }
}