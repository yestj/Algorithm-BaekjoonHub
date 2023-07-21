import java.util.*;

class Solution {
    
    int colLen;
    int rowLen;
    
    public String[][] relation;
    public ArrayList<String> combList;
    public ArrayList<String> answer;
    public boolean[] visited;
    
    public int solution(String[][] relation) {
        
        colLen = relation[0].length;
        rowLen = relation.length;
        
        this.relation = relation;
        combList = new ArrayList<>();
        answer = new ArrayList<>();
        visited = new boolean[colLen];
        
        // 숫자 조합을 늘려가며 후보키가 될 수 있는지 확인.
        for (int i = 1; i <= relation[0].length; i++) {
            comb(0, i); // 순서 조합만 뽑아주는 것.
            findTuple();
            combList.clear();
        }
        
        return answer.size();
    }
    
    // 조합으로 순서 뽑기.
    public void comb (int start, int r) {
        if (r == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < colLen; i++) {
                if(visited[i]) {
                    sb.append(i);
                }
            }
            combList.add(sb.toString());
            return;
        }
        
        for (int i = start; i < colLen; i++) {
            if (!visited[i]) {
                visited[i] = true;
                comb (start + 1, r - 1);
                visited[i] = false;
            }
        }
    }
    
    public void findTuple() {
        
        // 조합 순서에 맞게 데이터 유일성 검증
        for (String comb : combList) {
            
            // 몇 번째 컬럼을 선택했는지를 정리
            int[] order = new int[comb.length()];
            for (int i = 0; i < order.length; i++) {
                order[i] = comb.charAt(i) - '0';
            }
            
            // HashSet으로 유일한 값인지 아닌지 확인
            HashSet<String> set = new HashSet<>();
            for (int i = 0; i < rowLen; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < order.length; j++) {
                    sb.append(relation[i][order[j]]);
                }
                set.add(sb.toString());
            }
            
            // 유일할 경우에만 최소성 검사 진행
            if (set.size() == rowLen) {
                boolean flag = false;
                for (String aStr : answer) {
                    int cnt = 0;
                    String[] tmp = aStr.split("");
                    for (String tmpOrder : tmp) {
                        if(comb.contains(tmpOrder)) cnt++;
                    }
                    if (aStr.length() == cnt) flag = true;
                }
                if(!flag) answer.add(comb);
            }
        }
    }
}