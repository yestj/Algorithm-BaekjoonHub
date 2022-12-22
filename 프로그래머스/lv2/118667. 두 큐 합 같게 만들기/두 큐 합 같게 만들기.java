import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0;
        long sum2 = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for(int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            sum1 += queue1[i];                
        }
        for(int i = 0; i < queue2.length; i++) {
            q2.add(queue2[i]);
            sum2 += queue2[i];
        }
        
        long tg = (sum1+sum2)/2; // 목표 값 저장
        int cnt = 0;
        
        // sum1, sum2를 비교하여 목표 값보다 높은 큐에서 숫자를 빼 다른 큐에 더해줌
        while(sum1 != sum2 && cnt < queue1.length*4) {
            if(sum1 > tg) {
                int tmp = q1.poll();
                sum1 -= tmp;
                q2.add(tmp);
                sum2 += tmp;
                cnt++;
            } else {
                int tmp = q2.poll();
                sum2 -= tmp;
                q1.add(tmp);
                sum1 += tmp;
                cnt++;
            }
        }
        
        if(sum1 == sum2) return cnt;
        else return -1;
    }
}