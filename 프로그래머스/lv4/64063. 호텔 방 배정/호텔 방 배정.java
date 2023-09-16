import java.util.*;

/*
HashMap에 현재 방(key) 기준으로 다음 비어 있는 방(value)를 저장하고 있기
다음 방을 한 번만 탐색해서 빠르게 비어 있는 방을 찾을 수 있도록! 
*/
class Solution {
    
    Map<Long, Long> roomDic;
    
    public long[] solution(long k, long[] room_number) {
        
        roomDic = new HashMap<>();
        long[] answer = new long[room_number.length];
        
        for (int i = 0; i < room_number.length; i++) {
            answer[i] = findRoom(room_number[i]);
        }
        
        return answer;
    }
    
    private long findRoom(long pfrRoom) {
        
        if(!roomDic.containsKey(pfrRoom)) {
            roomDic.put(pfrRoom, pfrRoom + 1);
            return pfrRoom;
        }
        
        long nextRoom = roomDic.get(pfrRoom);
        long emptyRoom = findRoom(nextRoom);
        roomDic.put(pfrRoom, emptyRoom);
        return emptyRoom;
    }
    
}