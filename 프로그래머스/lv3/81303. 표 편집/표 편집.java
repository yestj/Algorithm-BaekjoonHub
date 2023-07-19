import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        
        Stack<Integer> delSt = new Stack<>();
        int lastIdx = n; 
        
        for (int i = 0; i < cmd.length; i++) {
        
            // 이동시에는 배열 밖으로 움직이지 않는다고 했음! 따라서 별도 경계처리 하지 않아도 됨.
            if (cmd[i].charAt(0) == 'D') {
                k += Integer.parseInt(cmd[i].substring(2));
            } else if (cmd[i].charAt(0) == 'U') {
                k -= Integer.parseInt(cmd[i].substring(2));
            } else if (cmd[i].charAt(0) == 'C') { // 삭제
                delSt.push(k);
                lastIdx--;
                if(lastIdx == k) k--; // 마지막 자리에서 삭제한거면 현재 위치 땡겨줌.
            } else {
                int tmp = delSt.pop();
                lastIdx++;
                if (tmp <= k) k++; // 현재 선택한 셀보다 작은애를 살리면 k 위치가 하나 증가해야 함.
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lastIdx; i++) {
            sb.append("O");
        }
        
        // 여기에 저장된 셀 자리도 결국 셀이 삭제되면서 변경되는 애들이 반영된 값이기 때문에,
        // 스택 순서대로 꺼내서 삭제된 셀 자리에 넣어줘야 함.
        while(!delSt.isEmpty()) {
            sb.insert(delSt.pop().intValue(), "X");
        }
        
        return sb.toString();
    }
}