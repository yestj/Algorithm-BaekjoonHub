class Solution {
    public String solution(String p) {
        if(checkParenthesis(p)) return p;
        return cvtParenthesis(p);
    }
    
    public String cvtParenthesis(String w) {
        
        // 1. 빈 문자열이면 빈 문자열 반환
        if (w.isBlank()) {
            return "";
        }
        
        // 2. w를 u, v로 분리. u는 더이상 분리가 되면 안됨.
        int idx = 0;
        int lCnt = 0;
        int rCnt = 0;
        
        while(true) {
            char parenthesis = w.charAt(idx++);
            if(parenthesis == '(') lCnt++;
            else rCnt++;
            
            if(lCnt == rCnt) break;
        }
        
        String u = w.substring(0, idx);
        String v = w.substring(idx, w.length());
        
        if(checkParenthesis(u)) {
            u += cvtParenthesis(v);
            return u;
        } else {
            String result = "(";
            result += cvtParenthesis(v);
            result += ")";
            result += changeDir(u.substring(1, u.length() - 1));
            return result;
        }
    }
    
    public boolean checkParenthesis(String b) {
        int cnt = 0; // '('의 개수 카운트
        for (int i = 0; i < b.length(); i++) {
            char parenthesis = b.charAt(i);
            if(parenthesis == '(') cnt++;
            else {
                if(cnt == 0) return false;
                cnt--;
            }
        }
        if(cnt != 0) return false;
        return true;
    }
    
    public String changeDir(String b) {
        String result = "";
        for (int i = 0; i < b.length(); i++) {
            if(b.charAt(i) == '(') result += ")";
            else result += "(";
        }
        return result;
    }
}