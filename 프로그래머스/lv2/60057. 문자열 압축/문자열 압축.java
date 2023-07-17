class Solution {
    public int solution(String s) {
        int sLen = s.length();
        int answer = sLen;
    
        int maxLoop = s.length() / 2;
        for (int l = 1; l <= maxLoop; l++) {
            StringBuilder sb = new StringBuilder();
            String curStr = s.substring(0, l);
            int cnt = 1;
            for (int i = l; i <= sLen; i += l) {
                int endIdx = Math.min(i + l, sLen); // 글자 전체 길이보다 크면 안되기 때문에 한 번 걸러줌
                String nxtStr = s.substring(i, endIdx);
                if(nxtStr.equals(curStr)) {
                    cnt++;
                } else {
                    if(cnt >= 2) {
                        sb.append(cnt);
                    }
                    sb.append(curStr);
                    curStr = nxtStr;
                    cnt = 1;
                }
            }
            if(cnt > 1) {
                sb.append(cnt);
            }
            sb.append(curStr);
            answer = Math.min(answer, sb.length());
        } 
        return answer;
    }
}