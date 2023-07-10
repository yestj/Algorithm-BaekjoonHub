import java.util.ArrayList;

class Solution {
    public long solution(String expression) {
        long answer = Long.MIN_VALUE;
        // 수식의 경우의 수 저장
        char[][] op = {{'+', '-', '*'}, {'+', '*', '-'}, {'*', '+', '-'}, {'*', '-', '+'}, {'-', '*', '+'}, {'-', '+', '*'}};
        
        ArrayList<Long> operandList = new ArrayList<>();
        ArrayList<Character> operatorList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < expression.length(); i++) {
            if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
                operandList.add(Long.parseLong(sb.toString()));
                sb = new StringBuilder();
                operatorList.add(expression.charAt(i));
            } else {
                sb.append(expression.charAt(i));
            }
        }
        operandList.add(Long.parseLong(sb.toString()));
        
        
        for (int i = 0; i < 6; i++) {
            ArrayList<Long> tmpOperandList = new ArrayList<>(operandList);
            ArrayList<Character> tmpOperatorList = new ArrayList<>(operatorList);
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < tmpOperatorList.size(); k++) {
                    if (tmpOperatorList.get(k) == op[i][j]) {
                        tmpOperandList.set(k, calc(tmpOperandList.get(k), tmpOperandList.get(k + 1), tmpOperatorList.get(k)));
                        tmpOperandList.remove(k+1);
                        tmpOperatorList.remove(k);
                        k--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(tmpOperandList.get(0)));
        }
        
        return answer;
    }
    
    public long calc(Long a, Long b, char op) {
        if(op == '+') {
            return (a + b);
        } else if (op == '*') {
            return (a * b);
        } else {
            return (a - b);
        }
    }
}