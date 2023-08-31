class Solution {
    public int[] solution(int n) {
        int[] answer = new int[(n*(n+1))/2];
        
        int[][] map = new int[n][n];
        
        int r = -1;
        int c = 0;
        
        int cnt = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) { // 아래로 이동
                    r++;
                } else if (i % 3 == 1) { // 오른쪽으로 이동
                    c++;
                } else { // 대각선으로 이동
                    r--;
                    c--;
                }
                map[r][c] = cnt++;
            }
        }
        
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 0) {
                    break; // 0을 만나면 그 뒤는 다 0이니 계속 할 필요 없음
                }
                answer[idx++] = map[i][j];
            }
        }
        
        return answer;
    }
}